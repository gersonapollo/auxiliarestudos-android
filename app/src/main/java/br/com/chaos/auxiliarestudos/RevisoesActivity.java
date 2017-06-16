package br.com.chaos.auxiliarestudos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class RevisoesActivity extends AppCompatActivity {

    private ListView listaRevisoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisoes);
        setTitle("Revisões");



        listaRevisoes = (ListView) findViewById(R.id.revisoes_lista);
        String[] revisoes = {"Android", "Spring"};

        ArrayAdapter revisoesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, revisoes);
        listaRevisoes.setAdapter(revisoesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.revisoes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
