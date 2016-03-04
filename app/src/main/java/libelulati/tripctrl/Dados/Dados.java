package libelulati.tripctrl.Dados;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dados extends SQLiteOpenHelper{

    public Dados(Context context) {
        super(context, Nomes.getNomeBanco(), null, Nomes.getVERSAO());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CRIAR TABELAS
        db.execSQL(ComandosSql.getCreateTableUsuarios());
        db.execSQL(ComandosSql.getCreateTableTipospagamento());
        db.execSQL(ComandosSql.getCreateTableCategorias());
        db.execSQL(ComandosSql.getCreateTableViagens());
        db.execSQL(ComandosSql.getCreateTablePagamentos());
        db.execSQL(ComandosSql.getCreateTablePlanejamentos());
        db.execSQL(ComandosSql.getCreateTableGastos());
        db.execSQL(ComandosSql.getCreateTableCnotificacoes());

        //INSERIR DADOS CATEGORIAS
        db.execSQL(ComandosSql.getInsertCategoriasAlimentacao());
        db.execSQL(ComandosSql.getInsertCategoriasTransporte());
        db.execSQL(ComandosSql.getInsertCategoriasHospedagem());
        db.execSQL(ComandosSql.getInsertCategoriasLazer());
        db.execSQL(ComandosSql.getInsertCategoriasCompras());

        //INSERIR DADOS TIPOS PAGAMENTO
        db.execSQL(ComandosSql.getInsertTipospagamentoDinheiro());
        db.execSQL(ComandosSql.getInsertTipospagamentoCredito());
        db.execSQL(ComandosSql.getInsertTipospagamentoDebito());
        db.execSQL(ComandosSql.getInsertTipospagamentoMilhas());

        //INSERIR DADOS CNOTIFICACOES
        db.execSQL(ComandosSql.getInsertCnotificacoes());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //APAGA AS TABELAS
        db.execSQL(ComandosSql.getDropTableGastos());
        db.execSQL(ComandosSql.getDropTablePlanejamentos());
        db.execSQL(ComandosSql.getDropTablePagamentos());
        db.execSQL(ComandosSql.getDropTableViagens());
        db.execSQL(ComandosSql.getDropTableCategorias());
        db.execSQL(ComandosSql.getDropTableTipospagamento());
        db.execSQL(ComandosSql.getDropTableUsuarios());
        db.execSQL(ComandosSql.getDropTableCnotificacoes());

        onCreate(db);
    }
}
