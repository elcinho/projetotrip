package libelulati.tripctrl.Categoria;


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

public class CategoriaDAO extends BancoDados{

    public CategoriaDAO(Context context){
        super(context);
    }

    public boolean criar(Categoria categoria){

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getCaNome(), categoria.getCa_nome());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaCategorias(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Categoria> listar(int usuario){

        List<Categoria> listarcategorias = new ArrayList<>();
        String sql = DBSelects.getSelecionarCategorias() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int ca_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
                String ca_nome = cursor.getString(cursor.getColumnIndex(StringsNomes.getCaNome()));

                Categoria categoria = new Categoria(ca_id, ca_nome);
                listarcategorias.add(categoria);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarcategorias;
    }
}
