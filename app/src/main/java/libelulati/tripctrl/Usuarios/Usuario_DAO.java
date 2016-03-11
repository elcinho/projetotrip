package libelulati.tripctrl.Usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Usuario_DAO extends Dados{
    public Usuario_DAO(Context context) {
        super(context);
    }

    public boolean criar(Usuario usuario) {

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsNome(), usuario.getUs_nome());
        values.put(Nomes.getUsDtnasc(), usuario.getUs_dtnasc());
        values.put(Nomes.getUsEmail(), usuario.getUs_email());
        values.put(Nomes.getUsSenha(), usuario.getUs_senha());
        values.put(Nomes.getUsSemsenha(), usuario.getUs_semsenha());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(Nomes.getTabelaUsuarios(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public boolean atualizar(Usuario usuario, int id) {

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsNome(), usuario.getUs_nome());
        values.put(Nomes.getUsDtnasc(), usuario.getUs_dtnasc());
        values.put(Nomes.getUsSemsenha(), usuario.getUs_semsenha());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(Nomes.getTabelaUsuarios(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }

    public boolean alterarsenha(Usuario usuario, int id) {

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsSenha(), usuario.getUs_senha());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(Nomes.getTabelaUsuarios(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }

    public Usuario buscaId(int id) {

        Usuario usuario = null;
        String sql = ComandosSql.getSelectIdUsuario() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int id_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int semsenha_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsSemsenha())));
            String nome_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsNome()));
            String email_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsEmail()));
            String dtnasc_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsDtnasc()));
            String senha_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsSenha()));

            usuario = new Usuario();
            usuario.setUs_id(id_us);
            usuario.setUs_semsenha(semsenha_us);
            usuario.setUs_nome(nome_us);
            usuario.setUs_email(email_us);
            usuario.setUs_dtnasc(dtnasc_us);
            usuario.setUs_senha(senha_us);
        }

        cursor.close();
        db.close();

        return usuario;
    }

    public Usuario buscaEmail(String email) {

        Usuario usuario = null;
        String sql = ComandosSql.getSelectEmailUsuario() + "'" + email + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int id_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int semsenha_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsSemsenha())));
            String nome_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsNome()));
            String email_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsEmail()));
            String dtnasc_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsDtnasc()));
            String senha_us = cursor.getString(cursor.getColumnIndex(Nomes.getUsSenha()));

            usuario = new Usuario();
            usuario.setUs_id(id_us);
            usuario.setUs_semsenha(semsenha_us);
            usuario.setUs_nome(nome_us);
            usuario.setUs_email(email_us);
            usuario.setUs_dtnasc(dtnasc_us);
            usuario.setUs_senha(senha_us);
        }

        cursor.close();
        db.close();

        return usuario;
    }

    public boolean alterarTermo(Usuario usuario, int id) {

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsUso(), usuario.getUs_uso());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(Nomes.getTabelaUsuarios(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }
}
