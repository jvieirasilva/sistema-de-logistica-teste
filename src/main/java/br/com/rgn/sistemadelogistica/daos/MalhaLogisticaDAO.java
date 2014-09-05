package br.com.rgn.sistemadelogistica.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.rgn.sistemadelogistica.beans.Mapa;
import br.com.rgn.sistemadelogistica.beans.Rota;

/**
 * Classe no padrão DAO para cadastrar, atualizar, selecionar ou excluir
 * rotas para a malha logística.
 * 
 */
public class MalhaLogisticaDAO {
	private static Logger logger = LoggerFactory.getLogger(MalhaLogisticaDAO.class);
	private Connection conexao;
	private ResultSet rs;
	private PreparedStatement statement;
	
	public List<Rota> consultarRotas() throws SQLException{
		try{
			this.conexao = DataConnectionFactory.getConexaoViaJDBC();
			List<Rota> rotas = new ArrayList<Rota>();
			
			String colunaId = "ID";
			String colunaOrigem = "ORIGEM";
			String colunaDestino = "DESTINO";
			String colunaDistanciaEmKm = "DISTANCIA_EM_KM";
			String query = fazerSQLlistarRotas(colunaId, colunaOrigem, colunaDestino,
					colunaDistanciaEmKm);
			
			logger.info("\nselecionarOperadoras: [query]\n" + query + "\n\n");
			statement = this.conexao.prepareStatement(query);
			rs = statement.executeQuery();
			
			Rota rota;
			while(rs.next()){
				rota = new Rota();
				rota.setId( rs.getInt(colunaId) );
				rota.setOrigem( rs.getString(colunaOrigem).charAt(0) );
				rota.setDestino( rs.getString(colunaDestino).charAt(0) );
				rota.setDistanciaEmKM(rs.getDouble(colunaDistanciaEmKm));
				rotas.add(rota);
			}
			Collections.sort(rotas);
			return rotas;
			
		}catch(SQLException erroDeCRUD){
			throw erroDeCRUD;
		}finally{
			fechaTudo();
		}
	}

	private String fazerSQLlistarRotas(String colunaId, String colunaOrigem,
		String colunaDestino, String colunaDistanciaEmKm
	) {
		StringBuilder query = new StringBuilder(); 
		query.append("SELECT ");
		query.append( colunaId);
		query.append(", ");
		query.append( colunaOrigem);
		query.append(", ");
		query.append( colunaDestino);
		query.append(", ");
		query.append( colunaDistanciaEmKm);
		query.append( " FROM ROTAS; ");
		return query.toString();
	}
	
	public boolean adicionar(Rota rota) throws SQLException{
		try{
			this.conexao = DataConnectionFactory.getConexaoViaJDBC();
			
			String colunaOrigem = "ORIGEM";
			String colunaDestino = "DESTINO";
			String colunaDistanciaEmKm = "DISTANCIA_EM_KM";
			String insert = fazerSQLInsertRotas(rota, colunaOrigem, colunaDestino, colunaDistanciaEmKm);
			
			statement = this.conexao.prepareStatement(insert.toString());
			boolean adicionou = statement.execute();
			return adicionou;
		}catch(SQLException erroDeCRUD){
			throw erroDeCRUD;
		}finally{
			fechaTudo();
		}		
	}
	
	
	private String fazerSQLInsertRotas(Rota rota, String colunaOrigem,
			String colunaDestino, String colunaDistanciaEmKm) {
		StringBuilder insert = new StringBuilder(); 
		insert.append("INSERT INTO ROTAS( ");
		insert.append( colunaOrigem);
		insert.append(", ");
		insert.append( colunaDestino);
		insert.append(", ");
		insert.append( colunaDistanciaEmKm);
		insert.append( ")");
		insert.append( " VALUES ( '");
		insert.append( rota.getOrigem() );
		insert.append("', '");
		insert.append( rota.getDestino() );
		insert.append("', ");
		insert.append( rota.getDistanciaEmKM() );
		insert.append( " ); ");
		return insert.toString();
	}
	
	public boolean adicionar(Mapa mapa) throws SQLException {
		try{
			this.conexao = DataConnectionFactory.getConexaoViaJDBC();
			
			String colunaNome = "NOME";
			String colunaAutonomiaDoCaminhao = "AUTONOMIA_DO_CAMINHAO_EM_KM_L";
			String colunaLitroDoCombustivel = "LITRO_DO_COMBUSTIVEL";
			String colunaPontosDaRota = "PONTOS_DA_ROTA";
			String colunaCusto = "CUSTO";
			
			String insert = fazerSQLInsertMapas(mapa, colunaNome, colunaAutonomiaDoCaminhao, colunaLitroDoCombustivel, colunaPontosDaRota, colunaCusto);
			
			statement = this.conexao.prepareStatement(insert.toString());
			boolean adicionou = statement.execute();
			return adicionou;
		}catch(SQLException erroDeCRUD){
			throw erroDeCRUD;
		}finally{
			fechaTudo();
		}
	}
	
	
	private String fazerSQLInsertMapas(
		Mapa mapa, String colunaNome, String colunaAutonomiaDoCaminhao, 
		String colunaLitroDoCombustivel, String colunaPontosDaRota, String colunaCusto
	){
		StringBuilder insert = new StringBuilder(); 
		insert.append("INSERT INTO MAPAS( ");
		insert.append( colunaNome);
		insert.append(", ");
		insert.append( colunaAutonomiaDoCaminhao);
		insert.append(", ");
		insert.append( colunaLitroDoCombustivel);
		insert.append(", ");
		insert.append( colunaPontosDaRota);
		insert.append(", ");
		insert.append( colunaCusto);
		insert.append( " )");
		
		insert.append( " VALUES ( '");
		insert.append( mapa.getNome() );
		insert.append("', ");
		insert.append( mapa.getRotaAhProcurar().getAutonomiaDoCaminhaoEmKMl() );
		insert.append(", ");
		insert.append( mapa.getRotaAhProcurar().getLitroDoCombustivel() );
		insert.append(", '");
		insert.append( mapa.getMelhorRota().getPontos() );
		insert.append("', ");
		insert.append( mapa.getMelhorRota().getCusto() );
		insert.append( " ); ");
		
		return insert.toString();
	}
	
	private void fechaTudo() throws SQLException {
		if(rs != null){
			rs.close();
		}
		if(statement != null){
			statement.close();
		}
		if(conexao != null && !conexao.isClosed()){
			conexao.close();
		}
	}
	
}