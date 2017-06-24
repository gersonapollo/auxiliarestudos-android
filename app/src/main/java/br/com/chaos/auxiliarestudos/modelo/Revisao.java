package br.com.chaos.auxiliarestudos.modelo;

import java.util.Date;

/**
 * Created by gersonapollo on 16/06/17.
 */

public class Revisao {

    private long id;
    private Date dataCriacao;
    private String titulo;
    private String notas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return this.getId() +" - "+ this.getTitulo();
    }
}
