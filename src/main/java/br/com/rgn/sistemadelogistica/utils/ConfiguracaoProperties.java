package br.com.rgn.sistemadelogistica.utils;

import java.util.ResourceBundle;

public class ConfiguracaoProperties {
	private ResourceBundle configuracao;
	
	// 2 construtores: 1 que usa o arquivo configuracao.properties e outro que recebe como parametro o caminho até o arquivo.
	// Obs: se for usar o segundo porque o seu arquivo tem outro nome, tire a extensão .properties
	public ConfiguracaoProperties(){
		this.configuracao = ResourceBundle.getBundle("/conf/configuracao");
	}
	public ConfiguracaoProperties(String arquivo){
		this.configuracao = ResourceBundle.getBundle(arquivo);
	}
	
	
	
	
		
	public String getParametroDeConfiguracao(String token){
		return configuracao.getString(token);
	}
}
