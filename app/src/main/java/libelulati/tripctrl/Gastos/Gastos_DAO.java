package libelulati.tripctrl.Gastos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;

public class Gastos_DAO extends Dados{

    public Gastos_DAO(Context context) {
        super(context);
    }

    public boolean criar(Gasto gasto){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), gasto.getUs_id());
        values.put(Nomes.getViId(), gasto.getVi_id());
        values.put(Nomes.getCaId(), gasto.getCa_id());
        values.put(Nomes.getPaId(), gasto.getPa_id());
        values.put(Nomes.getGaDescricao(), gasto.getGa_descricao());
        values.put(Nomes.getGaValor(), gasto.getGa_valor());
        values.put(Nomes.getGaData(), gasto.getGa_data());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(Nomes.getTabelaGastos(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Gasto> listar(int viagem){
        List<Gasto> listarregistros = new ArrayList<Gasto>();
        String sql = ComandosSql.getSelectGastos() + viagem;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int ga_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String ga_descricao = cursor.getString(cursor.getColumnIndex(Nomes.getGaDescricao()));
                String ga_valor = cursor.getString(cursor.getColumnIndex(Nomes.getGaValor()));
                String ga_data = cursor.getString(cursor.getColumnIndex(Nomes.getGaData()));
                String ga_categoria = cursor.getString(cursor.getColumnIndex(Nomes.getCaNome()));
                String ga_pagamento = cursor.getString(cursor.getColumnIndex(Nomes.getPaId()));

                Gasto gasto = new Gasto(ga_id, ga_descricao, ga_valor, ga_data, ga_categoria, ga_pagamento);

                listarregistros.add(gasto);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public Gasto buscarID(int id){
        Gasto gasto = null;

        String sql = ComandosSql.getSelectIdGasto() + id;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int ga_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int us_is = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getViId())));
            String ca_id = cursor.getString(cursor.getColumnIndex(Nomes.getCaId()));
            String pa_id = cursor.getString(cursor.getColumnIndex(Nomes.getPaId()));
            String ga_descricao = cursor.getString(cursor.getColumnIndex(Nomes.getGaDescricao()));
            String ga_data = cursor.getString(cursor.getColumnIndex(Nomes.getGaData()));
            String ga_valor = cursor.getString(cursor.getColumnIndex(Nomes.getGaValor()));

            gasto = new Gasto();
            gasto.setGa_id(ga_id);
            gasto.setVi_id(vi_id);
            gasto.setUs_id(us_is);
            gasto.setCa_id(ca_id);
            gasto.setPa_id(pa_id);
            gasto.setGa_descricao(ga_descricao);
            gasto.setGa_data(ga_data);
            gasto.setGa_valor(ga_valor);
        }

        cursor.close();
        db.close();

        return gasto;
    }

    public boolean atualizar(Gasto gasto, int id){
        return false;
    }

    public boolean deletar(int id){
        boolean sucesso = false;

        String sql = Nomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(Nomes.getTabelaGastos(), sql, null) > 0;
        db.close();

        return sucesso;
    }
}
