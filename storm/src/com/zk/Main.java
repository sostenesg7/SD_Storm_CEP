package com.zk;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Essa classe contem apenas o m�todo main. Esse m�todo poderia estar em qualquer classe, 
 * mas para melhor organiza��o e separa��o do c�digo, colocamos em uma classe separada dos spouts e bolts.
 * 
 * Lembrando que para esse c�digo funcionar, devemos importar as Libs do Storm.
 * Em sala de aula utilizamos a vers�o 1.0.2
 */
public class Main {
	public static void main(String[] args) {
		
		//Criando uma configura�� para a topologia. Mesmo que nenhum par�metro seja passado, devemos criar uma config vazia.
		Config config = new Config();
		
		//Builder da topologia para auxiliar na declara��o dos spouts e bolts, e nos links entre esses. 
		TopologyBuilder builder = new TopologyBuilder();
		
		//Para cada compomente, devemos passar uma inst�ncia e um label/r�tulo.
		builder.setSpout("accident-spout", new AccidentSpout());

		builder.setBolt("selectAccident-bolt", new SelectAccidentBolt()).shuffleGrouping("accident-spout");

		builder.setSpout("infringement-spout", new InfringementSpout());

		builder.setBolt("selectInfringement-bolt", new SelectInfringementBolt()).shuffleGrouping("infringement-spout");
		
		builder.setBolt("cep-bolt", new CepBolt(),2).shuffleGrouping("selectInfringement-bolt").shuffleGrouping("selectAccident-bolt");
		
		//Como n�o estamos trabalhando com o ambiente em produ��o, e sim localmente, devemos "simular" um storm atrav�s da classe LocalCluster.
		//Uma vez criado, podemos submeter a nossa topologia para esse cluster local.
		//Para ambientes em produ��o, a chamada � um pouco diferente. Veremos na pr�xima aula.

        config.setNumWorkers(2);
        //config.setMaxSpoutPending(5000);

        LocalCluster local = new LocalCluster();
		local.submitTopology("noiz2", config, builder.createTopology());

		/*try {
			StormSubmitter.submitTopology("noiz2", config, builder.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
			e.printStackTrace();
		}*/
	}

}
