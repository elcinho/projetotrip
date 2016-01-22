package libelulati.tripctrl.Listas;

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

public class ListasDAO extends BancoDados{
    public ListasDAO(Context context) {
        super(context);
    }

    public boolean criar(Lista lista){

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsId(), InicioActivity.getId_uslogado());
        values.put(StringsNomes.getLiNome(), lista.getLi_nome());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaListas(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Lista> listar(int usuario){

        List<Lista> listarlistas = new ArrayList<>();
        String sql = DBSelects.getSelecionarListas() + usuario;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int li_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
                String li_nome = cursor.getString(cursor.getColumnIndex(StringsNomes.getCaNome()));

                Lista lista = new Lista(li_id, li_nome);
                listarlistas.add(lista);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarlistas;
    }
}
