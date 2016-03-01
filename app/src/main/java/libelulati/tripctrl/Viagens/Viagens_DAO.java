package libelulati.tripctrl.Viagens;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;

public class Viagens_DAO extends Dados{

    public Viagens_DAO(Context context) {
        super(context);
    }

    public boolean criar(Viagem viagem){

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), viagem.getUs_id());
        values.put(Nomes.getViNome(), viagem.getVi_nome());
        values.put(Nomes.getViDtini(), viagem.getVi_dtinic());
        values.put(Nomes.getViDtfim(), viagem.getVi_dtfim());
        values.put(Nomes.getViValortotal(), viagem.getVi_valor());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(Nomes.getTabelaViagens(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Viagem> listar(int usuario) {

        List<Viagem> listaRegistros = new ArrayList<Viagem>();
        String sql = ComandosSql.getSelectViagensUs() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String vi_nome = cursor.getString(cursor.getColumnIndex(Nomes.getViNome()));
                String vi_dtini = cursor.getString(cursor.getColumnIndex(Nomes.getViDtini()));
                String vi_dtfim = cursor.getString(cursor.getColumnIndex(Nomes.getViDtfim()));
                String vi_valor = cursor.getString(cursor.getColumnIndex(Nomes.getViValortotal()));

                Viagem viagem = new Viagem(vi_id, vi_nome, vi_dtini, vi_dtfim, vi_valor);

                listaRegistros.add(viagem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaRegistros;
    }

    public Viagem buscarID(int id) {

        Viagem viagem = null;

        String sql = ComandosSql.getSelectIdViagem() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int us_id = InicioActivity.getId_usuario();
            String vi_nome = cursor.getString(cursor.getColumnIndex(Nomes.getViNome()));
            String vi_dtinicio = cursor.getString(cursor.getColumnIndex(Nomes.getViDtini()));
            String vi_dtfim = cursor.getString(cursor.getColumnIndex(Nomes.getViDtfim()));
            String vi_valortotal = cursor.getString(cursor.getColumnIndex(Nomes.getViValortotal()));

            viagem = new Viagem();
            viagem.setVi_id(vi_id);
            viagem.setUs_id(us_id);
            viagem.setVi_nome(vi_nome);
            viagem.setVi_dtinic(vi_dtinicio);
            viagem.setVi_dtfim(vi_dtfim);
            viagem.setVi_valor(vi_valortotal);
        }

        cursor.close();
        db.close();

        return viagem;
    }
}
