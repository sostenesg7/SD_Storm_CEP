package com.cep;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Main {

    public static void main(String[] args) {
        try {
            // Cria uma configuração
            Configuration cepConfig = new Configuration();

            // Registra os objetos que o engenho CEP vai tratar
            cepConfig.addEventType("produto", Produto.class.getName());

            // Configura o engine com seu config
            EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);

            // Registra a consulta via CEP Administrator
            EPAdministrator cepAdm = cep.getEPAdministrator();
            //EPStatement cepStatement = cepAdm.createEPL("select * from produto");

            EPStatement cepStatement = cepAdm.createPattern(
                    "every (padrao=" + "produto" + "(descricao='Democrito' or descricao='Patricia' ))");

            //Adiciona o listener para receber eventos do engenho
            cepStatement.addListener(new CEPListener());

            // Pega a instancia do engenho/core para associar ao Stream e injetar os dados
            EPRuntime cepRT = cep.getEPRuntime();



            Thread t = new ThreadEnviarEventos(cepRT);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
