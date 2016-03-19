package libelulati.tripctrl.Inicio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Totais_DAO extends Dados{
    public Totais_DAO(Context context) {
        super(context);
    }

    public void criar(Totais totais){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), totais.getUs_id());
        values.put(Nomes.getViId(), totais.getVi_id());
        values.put(Nomes.getToNome(), totais.getTo_nome());
        values.put(Nomes.getToTotal(), totais.getTo_total());
        values.put(Nomes.getToGasto(), totais.getTo_gasto());
        values.put(Nomes.getToPlanejamento(), totais.getTo_planejamento());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Nomes.getTabelaTotais(), null, values);
        db.close();
    }

    public Totais buscarNome(String nome){
        Totais totais = null;

        String sql = ComandosSql.getSelectNomeTotais() + "'" + nome + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int to_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int us_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getViId())));
            String to_nome = cursor.getString(cursor.getColumnIndex(Nomes.getToNome()));
            String to_total = cursor.getString(cursor.getColumnIndex(Nomes.getToTotal()));
            String to_gasto = cursor.getString(cursor.getColumnIndex(Nomes.getToGasto()));
            String to_planejamento = cursor.getString(cursor.getColumnIndex(Nomes.getToPlanejamento()));

            totais = new Totais(to_id, us_id, vi_id, to_nome, to_total, to_gasto, to_planejamento);
        }

        cursor.close();
        db.close();

        return totais;
    }

    public List<Totais> listar(int viagem){
        List<Totais> listarregistros = new ArrayList<Totais>();
        String sql = ComandosSql.getSelectTotaisVi() + viagem;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int to_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                int us_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
                int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getViId())));
                String to_nome = cursor.getString(cursor.getColumnIndex(Nomes.getToNome()));
                String to_total = cursor.getString(cursor.getColumnIndex(Nomes.getToTotal()));
                String to_gasto = cursor.getString(cursor.getColumnIndex(Nomes.getToGasto()));
                String to_planejamento = cursor.getString(cursor.getColumnIndex(Nomes.getToPlanejamento()));

                Totais totais = new Totais(to_id, us_id, vi_id, to_nome, to_total, to_gasto, to_planejamento);

                listarregistros.add(totais);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public void atualizar(Totais totais, String nome){

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), totais.getUs_id());
        values.put(Nomes.getViId(), totais.getVi_id());
        values.put(Nomes.getToNome(), totais.getTo_nome());
        values.put(Nomes.getToTotal(), totais.getTo_total());
        values.put(Nomes.getToGasto(), totais.getTo_gasto());
        values.put(Nomes.getToPlanejamento(), totais.getTo_planejamento());

        String where = " to_nome = ?";
        String[] whereArgs = {nome};

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Nomes.getTabelaTotais(), values, where, whereArgs);

        db.close();
    }

}
