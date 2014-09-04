package br.com.rgn.sistemadelogistica.facade;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.rgn.sistemadelogistica.beans.MalhaLogistica;
import br.com.rgn.sistemadelogistica.beans.Mapa;
import br.com.rgn.sistemadelogistica.beans.MelhorRota;
import br.com.rgn.sistemadelogistica.beans.Rota;
import br.com.rgn.sistemadelogistica.beans.RotaAhProcurar;
import br.com.rgn.sistemadelogistica.daos.MalhaLogisticaDAO;

 
@WebService
public class RastreadorDeRotas {
	
	private MalhaLogisticaDAO malhaLogisticaDAO;
	private Result result;
	private Validator validador;
	private MalhaLogistica malhaLogistica;
	
	public RastreadorDeRotas(){
		this.malhaLogisticaDAO = new MalhaLogisticaDAO();
	}
	public RastreadorDeRotas(MalhaLogisticaDAO rotaDAO,
			Result result, Validator validador) {
		this.malhaLogisticaDAO = rotaDAO;
		this.result = result;
		this.validador = validador;
	}
	
	@WebMethod
	public Mapa encontrarMelhorRotaPara(Mapa esteMapaComRotaPraProcurar) {
		if(malhaLogistica == null){
			try{
				malhaLogistica = new MalhaLogistica(malhaLogisticaDAO.consultarRotas());
			}catch(SQLException erroDeSql){
				System.out.println("erro ao consultar rotas");
			}
		}
		StringBuilder pontos = new StringBuilder();
		
		RotaAhProcurar rotaAhProc = esteMapaComRotaPraProcurar.getRotaAhProcurar();
		Double distancia = 0.0;
		boolean caminhocrescente = rotaAhProc.getOrigem() < rotaAhProc.getDestino();
		
		List<Rota> rotas = malhaLogistica.getRotas();
		
		for(Rota rota: rotas){
			if(rota.getOrigem() == rotaAhProc.getOrigem()){
				if(!pontos.toString().contains(rota.getOrigem().toString())){
					pontos.append(rota.getOrigem());
					distancia += rota.getDistanciaEmKM();
				}
			}
			if(rota.getDestino() == rotaAhProc.getDestino()){
				if(!pontos.toString().contains(rota.getOrigem().toString())){
					pontos.append(",");
					pontos.append(rota.getOrigem());
				}
				if(!pontos.toString().contains(rota.getDestino().toString())){
					pontos.append(",");
					pontos.append(rota.getDestino());
					distancia += rota.getDistanciaEmKM();
				}
			}
		}
		
		definirMelhorRota(esteMapaComRotaPraProcurar, pontos, distancia);
		
		return esteMapaComRotaPraProcurar;
	}
	
	private void definirMelhorRota(Mapa esteMapa, StringBuilder pontos,
			Double distancia) {
		// a conta eh esta
		Double autonomia = esteMapa.getRotaAhProcurar().getAutonomiaDoCaminhaoEmKMl();
		Double valor = esteMapa.getRotaAhProcurar().getLitroDoCombustivel();
		Double custo = (distancia / autonomia) * valor;
		
		MelhorRota melhorRota = new MelhorRota();
		melhorRota.setCusto(custo);
		melhorRota.setPontos(pontos.toString()); 
		esteMapa.setMelhorRota(melhorRota);
	}
	
}