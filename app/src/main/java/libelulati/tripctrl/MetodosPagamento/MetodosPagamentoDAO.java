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
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagens;

public class MetodosPagamentoDAO extends BancoDados {
    Context context;

    public MetodosPagamentoDAO(Context context) {
        super(context);
        this.context = context;
    }

    public boolean criar(MetodosPagamento metodosPagamento){

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViId(), metodosPagamento.getVi_id());
        values.put(StringsNomes.getTpId(), metodosPagamento.getTp_id());
        values.put(StringsNomes.getMeDetalhe(), metodosPagamento.getMp_detalhe());
        values.put(StringsNomes.getMeVencimento(), metodosPagamento.getMp_dtvenc());
        values.put(StringsNomes.getMeValor(), metodosPagamento.getMp_valor());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaMetodospagamento(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<MetodosPagamento> listar(){

        List<MetodosPagamento> listaRegistros = new ArrayList<MetodosPagamento>();
        String sql = DBSelects.getSelecionarTodosMetodosPagamento();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int mp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
                String mp_detalhe = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeDetalhe()));
                String mp_dtvenc = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeVencimento()));
                String mp_valor = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeValor()));

                MetodosPagamento metodosPagamento = new MetodosPagamento(mp_id, mp_detalhe, mp_dtvenc, mp_valor);

                listaRegistros.add(metodosPagamento);
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
            int mp_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            int us_id = InicioActivity.getId_uslogado();
            String mp_detalhe = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeDetalhe()));
            String vi_id = cursor.getString(cursor.getColumnIndex(StringsNomes.getViId()));
            String tp_id = cursor.getString(cursor.getColumnIndex(StringsNomes.getTpId()));
            String mp_dtvenc = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeVencimento()));
            String mp_valor = cursor.getString(cursor.getColumnIndex(StringsNomes.getMeValor()));

            metodosPagamento = new MetodosPagamento();
            metodosPagamento.setMp_id(mp_id);
            metodosPagamento.setUs_id(us_id);
            metodosPagamento.setMp_detalhe(mp_detalhe);
            metodosPagamento.setVi_id(vi_id);
            metodosPagamento.setTp_id(tp_id);
            metodosPagamento.setMp_dtvenc(mp_dtvenc);
            metodosPagamento.setMp_valor(mp_valor);
        }

        cursor.close();
        db.close();

        return metodosPagamento;
    }

    public boolean atualizar(MetodosPagamento metodosPagamento, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getMeDetalhe(), metodosPagamento.getMp_detalhe());
        values.put(StringsNomes.getViId(), metodosPagamento.getVi_id());
        values.put(StringsNomes.getTpId(), metodosPagamento.getTp_id());
        values.put(StringsNomes.getMeVencimento(), metodosPagamento.getMp_dtvenc());
        values.put(StringsNomes.getMeValor(), metodosPagamento.getMp_valor());

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

    public List sp_viagens(){
        List<String> listarviagens = new ArrayList<>();
        String sql = DBSelects.getSelecionarNomeViagens();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            int indice = cursor.getColumnIndex(StringsNomes.getViNome());
            do{
                listarviagens.add(cursor.getString(indice));
            }while(cursor.moveToNext());
        }
        else{
            listarviagens.add(context.getResources().getString(R.string.encontrado_viagem));
        }

        cursor.close();
        db.close();

        return listarviagens;
    }

    public List sp_tipospagamento(){
        List<String> listartipopagamento = new ArrayList<>();
        String sql = DBSelects.getSelecionarTodosTipoPagamento();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){

            int indice = cursor.getColumnIndex(StringsNomes.getTpNome());

            do{
                listartipopagamento.add(cursor.getString(indice));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listartipopagamento;
    }
}
