package com.zk;

import java.util.ArrayList;

public class AccidentModel extends ArrayList<AccidentModel.Container> {
	
    public class Container {
    	public String tipo;
        public String situacao;
        public String data;
        public String hora;
        public String bairro;
        public String endereco;
        public String numero;
        public String complemento;
        public String natureza;
        public String descricao;
        public String vitimas;
        //public Object object;
    }

}
