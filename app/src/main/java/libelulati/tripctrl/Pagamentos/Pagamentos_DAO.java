package libelulati.tripctrl.Pagamentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Dados.ComandosSql;
import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class Pagamentos_DAO extends Dados{
    public Pagamentos_DAO(Context context) {
        super(context);
    }

    public boolean criar(Pagamento pagamento){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), pagamento.getUs_id());
        values.put(Nomes.getViId(), pagamento.getVi_id());
        values.put(Nomes.getTpId(), pagamento.getTp_id());
        values.put(Nomes.getPaDescricao(), pagamento.getPa_descricao());
        values.put(Nomes.getPaVencimento(), pagamento.getPa_dtvenc());
        values.put(Nomes.getPaValor(), pagamento.getPa_valor());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.insert(Nomes.getTabelaPagamentos(), null, values) > 0;
        db.close();

        return sucesso;
    }

    public List<Pagamento> listar(int viagem){
        List<Pagamento> listarregistros = new ArrayList<Pagamento>();
        String sql = ComandosSql.getSelectPagamentosVi() + viagem;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int pa_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
                String pa_descricao = cursor.getString(cursor.getColumnIndex(Nomes.getPaDescricao()));
                String pa_tipopagamento = cursor.getString(cursor.getColumnIndex(Nomes.getTpId()));
                String pa_vencimento = cursor.getString(cursor.getColumnIndex(Nomes.getPaVencimento()));
                String pa_valor = cursor.getString(cursor.getColumnIndex(Nomes.getPaValor()));

                Pagamento pagamento = new Pagamento(pa_id, pa_descricao, pa_valor, pa_vencimento, pa_tipopagamento);

                listarregistros.add(pagamento);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listarregistros;
    }

    public Pagamento buscarID(int id){
        Pagamento pagamento = null;

        String sql = ComandosSql.getSelectIdPagamento() + id;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int pa_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getID())));
            int us_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getUsId())));
            int vi_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Nomes.getViId())));
            String tp_id = cursor.getString(cursor.getColumnIndex(Nomes.getTpId()));
            String pa_descricao = cursor.getString(cursor.getColumnIndex(Nomes.getPaDescricao()));
            String pa_valor = cursor.getString(cursor.getColumnIndex(Nomes.getPaValor()));
            String pa_vencimento = cursor.getString(cursor.getColumnIndex(Nomes.getPaVencimento()));

            pagamento = new Pagamento();
            pagamento.setPa_id(pa_id);
            pagamento.setUs_id(us_id);
            pagamento.setVi_id(vi_id);
            pagamento.setTp_id(tp_id);
            pagamento.setPa_descricao(pa_descricao);
            pagamento.setPa_valor(pa_valor);
            pagamento.setPa_dtvenc(pa_vencimento);
        }

        cursor.close();
        db.close();

        return pagamento;
    }

    public boolean atualizar(Pagamento pagamento, int id){

        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), pagamento.getUs_id());
        values.put(Nomes.getViId(), pagamento.getVi_id());
        values.put(Nomes.getTpId(), pagamento.getTp_id());
        values.put(Nomes.getPaDescricao(), pagamento.getPa_descricao());
        values.put(Nomes.getPaVencimento(), pagamento.getPa_dtvenc());
        values.put(Nomes.getPaValor(), pagamento.getPa_valor());

        String where = ComandosSql.getAtualizarWhere();
        String[] whereArgs = {Integer.toString(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean sucesso = db.update(Nomes.getTabelaPagamentos(), values, where, whereArgs) > 0;
        db.close();

        return sucesso;
    }

    public boolean deletar(int id){
        boolean sucesso = false;

        String sql = Nomes.getID() + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        sucesso = db.delete(Nomes.getTabelaPagamentos(), sql, null) > 0;
        db.close();

        return sucesso;
    }

    public List sp_tipopagamentos (int id_usuario){
        List<String> listartipospagamentos = new ArrayList<>();
        String sql = ComandosSql.getSelectTipospagamentoUs() + id_usuario;

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){

            int indice = cursor.getColumnIndex(Nomes.getTpNome());
            do{
                listartipospagamentos.add(cursor.getString(indice));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listartipospagamentos;
    }
}
