package br.com.rgn.sistemadelogistica.testes;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.rgn.sistemadelogistica.beans.Mapa;
import br.com.rgn.sistemadelogistica.beans.MelhorRota;
import br.com.rgn.sistemadelogistica.beans.Rota;
import br.com.rgn.sistemadelogistica.beans.RotaAhProcurar;
import br.com.rgn.sistemadelogistica.daos.MalhaLogisticaDAO;
import br.com.rgn.sistemadelogistica.facade.RastreadorDeRotas;

@RunWith(JUnit4.class)
public class TesteParaBuscarMelhorRota {
	
	public static List<Rota> rotas;
	public static MalhaLogisticaDAO malhaLogisticaDAO;
	
	@Before
	public void inserirRotasNoDBseNaoTiver(){
		malhaLogisticaDAO = new MalhaLogisticaDAO();
		try {
			rotas = malhaLogisticaDAO.consultarRotas();
		} catch (SQLException e) {
			System.out.println("Erro de SQL\n");
			e.printStackTrace();
		}
		if(rotas == null){
			adicionarRotas();
		}else if(rotas.isEmpty()){
			adicionarRotas();
		}
	}

	private void adicionarRotas(){
		try {
			Rota rota = new Rota();
			rota.setOrigem('A');
			rota.setDestino('B');
			rota.setDistanciaEmKM(10.0);
			malhaLogisticaDAO.adicionar(rota);
			
			rota = new Rota();
			rota.setOrigem('B');
			rota.setDestino('D');
			rota.setDistanciaEmKM(15.0);
			malhaLogisticaDAO.adicionar(rota);
			
			rota = new Rota();
			rota.setOrigem('A');
			rota.setDestino('C');
			rota.setDistanciaEmKM(20.0);
			malhaLogisticaDAO.adicionar(rota);
			
			rota = new Rota();
			rota.setOrigem('C');
			rota.setDestino('D');
			rota.setDistanciaEmKM(30.0);
			malhaLogisticaDAO.adicionar(rota);
			
			rota = new Rota();
			rota.setOrigem('B');
			rota.setDestino('E');
			rota.setDistanciaEmKM(50.0);
			malhaLogisticaDAO.adicionar(rota);
			
			rota = new Rota();
			rota.setOrigem('D');
			rota.setDestino('E');
			rota.setDistanciaEmKM(30.0);
			malhaLogisticaDAO.adicionar(rota);
			
		} catch (SQLException e) {
			System.out.println("Erro de SQL\n");
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscarRotaTeste1(){
		Mapa esteMapa = criarMapaParaTeste1();
		RastreadorDeRotas rastreadorDeRotas = new RastreadorDeRotas();
		rastreadorDeRotas.encontrarMelhorRotaPara(esteMapa);
		MelhorRota melhorRota = esteMapa.getMelhorRota();
		// com esses dados, melhor rota deve ser A B D com custo de 6,75
		
		Assert.assertTrue( "Não obteve rota!", isMelhorRotaComPontos(melhorRota) );
		Assert.assertTrue( "Não calculou custo!", isMelhorRotaComCusto(melhorRota) );
		Assert.assertTrue( "Não é a rota esperada A B D!", melhorRota.getPontos().contains("A,B,D") );
		Assert.assertTrue( "Não é o custo esperado 6,75!", melhorRota.getCusto().doubleValue() == 6.75 );
	}

	private Mapa criarMapaParaTeste1() {
		char origem = 'A';
		char destino = 'D';
		Double autonomia = 10.0;
		Double valorDoLitro = 2.50;
		RotaAhProcurar rotaAhProcurar = new RotaAhProcurar(origem, destino, autonomia, valorDoLitro);
		Mapa esteMapa = new Mapa("Rota de A a D", rotaAhProcurar);
		
		return esteMapa;
	}
	
	private boolean isMelhorRotaComCusto(MelhorRota melhorRota) {
		return melhorRota != null && melhorRota.getCusto() != null &&  melhorRota.getCusto() > 0.0;
	}

	private boolean isMelhorRotaComPontos(MelhorRota melhorRota) {
		return melhorRota != null && melhorRota.getPontos() != null && !melhorRota.getPontos().trim().equals("");
	}
	
}
