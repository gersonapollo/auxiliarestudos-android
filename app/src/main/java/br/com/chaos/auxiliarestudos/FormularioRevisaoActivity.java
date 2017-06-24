package br.com.chaos.auxiliarestudos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.Date;

import br.com.chaos.auxiliarestudos.dao.RevisaoDao;
import br.com.chaos.auxiliarestudos.helper.FormularioRevisaoHelper;
import br.com.chaos.auxiliarestudos.modelo.Revisao;

public class FormularioRevisaoActivity extends AppCompatActivity {

    private FormularioRevisaoHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_revisao);
        setTitle("Cadastrar Assunto");

        helper = new FormularioRevisaoHelper(this);

        Revisao revisao = (Revisao) getIntent().getSerializableExtra("revisao");
        if(revisao != null){
            helper.preencherRevisao(revisao);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.formulario_revisao_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_confirmar:
                Revisao revisao = helper.getRevisao();
                revisao.setDataCriacao(new Date(System.currentTimeMillis()));
                RevisaoDao dao = new RevisaoDao(this);
                if(null != revisao.getId()){
                    dao.atualizar(revisao);
                }else{
                    dao.cadastrar(revisao);
                }


                dao.close();
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
