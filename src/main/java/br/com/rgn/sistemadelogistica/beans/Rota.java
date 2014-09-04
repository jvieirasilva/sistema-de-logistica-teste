package br.com.rgn.sistemadelogistica.beans;


public class Rota implements Comparable<Rota>{
	
	private Integer id;
	
	private char origem;
	private char destino;
	private Double distanciaEmKM;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Character getOrigem() {
		return origem;
	}
	public void setOrigem(Character origem) {
		this.origem = origem;
	}
	public Character getDestino() {
		return destino;
	}
	public void setDestino(Character destino) {
		this.destino = destino;
	}
	public Double getDistanciaEmKM() {
		return distanciaEmKM;
	}
	public void setDistanciaEmKM(Double distanciaEmKM) {
		this.distanciaEmKM = distanciaEmKM;
	}
	public int compareTo(Rota rotaAhComparar) {
		Character origem = this.origem;
		Character origemAhComparar = rotaAhComparar.getOrigem();
		return origem.compareTo(origemAhComparar);
	}
	
}