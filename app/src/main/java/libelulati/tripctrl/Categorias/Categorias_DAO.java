package libelulati.tripctrl.Categorias;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Categorias_DAO extends Dados{
    public Categorias_DAO(Context context) {
        super(context);
    }

    public boolean criar (Categoria categoria){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), categoria.getUs_id());
        values.put(Nomes.getCaNome(), categoria.getCa_nome());

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.insert(Nomes.getTabelaCategorias(), null, values) > 0;

        db.close();
        return sucesso;
    }

    public List<Categoria> listar(int usuario){
        List<Categoria> listarregistros = new ArrayList<Categoria>();
        String sql = ComandosSql.getSelectCategoriasUs() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int ca_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String us_id = cursor.getString(cursor.getColumnIndex(Nomes.getUsId()));
                String ca_nome = cursor.getString(cursor.getColumnIndex(Nomes.getCaNome()));

                if(us_id == null){
                    us_id = "0";
                }

                Categoria categoria = new Categoria(ca_id, Integer.parseInt(us_id), ca_nome);
                listarregistros.add(categoria);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public boolean atualizar(Categoria categoria, int id){
        ContentValues values = new ContentValues();
        values.put(Nomes.getUsId(), categoria.getUs_id());
        values.put(Nomes.getCaNome(), categoria.getCa_nome());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.update(Nomes.getTabelaCategorias(), values, where, whereArgs) > 0;

        db.close();
        return sucesso;
    }

    public boolean deletar (int id){
        String sql = Nomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        boolean sucesso = db.delete(Nomes.getTabelaCategorias(), sql, null) > 0;

        db.close();

        return sucesso;
    }
}
