package it.polito.tdp.poweroutages.model;

public class Arco {

	private Nerc n1;
	private Nerc n2;
	private Double peso;
	public Arco(Nerc n1, Nerc n2, Double peso) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.peso = peso;
	}
	public Nerc getN1() {
		return n1;
	}
	public void setN1(Nerc n1) {
		this.n1 = n1;
	}
	public Nerc getN2() {
		return n2;
	}
	public void setN2(Nerc n2) {
		this.n2 = n2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Arco [n1=" + n1 + ", n2=" + n2 + ", peso=" + peso + "]";
	}
	
	
	
	
}
