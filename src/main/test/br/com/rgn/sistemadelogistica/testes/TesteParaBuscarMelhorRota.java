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
	public void buscarRotaTesteDeApraD(){
		Mapa esteMapa = criarMapaParaTesteDeApraD();
		RastreadorDeRotas rastreadorDeRotas = new RastreadorDeRotas();
		rastreadorDeRotas.encontrarMelhorRotaPara(esteMapa);
		MelhorRota melhorRota = esteMapa.getMelhorRota();
		
		boolean isMelhorRotaComPontos = melhorRota != null && melhorRota.getCusto() != null &&  melhorRota.getCusto() > 0.0;
		boolean isMelhorRotaComCusto = melhorRota != null && melhorRota.getPontos() != null && !melhorRota.getPontos().trim().equals("");
		boolean isRotaEsperada = melhorRota.getPontos().contains("A,B,D");
		boolean isCustoEsperado = melhorRota.getCusto().doubleValue() == 6.25;
		Assert.assertTrue( "Não obteve rota!", isMelhorRotaComPontos );
		Assert.assertTrue( "Não calculou custo!", isMelhorRotaComCusto );
		Assert.assertTrue( "Não é a rota esperada A B D!\nVeio " + melhorRota.getPontos(), isRotaEsperada );
		Assert.assertTrue( "Não é o custo esperado 6,25!\nVeio " + melhorRota.getCusto(), isCustoEsperado );
	}

	private Mapa criarMapaParaTesteDeApraD() {
		char origem = 'A';
		char destino = 'D';
		Double autonomia = 10.0;
		Double valorDoLitro = 2.50;
		RotaAhProcurar rotaAhProcurar = new RotaAhProcurar(origem, destino, autonomia, valorDoLitro);
		Mapa esteMapa = new Mapa("Rota de A a D", rotaAhProcurar);
		
		return esteMapa;
	}
	
	@Test
	public void buscarRotaTesteDeEpraA(){
		Mapa esteMapa = criarMapaParaTesteDeEpraA();
		RastreadorDeRotas rastreadorDeRotas = new RastreadorDeRotas();
		rastreadorDeRotas.encontrarMelhorRotaPara(esteMapa);
		MelhorRota melhorRota = esteMapa.getMelhorRota();
		boolean isRotaEsperada = melhorRota.getPontos().contains("E,D,B,A");
		boolean isCustoEsperado = melhorRota.getCusto().doubleValue() == 6.25;
		Assert.assertTrue( "Não é a rota esperada E D B A!\nVeio " + melhorRota.getPontos(), isRotaEsperada );
		Assert.assertTrue( "Não é o custo esperado 6,25!\nVeio " + melhorRota.getCusto(), isCustoEsperado );
	}
	
	private Mapa criarMapaParaTesteDeEpraA() {
		char origem = 'E';
		char destino = 'A';
		Double autonomia = 12.0;
		Double valorDoLitro = 2.80;
		RotaAhProcurar rotaAhProcurar = new RotaAhProcurar(origem, destino, autonomia, valorDoLitro);
		Mapa esteMapa = new Mapa("Rota de E a A", rotaAhProcurar);
		
		return esteMapa;
	}
	
}
