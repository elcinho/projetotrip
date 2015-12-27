package libelulati.tripctrl.MetodosPagamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.BancoDados.BancoDados;
import libelulati.tripctrl.BancoDados.DBSelects;
import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Inicio.InicioActivity;


/**
 * Created by elcinho on 10/12/2015.
 */
public class MetodosPagamentoDAO extends BancoDados {

    public MetodosPagamentoDAO(Context context) {
        super(context);
    }


    public boolean criar(MetodosPagamento mp) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViId(), mp.getDv_id());
        values.put(StringsNomes.getTpId(), mp.getTp_id());
        values.put(StringsNomes.getMeDetalhe(), mp.getMe_detalhes());
        values.put(StringsNomes.getMeValor(), mp.getMe_valor());
        values.put(StringsNomes.getMeVencimento(), mp.getMe_vencimento());


        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaMetodospagamento(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<MetodosPagamento> listar() {

        List<MetodosPagamento> listaRegistros = new ArrayList<MetodosPagamento>();
        String sql = DBSelects.getSelecionarListaMetodosPagamento();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int me_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getMeId())));
                int dv_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getViId())));
                int tp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getTpId())));
                String me_detalhe = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeDetalhe()));
                String me_valor = cursor.getString((cursor.getColumnIndex(StringsNomes.getMeValor())));


                MetodosPagamento mp = new MetodosPagamento(me_id, dv_id, tp_id, me_detalhe, me_valor);

                listaRegistros.add(mp);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaRegistros;
    }


    public MetodosPagamento buscarID(int id) {

        MetodosPagamento mp = null;

        String sql = DBSelects.getSelecionarIdMetodosPagamento() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int me_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            int us_id = InicioActivity.getId_uslogado();
            int dv_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getMeId())));
            int tp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getTpId())));
            String me_detalhe = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeDetalhe()));
            int me_valor = Integer.parseInt(cursor.getString((cursor.getColumnIndex(StringsNomes.getMeValor()))));
            String me_vencimento = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeVencimento()));

            mp = new MetodosPagamento();
            mp.setMe_id(me_id);
            mp.setUs_id(us_id);//estrangeira usuario
            mp.setDv_id(dv_id);//destrangeira viagem
            mp.setTp_id(tp_id);// estragneira tipo de pagamento
            mp.setMe_detalhes(me_detalhe);
            mp.setMe_valor(me_valor);
            mp.setMe_vencimento(me_vencimento);

        }

        cursor.close();
        db.close();

        return mp;
    }

    public boolean atualizar(MetodosPagamento mp, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViId(), mp.getDv_id());
        values.put(StringsNomes.getTpId(), mp.getTp_id());
        values.put(StringsNomes.getMeDetalhe(), mp.getMe_detalhes());
        values.put(StringsNomes.getMeValor(), mp.getMe_valor());
        values.put(StringsNomes.getMeVencimento(), mp.getMe_vencimento());

        String where = DBSelects.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(StringsNomes.getTabelaMetodospagamento(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;

    }

    public boolean deletar(int id) {
        boolean sucesso = false;

        String sql = StringsNomes.getID() + " where = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(StringsNomes.getTabelaMetodospagamento(), sql, null) > 0;
        db.close();

        return sucesso;
    }

    public List SpinerUsuario(){
        SQLiteDatabase db = getReadableDatabase();
        String resultado="";
        List<String> listaUsuario = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select _id, us_nome from " + StringsNomes.getTabelaUsuarios(), null);
        while(cursor.moveToNext()){
            listaUsuario.add(cursor.getString(0)+" "+cursor.getString(1));
        }
        cursor.close();
        db.close();
        return (listaUsuario);
    }
    public List SpinerTipoPagamento()    {
        SQLiteDatabase db = getReadableDatabase();
        String resultado="";
        List<String> listaTipoPagamento = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select _id, tp_nome from " + StringsNomes.getTabelaTipospagamento()  , null);
        while(cursor.moveToNext()){
            listaTipoPagamento.add(cursor.getString(0)+" "+cursor.getString(1));
        }
        cursor.close();
        db.close();
        return (listaTipoPagamento);
    }

    public List SpinerViagem(){
        SQLiteDatabase db = getReadableDatabase();
        String resultado= "";
        List<String> listaViagem = new ArrayList<String>();
            Cursor cursor = db.rawQuery("select _id, vi_nome from " + StringsNomes.getTabelaViagens(), null);
            while(cursor.moveToNext()){

            listaViagem.add(cursor.getString(0) + " " + cursor.getString(1));
        }
        cursor.close();
        db.close();
        return (listaViagem);
    }
}