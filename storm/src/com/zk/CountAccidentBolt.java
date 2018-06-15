package com.zk;

import java.util.ArrayList;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import com.google.gson.Gson;

public class CountAccidentBolt implements IRichBolt {
	
	private static final long serialVersionUID = 1L;
	
	//Estruturas de dados usadas para contar as palavras (sugestï¿½o do gaï¿½cho Pï¿½ricles).
	private int counter;

	/*
	 * Mï¿½todo chamado pela plataforma. Como este bolt estï¿½ localizado no final da nossa topologia, 
	 * nï¿½o ï¿½ preciso guardar referï¿½ncia para os objetos passados como parï¿½metro.
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
	}
	
	/*
	 * Mï¿½todo chamado pela plataforma/storm. A cada nova tupla (registro do stream) esse mï¿½todo ï¿½ chamado.
	 * Aqui temos um simples cï¿½digo utilizado para contar e imprimir os dados sumarizados.
	 */
	@Override
	public void execute(Tuple input) {
		System.out.println("CountBolt --> execute");

		String word = input.getString(0);
		
		Gson gson = new Gson();
		
		AccidentModel.Container model = gson.fromJson(word, AccidentModel.Container.class);
		
		if (Integer.parseInt(model.vitimas) > 0) {
			this.counter++;
		}
		
		System.out.println("TOTAL DE VÍTIMAS => " + this.counter);
	}


	/*
	 * Mï¿½todo chamado pela plataforma. Aqui devemos declarar os nomes dos campos que esse bolt emite.
	 * Como esse bolt estï¿½ no fim da topologia, ele nao emite nada (logo, nao ï¿½ preciso declarar nenhum campo). 
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
