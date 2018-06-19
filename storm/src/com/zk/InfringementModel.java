package com.zk;

import java.io.Serializable;
import java.util.ArrayList;

public class InfringementModel extends ArrayList<InfringementModel.Container> {
	
    public class Container implements Serializable {
    	public String datainfracao;
        public String horainfracao;
        public String dataimplantacao;
        public String agenteequipamento;
        public String infracao;
        public String descricaoinfracao;
        public String amparolegal;
        public String localcometimento;
        //public Object object;


        public String getDatainfracao() {
            return datainfracao;
        }

        public void setDatainfracao(String datainfracao) {
            this.datainfracao = datainfracao;
        }

        public String getHorainfracao() {
            return horainfracao;
        }

        public void setHorainfracao(String horainfracao) {
            this.horainfracao = horainfracao;
        }

        public String getDataimplantacao() {
            return dataimplantacao;
        }

        public void setDataimplantacao(String dataimplantacao) {
            this.dataimplantacao = dataimplantacao;
        }

        public String getAgenteequipamento() {
            return agenteequipamento;
        }

        public void setAgenteequipamento(String agenteequipamento) {
            this.agenteequipamento = agenteequipamento;
        }

        public String getInfracao() {
            return infracao;
        }

        public void setInfracao(String infracao) {
            this.infracao = infracao;
        }

        public String getDescricaoinfracao() {
            return descricaoinfracao;
        }

        public void setDescricaoinfracao(String descricaoinfracao) {
            this.descricaoinfracao = descricaoinfracao;
        }

        public String getAmparolegal() {
            return amparolegal;
        }

        public void setAmparolegal(String amparolegal) {
            this.amparolegal = amparolegal;
        }

        public String getLocalcometimento() {
            return localcometimento;
        }

        public void setLocalcometimento(String localcometimento) {
            this.localcometimento = localcometimento;
        }
    }

}
