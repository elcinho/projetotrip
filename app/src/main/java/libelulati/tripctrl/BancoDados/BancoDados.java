package libelulati.tripctrl.BancoDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDados extends SQLiteOpenHelper{


    public BancoDados(Context context) {
        super(context, StringsNomes.getNomeBanco(), null, StringsNomes.getVERSAO());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //cria tabelas

        db.execSQL(DBCreateDrop.getCreateTableCategorias());
        db.execSQL(DBCreateDrop.getCreateTableUsuarios());
        db.execSQL(DBCreateDrop.getCreateTableSubcategorias());
        db.execSQL(DBCreateDrop.getCreateTableTipostransporte());
        db.execSQL(DBCreateDrop.getCreateTableTiposhospedagem());
        db.execSQL(DBCreateDrop.getCreateTableTipospagamento());
        db.execSQL(DBCreateDrop.getCreateTableViagens());
        db.execSQL(DBCreateDrop.getCreateTablePlanejamento());
        db.execSQL(DBCreateDrop.getCreateTableMetodospagamento());
        db.execSQL(DBCreateDrop.getCreateTableGastos());

        //insere dados nas tabelas fixas

        //CATEGORIAS
        db.execSQL(DBInsertTabelas.getInsertTabelaCategoriasAlimentacao());
        db.execSQL(DBInsertTabelas.getInsertTabelaCategoriasTransporte());
        db.execSQL(DBInsertTabelas.getInsertTabelaCategoriasLazer());
        db.execSQL(DBInsertTabelas.getInsertTabelaCategoriasHospedagem());
        db.execSQL(DBInsertTabelas.getInsertTabelaCategoriasCompras());
        //TIPO HOSPEDAGEM
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemHotel());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemHostel());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemPousada());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemProprio());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemAcampamento());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemAlugado());
        db.execSQL(DBInsertTabelas.getInsertTabelaTiposhospedagemOutro());
        //TIPO PAGAMENTO
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoDinheiro());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoCc());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoCd());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoVp());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoTerceiros());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipospagamentoOutro());
        //TIPO TRANSPORTE
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteAviao());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteNavio());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteOnibus());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteExcursao());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteVp());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteVa());
        db.execSQL(DBInsertTabelas.getInsertTabelaTipostransporteOutro());
        db.execSQL(DBInsertTabelas.getInsertTabelaUsuarioGeral());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBCreateDrop.getDropTableGastos());
        db.execSQL(DBCreateDrop.getDropTableMetodospagamento());
        db.execSQL(DBCreateDrop.getDropTablePlanejamentos());
        db.execSQL(DBCreateDrop.getDropTableViagens());
        db.execSQL(DBCreateDrop.getDropTableTipostransporte());
        db.execSQL(DBCreateDrop.getDropTableTiposhospedagem());
        db.execSQL(DBCreateDrop.getDropTableTipospagamento());
        db.execSQL(DBCreateDrop.getDropTableSubcategorias());
        db.execSQL(DBCreateDrop.getDropTableUsuarios());
        db.execSQL(DBCreateDrop.getDropTableCategorias());

        onCreate(db);
    }
}