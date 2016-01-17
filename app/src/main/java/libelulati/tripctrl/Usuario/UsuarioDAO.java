package libelulati.tripctrl.Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import libelulati.tripctrl.BancoDados.BancoDados;
import libelulati.tripctrl.BancoDados.DBSelects;
import libelulati.tripctrl.BancoDados.StringsNomes;

/*
 *Classe de conexão do objeto Usuário ao banco de dados com os métodos de manipulação do mesmo.
 *
 */

public class UsuarioDAO extends BancoDados {

    public UsuarioDAO(Context context) {
        super(context);
    }

    //INSERT: Cria um novo usuário
    public boolean criar(Usuario usuario) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsCod(), usuario.getUs_cod());
        values.put(StringsNomes.getUsNome(), usuario.getUs_nome());
        values.put(StringsNomes.getUsDtnasc(), usuario.getUs_dtnasc());
        values.put(StringsNomes.getUsEmail(), usuario.getUs_email());
        values.put(StringsNomes.getUsLattude(), usuario.getUs_latitude());
        values.put(StringsNomes.getUsLongitude(), usuario.getUs_longitude());
        values.put(StringsNomes.getUsCodarea(), usuario.getUs_codarea());
        values.put(StringsNomes.getUsTelefone(), usuario.getUs_telefone());
        values.put(StringsNomes.getUsSenha(), usuario.getUs_senha());
        values.put(StringsNomes.getUsConfirmesenha(), usuario.getUs_confirmesenha());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(StringsNomes.getTabelaUsuarios(), null, values) > 0;
        db.close();

        return sucesso;
    }

    //UPDATE: atualiza o registro do usuário conforme seu ID
    public boolean atualizar(Usuario usuario, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsNome(), usuario.getUs_nome());
        values.put(StringsNomes.getUsDtnasc(), usuario.getUs_dtnasc());
        values.put(StringsNomes.getUsLattude(), usuario.getUs_latitude());
        values.put(StringsNomes.getUsLongitude(), usuario.getUs_longitude());
        values.put(StringsNomes.getUsCodarea(), usuario.getUs_codarea());
        values.put(StringsNomes.getUsTelefone(), usuario.getUs_telefone());

        String where = DBSelects.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(StringsNomes.getTabelaUsuarios(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }

    //Altera a senha do usuário
    public boolean alterarsenha(Usuario usuario, int id) {

        ContentValues values = new ContentValues();

        values.put(StringsNomes.getUsSenha(), usuario.getUs_senha());
        values.put(StringsNomes.getUsConfirmesenha(), usuario.getUs_confirmesenha());

        String where = DBSelects.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(StringsNomes.getTabelaUsuarios(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }

    //SELECT ID: Faz uma busca pelo ID informado
    public Usuario buscaId(int id) {

        Usuario usuario = null;
        String sql = DBSelects.getSelecionarIdUsuario() + id;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int id_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            String nome_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsNome()));
            String cod_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsCod()));
            String email_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsEmail()));
            String dtnasc_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsDtnasc()));
            String latitude_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsLattude()));
            String longitude_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsLongitude()));
            String codarea_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsCodarea()));
            String telefone_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsTelefone()));
            String senha_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsSenha()));
            String confirmesenha_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsConfirmesenha()));

            usuario = new Usuario();
            usuario.setUs_id(id_us);
            usuario.setUs_nome(nome_us);
            usuario.setUs_cod(cod_us);
            usuario.setUs_email(email_us);
            usuario.setUs_dtnasc(dtnasc_us);
            usuario.setUs_latitude(latitude_us);
            usuario.setUs_longitude(longitude_us);
            usuario.setUs_codarea(codarea_us);
            usuario.setUs_telefone(telefone_us);
            usuario.setUs_senha(senha_us);
            usuario.setUs_confirmesenha(confirmesenha_us);
        }

        cursor.close();
        db.close();

        return usuario;
    }

    //SELECT EMAIL: faz uma busca pelo e-mail informado
    public Usuario buscaEmail(String email) {

        Usuario usuario = null;
        String sql = DBSelects.getSelecionarEmailUsuario() + "'" + email + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int id_us = Integer.parseInt(cursor.getString(cursor.getColumnIndex(StringsNomes.getID())));
            String nome_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsNome()));
            String cod_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsCod()));
            String email_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsEmail()));
            String dtnasc_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsDtnasc()));
            String latitude_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsLattude()));
            String longitude_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsLongitude()));
            String codarea_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsCodarea()));
            String telefone_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsTelefone()));
            String senha_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsSenha()));
            String confirmesenha_us = cursor.getString(cursor.getColumnIndex(StringsNomes.getUsConfirmesenha()));

            usuario = new Usuario();
            usuario.setUs_id(id_us);
            usuario.setUs_nome(nome_us);
            usuario.setUs_cod(cod_us);
            usuario.setUs_email(email_us);
            usuario.setUs_dtnasc(dtnasc_us);
            usuario.setUs_latitude(latitude_us);
            usuario.setUs_longitude(longitude_us);
            usuario.setUs_codarea(codarea_us);
            usuario.setUs_telefone(telefone_us);
            usuario.setUs_senha(senha_us);
            usuario.setUs_confirmesenha(confirmesenha_us);
        }

        cursor.close();
        db.close();

        return usuario;
    }
}

