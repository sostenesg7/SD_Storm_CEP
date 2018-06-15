package com.zk;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/** 
 * Devemos implementar a classe IRichBolt para criarmos um Bolt na plataforma Storm
 * 
 * Lembrando que para esse c�digo funcionar, devemos importar as Libs do Storm.
 * Em sala de aula utilizamos a vers�o 1.0.2
 */ 
public class SplitterBolt implements IRichBolt {

	private static final long serialVersionUID = 1L;

	//Atributo que guarda a referencia do OutputCollector para usar no metodo execute().
	private OutputCollector collector;
	
	/*
	 * M�todo chamado pela plataforma. 
	 * Como no m�todo execute vamos emitir dados, � preciso guardar a refer�ncia do objeto OutputCollector.
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
	}


	/*
	 * M�todo chamado pela plataforma/storm. A cada nova tupla (registro do stream) esse m�todo � chamado.
	 * � c�digo abaixo divide a linha em palavras, e emite uma a uma para que o pr�ximo Bolt possa contar.
	 */
	@Override
	public void execute(Tuple input) {
		String line = input.getStringByField("linha");
		
		String[] words = line.split(" ");
		for(String word: words){
			if(!word.isEmpty()) {
				this.collector.emit(new Values(word));
			}
		}
		
		
		
	}

	/*
	 * M�todo chamado pela plataforma. Aqui devemos declarar os nomes dos campos que esse bolt emite.
	 * Esse campo emite apenas um campo (que representa a palavra que ser� contada).
	 * Logo, vamos declarar apenas esse campo. 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("palavra"));
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
