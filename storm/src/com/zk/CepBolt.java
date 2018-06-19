package com.zk;

import java.util.ArrayList;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.google.gson.Gson;

public class CepBolt implements IRichBolt {
	
	private static final long serialVersionUID = 1L;

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
		System.out.println("CepBolt --> execute");

		String word = input.getString(0);
		
		Gson gson = new Gson();
		
		System.out.println("IDDD ==>> " + input.getSourceTask());
		
		// processamento

		if (input.getSourceTask() == 6) {
			InfringementModel.Container model = gson.fromJson(word, InfringementModel.Container.class);
			System.out.println("<<< INFRA��O >>>");
		}
		else {
			AccidentModel.Container model = gson.fromJson(word, AccidentModel.Container.class);
			System.out.println("<<< ACIDENTE >>>");
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
