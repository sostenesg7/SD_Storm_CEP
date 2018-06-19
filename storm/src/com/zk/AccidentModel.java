package com.zk;

import java.io.Serializable;
import java.util.ArrayList;

public class AccidentModel extends ArrayList<AccidentModel.Container> {
	
    public class Container implements Serializable {
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


        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getSituacao() {
            return situacao;
        }

        public void setSituacao(String situacao) {
            this.situacao = situacao;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getHora() {
            return hora;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }

        public String getNatureza() {
            return natureza;
        }

        public void setNatureza(String natureza) {
            this.natureza = natureza;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public String getVitimas() {
            return vitimas;
        }

        public void setVitimas(String vitimas) {
            this.vitimas = vitimas;
        }
    }

}
