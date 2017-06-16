package br.com.chaos.auxiliarestudos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TecnicasEstudoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnicas_estudo);

        View botaoMemorizacao = findViewById(R.id.tecnicas_botao_memorizacao);
        botaoMemorizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TecnicasEstudoActivity.this, RevisoesActivity.class);
                startActivity(intent);
            }
        });
    }
}
