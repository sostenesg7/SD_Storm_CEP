package com.zk;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


/**
 * Devemos implementar a classe IRichSpout para criarmos um Spout na plataforma Storm
 * 
 * Lembrando que para esse c�digo funcionar, devemos importar as Libs do Storm.
 * Em sala de aula utilizamos a vers�o 1.0.2
 */
public class ArraySpout implements IRichSpout {
	
	private static final long serialVersionUID = 1L;
	
	//Atributo que guarda a referencia do OutputSpout para usar no metodo nextTuple()
	private SpoutOutputCollector collector;
	
	//Contador utilizado para indexar o array de dados
	private int count = 0;
	
	//Array utilizado para simular o stream de dados (depois vamos trocar pela fila do ActiveMq)
	String[] data = {"Sport Campeao", "Nautico Lanterna", "UPE Caruaru Show", 
			         "POli melhor depois de Caruaru", "Ciencia de Dados", "Almoco Varanda", 
			         "Davi Rico com Chumbo", "Eronita e Carol Chefe ATI", "talita ganha 14mil",
			         "Seu Francisco home da estatistica", "Vitor mendoca o cara da APAC", 
			         "Carlos Vera Diego Souza"};



	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo � chamado pela plataforma/storm no in�cio da topologia.
	 */
	@Override
	public void open(Map arg0, TopologyContext context, SpoutOutputCollector arg2) {
		this.collector = arg2;
	}

	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo � chamado continuamente pela plataforma. 
	 */
	@Override
	public void nextTuple() {
		System.out.println("---------------------");
		System.out.println("ArraySpout --> nextTuple");
		
		//Zera contador para deixarmos acesso c�clico ao "stream de dados"
		if(count >= data.length) {
			count=0;
		}
		
		String information = data[count++];
		this.collector.emit(new Values(information));
		
		//Sleep para visualizarmos melhor a saida dos dados na tela.
		//Na vers�o em produ��o esse sleep nao existe.
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo declara os nomes dos campos que o Spout vai enviar para os bolts 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("linha"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void ack(Object arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fail(Object arg0) {
		// TODO Auto-generated method stub

	}
	

}
