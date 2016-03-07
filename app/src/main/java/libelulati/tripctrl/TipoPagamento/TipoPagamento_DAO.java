package libelulati.tripctrl.TipoPagamento;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class TipoPagamento_DAO extends Dados{
    public TipoPagamento_DAO(Context context) {
        super(context);
    }

    public boolean criar(TipoPagamento tipoPagamento){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), tipoPagamento.getUs_id());
        values.put(Nomes.getTpNome(), tipoPagamento.getTp_nome());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.insert(Nomes.getTabelaTipospagamento(), null, values) > 0;

        db.close();
        return sucesso;
    }

    public List<TipoPagamento> listar(int usuario){
        List<TipoPagamento> listatregistros = new ArrayList<TipoPagamento>();
        String sql = ComandosSql.getSelectTipospagamentoUs() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int tp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String us_id = cursor.getString(cursor.getColumnIndex(Nomes.getUsId()));
                String tp_nome = cursor.getString(cursor.getColumnIndex(Nomes.getTpNome()));

                if(us_id ==null){
                    us_id = "0";
                }

                TipoPagamento tipoPagamento = new TipoPagamento(tp_id, Integer.parseInt(us_id), tp_nome);
                listatregistros.add(tipoPagamento);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listatregistros;
    }

    public boolean atualizar(TipoPagamento tipoPagamento, int id){
        ContentValues values = new ContentValues();
        values.put(Nomes.getUsId(), tipoPagamento.getUs_id());
        values.put(Nomes.getTpNome(), tipoPagamento.getTp_nome());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.update(Nomes.getTabelaTipospagamento(), values, where, whereArgs) > 0;

        db.close();
        return sucesso;
    }

    public boolean deletar (int id){
        String sql = Nomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.delete(Nomes.getTabelaTipospagamento(), sql, null) > 0;

        db.close();

        return sucesso;
    }
}
