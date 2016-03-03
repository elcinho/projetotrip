package libelulati.tripctrl.Notificacoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Cnotificacoes_DAO extends Dados{
    public Cnotificacoes_DAO(Context context) {
        super(context);
    }

    public List<Cnotificacoes> listar(int id_usuario){
        List<Cnotificacoes> listarregistros = new ArrayList<Cnotificacoes>();
        String sql = ComandosSql.getSelectCnotificacoesUs() + id_usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int cn_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                int us_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
                int cn_ativar = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnAtivar())));
                int cn_todos = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnTodas())));
                int cn_viagens = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnViagens())));
                int cn_pagamentos = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnPagamento())));
                int cn_planejamentos = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnPlanejamento())));
                int cn_gastos = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getCnGastos())));

                Cnotificacoes cnotificacoes = new Cnotificacoes(cn_id, us_id, cn_ativar, cn_todos, cn_viagens, cn_pagamentos, cn_planejamentos, cn_gastos);

                listarregistros.add(cnotificacoes);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public void atualizar (Cnotificacoes cnotificacoes, int id){

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), cnotificacoes.getUs_id());
        values.put(Nomes.getCnAtivar(), cnotificacoes.getCn_ativar());
        values.put(Nomes.getCnTodas(), cnotificacoes.getCn_todas());
        values.put(Nomes.getCnViagens(), cnotificacoes.getCn_viagens());
        values.put(Nomes.getCnPagamento(), cnotificacoes.getCn_pagamentos());
        values.put(Nomes.getCnPlanejamento(), cnotificacoes.getCn_planejamentos());
        values.put(Nomes.getCnGastos(), cnotificacoes.getCn_gastos());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(Nomes.getTabelaCnotificacao(), values, where, whereArgs);
        db.close();
    }
}
