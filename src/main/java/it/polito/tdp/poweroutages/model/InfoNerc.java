package it.polito.tdp.poweroutages.model;

public class InfoNerc implements Comparable<InfoNerc>{
	private Nerc nerc;
	private Double peso;
	public InfoNerc(Nerc nerc, Double peso) {
		super();
		this.nerc = nerc;
		this.peso = peso;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "InfoNerc [nerc=" + nerc + ", peso=" + peso + "]";
	}
	@Override
	public int compareTo(InfoNerc o) {
		return Double.compare(this.peso, o.peso);
	}
}
