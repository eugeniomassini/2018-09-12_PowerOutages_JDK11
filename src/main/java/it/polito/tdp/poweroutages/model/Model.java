package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.PowerOutagesDAO;

public class Model {

	private PowerOutagesDAO dao;
	private Graph <Nerc, DefaultWeightedEdge> grafo;
	private Map<Integer, Nerc> idMap;

	public Model() {
		dao = new PowerOutagesDAO();
	}

	public void creaGrafo() {
		idMap = new HashMap<Integer, Nerc>();
		grafo = new SimpleWeightedGraph<Nerc, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		Graphs.addAllVertices(this.grafo, dao.loadAllNercs(idMap));

		for(Arco a: dao.getArchi(idMap)) {
			DefaultWeightedEdge e = this.grafo.getEdge(a.getN1(), a.getN2());
			if(e==null) {
					Graphs.addEdge(this.grafo, a.getN1(), a.getN2(), a.getPeso());
			}
		}

		System.out.format("#vertici: %d\n#archi: %d\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());

	}

	public List<Nerc> getNerc() {

		List<Nerc> nerc = new ArrayList<Nerc>(idMap.values());

		Collections.sort(nerc);

		return nerc;
	}

	public List<InfoNerc> getVicini(Nerc partenza) {
		List<InfoNerc> vicini = new ArrayList<InfoNerc>();
		
		for(Nerc n: Graphs.neighborListOf(this.grafo, partenza)) {
			
			vicini.add(new InfoNerc(n, this.grafo.getEdgeWeight(this.grafo.getEdge(partenza, n))));
			
		}
		Collections.sort(vicini);
		return vicini;
	}

}
