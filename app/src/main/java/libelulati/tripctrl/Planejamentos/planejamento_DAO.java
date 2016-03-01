package libelulati.tripctrl.Planejamentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Planejamento_DAO extends Dados{
    public Planejamento_DAO(Context context) {
        super(context);
    }

    public boolean criar(Planejamento planejamento){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), planejamento.getUs_id());
        values.put(Nomes.getViId(), planejamento.getVi_id());
        values.put(Nomes.getCaId(), planejamento.getCa_id());
        values.put(Nomes.getPlValorcat(), planejamento.getPl_valor());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(Nomes.getTabelaPlanejamentos(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Planejamento> listar(int viagem){
        List<Planejamento> listarregistros = new ArrayList<Planejamento>();
        String sql = ComandosSql.getSelectPlanejamentosVi() + viagem;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int pl_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String pl_categoria = cursor.getString(cursor.getColumnIndex(Nomes.getCaId()));
                String pl_valor = cursor.getString(cursor.getColumnIndex(Nomes.getPlValorcat()));

                Planejamento planejamento = new Planejamento(pl_id, pl_categoria, pl_valor);

                listarregistros.add(planejamento);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public Planejamento buscarID(int id){
        Planejamento planejamento = null;

        String sql = ComandosSql.getSelectIdPlanejamento() + id;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int pl_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int us_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getViId())));
            String pl_categoria = cursor.getString(cursor.getColumnIndex(Nomes.getCaId()));
            String pl_valor = cursor.getString(cursor.getColumnIndex(Nomes.getPlValorcat()));

            planejamento = new Planejamento();
            planejamento.setPl_id(pl_id);
            planejamento.setUs_id(us_id);
            planejamento.setVi_id(vi_id);
            planejamento.setCa_id(pl_categoria);
            planejamento.setPl_valor(pl_valor);
        }

        cursor.close();
        db.close();

        return planejamento;
    }

    public boolean atualizar(Planejamento planejamento, int id){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), planejamento.getUs_id());
        values.put(Nomes.getViId(), planejamento.getVi_id());
        values.put(Nomes.getCaId(), planejamento.getCa_id());
        values.put(Nomes.getPlValorcat(), planejamento.getPl_valor());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(Nomes.getTabelaPlanejamentos(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;

    }

    public boolean deletar(int id){
        boolean sucesso = false;

        String sql = Nomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(Nomes.getTabelaPlanejamentos(), sql, null) > 0;
        db.close();

        return sucesso;
    }

    public List sp_categorias(int id_usuario){
        List<String> listarcategorias = new ArrayList<>();
        String sql = ComandosSql.getSelectCategoriasUs() + id_usuario;

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){

            int indice = cursor.getColumnIndex(Nomes.getCaNome());

            do{
                listarcategorias.add(cursor.getString(indice));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarcategorias;
    }
}
