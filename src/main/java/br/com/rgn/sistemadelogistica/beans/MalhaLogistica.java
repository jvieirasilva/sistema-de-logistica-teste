package br.com.rgn.sistemadelogistica.beans;

import java.util.ArrayList;
import java.util.List;

public class MalhaLogistica {

	private List<Rota> rotas;
	
	public MalhaLogistica(Rota rota){
		if(this.rotas == null){
			this.rotas = new ArrayList<Rota>();
		}
		addRota(rota);
	}
	
	public MalhaLogistica(List<Rota> rotas){
		this.rotas = rotas;
	}
	
	public void addRota(Rota rota){
		this.rotas.add(rota);
	}
	
	public List<Rota> getRotas(){
		return rotas;
	}
}
