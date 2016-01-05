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


    public boolean criar(MetodosPagamento metodospagamento) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViId(), metodospagamento.getDv_id());
        values.put(StringsNomes.getTpId(), metodospagamento.getTp_id());
        values.put(StringsNomes.getMeDetalhe(), metodospagamento.getMe_detalhes());
        values.put(StringsNomes.getMeValor(), metodospagamento.getMe_valor());
        values.put(StringsNomes.getMeVencimento(), metodospagamento.getMe_vencimento());


        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaMetodospagamento(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<MetodosPagamento> listar() {

        List<MetodosPagamento> listaRegistros = new ArrayList<MetodosPagamento>();
        String sql = DBSelects.getSelecionarTodosMetodosPagamento();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int dv_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getViId())));
                int tp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getTpId())));
                float me_valor = Integer.parseInt(cursor.getString((cursor.getColumnIndex(StringsNomes.getMeValor()))));
                String me_vencimento = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeVencimento()));


                MetodosPagamento metodospagamento = new MetodosPagamento(dv_id, tp_id, me_valor, me_vencimento);

                listaRegistros.add(metodospagamento);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaRegistros;
    }


    public MetodosPagamento buscarID(int id) {

        MetodosPagamento metodosPagamento = null;

        String sql = DBSelects.getSelecionarIdMetodosPagamento() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int me_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            int dv_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getMeId())));
            int tp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getTpId())));
            String me_detalhe = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeDetalhe()));
            float me_valor = Integer.parseInt(cursor.getString((cursor.getColumnIndex(StringsNomes.getMeValor()))));
            String me_vencimento = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeVencimento()));

            metodosPagamento = new MetodosPagamento();

            metodosPagamento.setMe_id(me_id);
            metodosPagamento.setDv_id(dv_id);//destrangeira viagem
            metodosPagamento.setTp_id(tp_id);// estragneira tipo de pagamento
            metodosPagamento.setMe_detalhes(me_detalhe);
            metodosPagamento.setMe_valor(me_valor);
            metodosPagamento.setMe_vencimento(me_vencimento);

        }

        cursor.close();
        db.close();

        return metodosPagamento;
    }

    public boolean atualizar(MetodosPagamento metodosPagamento, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViId(), metodosPagamento.getDv_id());
        values.put(StringsNomes.getTpId(), metodosPagamento.getTp_id());
        values.put(StringsNomes.getMeDetalhe(), metodosPagamento.getMe_detalhes());
        values.put(StringsNomes.getMeValor(), metodosPagamento.getMe_valor());
        values.put(StringsNomes.getMeVencimento(), metodosPagamento.getMe_vencimento());

        String where = DBSelects.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(StringsNomes.getTabelaMetodospagamento(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;

    }

    public boolean deletar(int id) {
        boolean sucesso = false;

        String sql = StringsNomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(StringsNomes.getTabelaMetodospagamento(), sql, null) > 0;
        db.close();

        return sucesso;
    }

    public List SpinerTipoPagamento() {
        List<String> listaTipoPagamento = new ArrayList<String>();
        String sql = DBSelects.getSelecionarTodosTipoPagamento();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {

            int indice = cursor.getColumnIndex(StringsNomes.getID());
            do {
                listaTipoPagamento.add(cursor.getString(indice));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaTipoPagamento;
    }

    public List SpinerViagem() {
        List<String> listaViagem = new ArrayList<String>();
        String sql = DBSelects.getSelecionarTodosViagens();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToNext()) {

            int indice = cursor.getColumnIndex(StringsNomes.getID());
            do {
                listaViagem.add(cursor.getString(indice));

            } while (cursor.moveToNext());
        }

            cursor.close();
            db.close();
            return listaViagem;
        }
    }


