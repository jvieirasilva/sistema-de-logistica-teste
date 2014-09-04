package br.com.rgn.sistemadelogistica.beans;


public class Mapa{
	
	private Long id;
	
	private String nome;
	private RotaAhProcurar rotaAhProcurar;
	private MelhorRota melhorRota;
	
	public Mapa(String nome, RotaAhProcurar rotaAhProcurar){
		this.nome = nome;
		this.rotaAhProcurar = rotaAhProcurar;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public RotaAhProcurar getRotaAhProcurar() {
		return rotaAhProcurar;
	}
	public void setRotaAhProcurar(RotaAhProcurar rotaAhProcurar) {
		this.rotaAhProcurar = rotaAhProcurar;
	}
	public MelhorRota getMelhorRota() {
		return melhorRota;
	}
	public void setMelhorRota(MelhorRota melhorRota) {
		this.melhorRota = melhorRota;
	}
}