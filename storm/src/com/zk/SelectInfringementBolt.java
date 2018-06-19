package com.zk;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import com.google.gson.Gson;

public class SelectInfringementBolt implements IRichBolt {
	
	private static final long serialVersionUID = 1L;
	
	private OutputCollector collector;
	
	private int filtroDeInfracoes[] = {  6050, 7455, 5380 };
	
	private TopologyContext context;
	
	//Estruturas de dados usadas para contar as palavras (sugest�o do ga�cho P�ricles).
	private int counter;

	/*
	 * M�todo chamado pela plataforma. Como este bolt est� localizado no final da nossa topologia, 
	 * n�o � preciso guardar refer�ncia para os objetos passados como par�metro.
	 */
	@Override
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		this.collector = arg2;
		this.context = arg1;
	}
	
	/*
	 * M�todo chamado pela plataforma/storm. A cada nova tupla (registro do stream) esse m�todo � chamado.
	 * Aqui temos um simples c�digo utilizado para contar e imprimir os dados sumarizados.
	 */
	@Override
	public void execute(Tuple input) {
		//System.out.println("SelectInfringementBolt --> execute");

		String word = input.getString(0);
		
		Gson gson = new Gson();
		
		InfringementModel.Container model = gson.fromJson(word, InfringementModel.Container.class);
		
		//System.out.println("THIS TASK BOUT ID ==>> " + this.context.getThisTaskId());
		
		if (model.infracao != null) {
			int infraTemp = Integer.parseInt(model.infracao);
			for (int i = 0; i < this.filtroDeInfracoes.length; i++) {
				if (infraTemp == this.filtroDeInfracoes[i]) {
					this.collector.emit(new Values(model));
                    //System.err.println("ERRR => " + model);
					//this.collector.emitDirect(4, new Values(word));
					break;
				}
			}
		}
		
	}


	/*
	 * M�todo chamado pela plataforma. Aqui devemos declarar os nomes dos campos que esse bolt emite.
	 * Como esse bolt est� no fim da topologia, ele nao emite nada (logo, nao � preciso declarar nenhum campo). 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("infracoes"));
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
