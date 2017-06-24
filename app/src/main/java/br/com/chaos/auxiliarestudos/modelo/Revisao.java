package br.com.chaos.auxiliarestudos.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gersonapollo on 16/06/17.
 */

public class Revisao implements Serializable{


    private Long id;
    private Date dataCriacao;
    private String titulo;
    private String notas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
