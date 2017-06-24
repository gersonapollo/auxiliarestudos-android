package br.com.chaos.auxiliarestudos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.chaos.auxiliarestudos.modelo.Revisao;

/**
 * Created by gersonapollo on 16/06/17.
 */

public class RevisaoDao extends SQLiteOpenHelper{

    public RevisaoDao(Context context) {
        super(context, "auxiliarestudos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Revisoes (id INTEGER PRIMARY KEY, titulo TEXT NOT NULL, notas TEXT, dataCriacao TEXT)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE Revisoes";
        db.execSQL(sql);
        onCreate(db);
    }

    public void cadastrar(Revisao revisao){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues content = pegaDadosRevisao(revisao);

        db.insert("Revisoes", null, content);
    }

    @NonNull
    private ContentValues pegaDadosRevisao(Revisao revisao) {
        ContentValues content = new ContentValues();

        content.put("titulo", revisao.getTitulo());
        content.put("notas", revisao.getNotas());
        content.put("dataCriacao", revisao.getDataCriacao().toString());
        return content;
    }

    public List<Revisao> listarRevisoes(){
        SQLiteDatabase db  = getReadableDatabase();

        String sql = "SELECT * FROM Revisoes";
        Cursor cursor = db.rawQuery(sql, null);
        List<Revisao> listaRevisoes = new ArrayList<>();
        while (cursor.moveToNext()){
            Revisao revisao = new Revisao();

            revisao.setId(cursor.getLong(cursor.getColumnIndex("id")));
            revisao.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            revisao.setNotas(cursor.getString(cursor.getColumnIndex("notas")));
            String data = cursor.getString(cursor.getColumnIndex("dataCriacao"));
            SimpleDateFormat format = new SimpleDateFormat("DD-MM-yyyy HH:MM:SS.SSSS");
            Date campoData = null;
            try {
                campoData = format.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            revisao.setDataCriacao(campoData);

            listaRevisoes.add(revisao);
        }
        cursor.close();
        return listaRevisoes;
    }

    public void excluir(Revisao revisao) {
        SQLiteDatabase db = getReadableDatabase();

        String[] argumentos = {String.valueOf(revisao.getId())};
        db.delete("Revisoes", "id=?", argumentos);
    }

    public void atualizar(Revisao revisao) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues content = pegaDadosRevisao(revisao);
        String[] argumentos = {revisao.getId().toString()};
        db.update("Revisoes", content, "id=?", argumentos);
    }
}
