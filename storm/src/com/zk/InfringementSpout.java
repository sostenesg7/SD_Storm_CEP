package com.zk;

import java.io.*;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import com.google.gson.Gson;

public class InfringementSpout implements IRichSpout {
	
	private static final long serialVersionUID = 1L;
	
	//Atributo que guarda a referencia do OutputSpout para usar no metodo nextTuple()
	private SpoutOutputCollector collector;
	
	private TopologyContext context;

	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo � chamado pela plataforma/storm no in�cio da topologia.
	 */
	@Override
	public void open(Map arg0, TopologyContext context, SpoutOutputCollector arg2) {
		this.collector = arg2;
		this.context = context;
	}

	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo � chamado continuamente pela plataforma. 
	 */
	@Override
	public void nextTuple() {
		System.out.println("---------------------");
		System.out.println("AccidentSpout --> nextTuple");
		
		System.out.println("THIS TASK SPOUT ID ==>> " + this.context.getThisTaskId());
		
		try {
			Reader reader = new InputStreamReader(new FileInputStream(new File("/home/infracoes.json").getAbsolutePath()), "UTF-8");
			//Reader reader = new InputStreamReader(Main.class.getResourceAsStream("../../infracoes.json"), "UTF-8");
			Gson gson = new Gson();
			InfringementModel model = gson.fromJson(reader, InfringementModel.class);
			for (InfringementModel.Container container : model) {
				if (container != null) { 
					String innerJson = gson.toJson(container);
					   // System.out.println(container.situacao);
					//eMITIR COM O OBJETO
					    this.collector.emit(new Values(innerJson));
					    Thread.sleep(10);
				}
			}
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/*
	 * Aqui estamos implementando um m�todo do IRichSpout. 
	 * Esse m�todo declara os nomes dos campos que o Spout vai enviar para os bolts 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("infrigement"));
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
