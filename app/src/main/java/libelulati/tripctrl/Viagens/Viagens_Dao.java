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

public class Viagens_Dao extends Dados{
    public Viagens_Dao(Context context) {
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
        String sql = ComandosSql.getSelectViagens() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String vi_nome = cursor.getString(cursor.getColumnIndex(Nomes.getViNome()));
                String vi_dtini = cursor.getString(cursor.getColumnIndex(Nomes.getViDtini()));
                String vi_dtfim = cursor.getString(cursor.getColumnIndex(Nomes.getViDtfim()));
                String vi_valor = cursor.getString(cursor.getColumnIndex(Nomes.getViValortotal()));

                Viagem viagens = new Viagem(vi_id, vi_nome, vi_dtini, vi_dtfim, vi_valor);

                listaRegistros.add(viagens);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaRegistros;
    }

}
