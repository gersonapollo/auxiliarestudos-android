package br.com.chaos.auxiliarestudos;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.chaos.auxiliarestudos.dao.RevisaoDao;
import br.com.chaos.auxiliarestudos.helper.FormularioRevisaoHelper;
import br.com.chaos.auxiliarestudos.modelo.Revisao;

public class RevisoesActivity extends AppCompatActivity {

    private ListView listaRevisoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revisoes);
        setTitle("Revisões");

        listaRevisoes = (ListView) findViewById(R.id.revisoes_lista);

        listaRevisoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View view, int position, long id) {
                Revisao revisao = (Revisao) listaRevisoes.getItemAtPosition(position);

                Intent intent = new Intent(RevisoesActivity.this, FormularioRevisaoActivity.class);
                intent.putExtra("revisao", revisao);
                startActivity(intent);

            }
        });

        Button botao_adicionar = (Button) findViewById(R.id.revisoes_botao_adicionar);
        botao_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevisoesActivity.this, FormularioRevisaoActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaRevisoes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        RevisaoDao dao = new RevisaoDao(this);
        List<Revisao> revisoes = dao.listarRevisoes();

        ArrayAdapter<Revisao> revisoesAdapter = new ArrayAdapter<Revisao>(this, android.R.layout.simple_list_item_1, revisoes);
        listaRevisoes.setAdapter(revisoesAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info  = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Revisao revisao = (Revisao) listaRevisoes.getItemAtPosition(info.position);


        MenuItem excluir = menu.add("Excluir");
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RevisaoDao dao = new RevisaoDao(RevisoesActivity.this);
                dao.excluir(revisao);
                dao.close();

                carregarLista();
                return false;
            }
        });

    }

    private void gerarNotificacao(){
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_confirmar)
                .setContentTitle("Minha Notificacao")
                .setContentText("Teste de Notificacao");

        Intent resultIntent = new Intent(this, FormularioRevisaoActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(FormularioRevisaoActivity.class);

        taskStackBuilder.addNextIntent(resultIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder.setVibrate(new long[] {100, 250, 100, 500});

        notificationManager.notify(0, notificationBuilder.build());


    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.revisoes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_adicionar:
                gerarNotificacao();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
