package br.com.rgn.sistemadelogistica.beans;

public class RotaAhProcurar {
	private char origem;
	private char destino;
	private Double autonomiaDoCaminhaoEmKMl;
	private Double litroDoCombustivel;
	
	public RotaAhProcurar(char origem, char destino, Double autonomiaDoCaminhaoEmKMl, Double litroDoCombustivel){
		this.origem = origem;
		this.destino = destino;
		this.autonomiaDoCaminhaoEmKMl = autonomiaDoCaminhaoEmKMl;
		this.litroDoCombustivel = litroDoCombustivel;
	}
	
	public char getOrigem() {
		return origem;
	}
	public void setOrigem(char origem) {
		this.origem = origem;
	}
	public char getDestino() {
		return destino;
	}
	public void setDestino(char destino) {
		this.destino = destino;
	}
	public Double getAutonomiaDoCaminhaoEmKMl() {
		return autonomiaDoCaminhaoEmKMl;
	}
	public void setAutonomiaDoCaminhaoEmKMl(Double autonomiaDoCaminhaoEmKMl) {
		this.autonomiaDoCaminhaoEmKMl = autonomiaDoCaminhaoEmKMl;
	}
	public Double getLitroDoCombustivel() {
		return litroDoCombustivel;
	}
	public void setLitroDoCombustivel(Double litroDoCombustivel) {
		this.litroDoCombustivel = litroDoCombustivel;
	}
}
