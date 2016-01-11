package libelulati.tripctrl.Viagens;

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

public class ViagensDAO extends BancoDados {

    public ViagensDAO(Context context) {
        super(context);
    }

    public boolean criar(Viagens viagens) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViNome(), viagens.getVi_nome());
        values.put(StringsNomes.getViLocal(), viagens.getVi_local());
        values.put(StringsNomes.getViDtini(), viagens.getVi_dtini());
        values.put(StringsNomes.getViDtfim(), viagens.getVi_dtfim());
        values.put(StringsNomes.getTrId(), viagens.getTr_id());
        values.put(StringsNomes.getViTransporte(), viagens.getVi_transporte());
        values.put(StringsNomes.getHoId(), viagens.getHo_id());
        values.put(StringsNomes.getViHospedagem(), viagens.getVi_hospedagem());
        values.put(StringsNomes.getViValortotal(), viagens.getVi_valortotal());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaViagens(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Viagens> listar(int usuario) {

        List<Viagens> listaRegistros = new ArrayList<Viagens>();
        String sql = DBSelects.getSelecionarViagens() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
                String vi_nome = cursor.getString(cursor.getColumnIndex(StringsNomes.getViNome()));
                String vi_dtini = cursor.getString(cursor.getColumnIndex(StringsNomes.getViDtini()));
                String vi_dtfim = cursor.getString(cursor.getColumnIndex(StringsNomes.getViDtfim()));

                Viagens viagens = new Viagens(vi_id, vi_nome, vi_dtini, vi_dtfim);

                listaRegistros.add(viagens);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listaRegistros;
    }

    public Viagens buscarID(int id) {

        Viagens viagens = null;

        String sql = DBSelects.getSelecionarIdViagens() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            int us_id = InicioActivity.getId_uslogado();
            String vi_nome = cursor.getString(cursor.getColumnIndex(StringsNomes.getViNome()));
            String vi_local = cursor.getString(cursor.getColumnIndex(StringsNomes.getViLocal()));
            String vi_dtinicio = cursor.getString(cursor.getColumnIndex(StringsNomes.getViDtini()));
            String vi_dtfim = cursor.getString(cursor.getColumnIndex(StringsNomes.getViDtfim()));
            String tr_tipotransporte = cursor.getString(cursor.getColumnIndex(StringsNomes.getTrId()));
            String vi_transporte = cursor.getString(cursor.getColumnIndex(StringsNomes.getViTransporte()));
            String ho_tipohospedagem = cursor.getString(cursor.getColumnIndex(StringsNomes.getHoId()));
            String vi_hospedagem = cursor.getString(cursor.getColumnIndex(StringsNomes.getViHospedagem()));
            String vi_valortotal = cursor.getString(cursor.getColumnIndex(StringsNomes.getViValortotal()));

            viagens = new Viagens();
            viagens.setVi_id(vi_id);
            viagens.setUs_id(us_id);
            viagens.setVi_nome(vi_nome);
            viagens.setVi_local(vi_local);
            viagens.setVi_dtini(vi_dtinicio);
            viagens.setVi_dtfim(vi_dtfim);
            viagens.setTr_id(tr_tipotransporte);
            viagens.setVi_transporte(vi_transporte);
            viagens.setHo_id(ho_tipohospedagem);
            viagens.setVi_hospedagem(vi_hospedagem);
            viagens.setVi_valortotal(vi_valortotal);
        }

        cursor.close();
        db.close();

        return viagens;
    }

    public boolean atualizar(Viagens viagens, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getViNome(), viagens.getVi_nome());
        values.put(StringsNomes.getViLocal(), viagens.getVi_local());
        values.put(StringsNomes.getViDtini(), viagens.getVi_dtini());
        values.put(StringsNomes.getViDtfim(), viagens.getVi_dtfim());
        values.put(StringsNomes.getTrId(), viagens.getTr_id());
        values.put(StringsNomes.getViTransporte(), viagens.getVi_transporte());
        values.put(StringsNomes.getHoId(), viagens.getHo_id());
        values.put(StringsNomes.getViHospedagem(), viagens.getVi_hospedagem());
        values.put(StringsNomes.getViValortotal(), viagens.getVi_valortotal());

        String where = DBSelects.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(StringsNomes.getTabelaViagens(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;

    }

    public boolean deletar(int id) {
        boolean sucesso = false;

        String sql = StringsNomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(StringsNomes.getTabelaViagens(), sql, null) > 0;
        db.close();

        return sucesso;
    }

    public List sp_tipostransporte(){
        List<String> listartipostransporte = new ArrayList<>();
        String sql = DBSelects.getSelecionarTodosTiposTransporte();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            int indice = cursor.getColumnIndex(StringsNomes.getTrNome());

            do{
                listartipostransporte.add(cursor.getString(indice));
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listartipostransporte;
    }

    public List sp_tiposhospedagem(){
        List<String> listartipohospedagem = new ArrayList<>();
        String sql = DBSelects.getSelecionarTodosTiposHospedagem();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){

            int indice = cursor.getColumnIndex(StringsNomes.getHoNome());

            do{
                listartipohospedagem.add(cursor.getString(indice));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listartipohospedagem;
    }
}
