package br.com.chaos.auxiliarestudos.helper;

import android.view.View;
import android.widget.EditText;

import br.com.chaos.auxiliarestudos.FormularioRevisaoActivity;
import br.com.chaos.auxiliarestudos.R;
import br.com.chaos.auxiliarestudos.modelo.Revisao;

/**
 * Created by gersonapollo on 17/06/17.
 */

public class FormularioRevisaoHelper {

    private final EditText titulo;
    private final EditText notas;

    private Revisao revisao;

    public FormularioRevisaoHelper(FormularioRevisaoActivity activity) {

        titulo = (EditText) activity.findViewById(R.id.formulario_titulo);
        notas = (EditText) activity.findViewById(R.id.formulario_titulo);
        revisao = new Revisao();
    }

    public Revisao getRevisao(){

        revisao.setTitulo(this.titulo.getText().toString());
        revisao.setNotas(this.notas.getText().toString());

        return revisao;
    }
}
