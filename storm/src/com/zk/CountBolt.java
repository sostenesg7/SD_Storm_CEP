package com.zk;

import java.util.ArrayList;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

/** 
 * Devemos implementar a classe IRichBolt para criarmos um Bolt na plataforma Storm
 * 
 * Lembrando que para esse c�digo funcionar, devemos importar as Libs do Storm.
 * Em sala de aula utilizamos a vers�o 1.0.2
 */ 
public class CountBolt implements IRichBolt {
	
	private static final long serialVersionUID = 1L;
	
	//Estruturas de dados usadas para contar as palavras (sugest�o do ga�cho P�ricles).
	private ArrayList<String> palavras = new ArrayList<String>();
	private ArrayList<Integer> contador = new ArrayList<Integer>();

	/*
	 * M�todo chamado pela plataforma. Como este bolt est� localizado no final da nossa topologia, 
	 * n�o � preciso guardar refer�ncia para os objetos passados como par�metro.
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
	}
	
	/*
	 * M�todo chamado pela plataforma/storm. A cada nova tupla (registro do stream) esse m�todo � chamado.
	 * Aqui temos um simples c�digo utilizado para contar e imprimir os dados sumarizados.
	 */
	@Override
	public void execute(Tuple input) {
		System.out.println("CountBolt --> execute");

		String word = input.getString(0);
		
		int idx = palavras.indexOf(word);
		if(idx != -1) {
			contador.set(idx, contador.get(idx)+1);
		} else {
			palavras.add(word);
			contador.add(1);
		}
		
		if((idx % 10) == 0) {
			System.out.println(palavras.toString());
			System.out.println(contador.toString());
		}
	}


	/*
	 * M�todo chamado pela plataforma. Aqui devemos declarar os nomes dos campos que esse bolt emite.
	 * Como esse bolt est� no fim da topologia, ele nao emite nada (logo, nao � preciso declarar nenhum campo). 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
