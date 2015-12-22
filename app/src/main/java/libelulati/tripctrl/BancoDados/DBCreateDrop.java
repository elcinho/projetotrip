package libelulati.tripctrl.BancoDados;

public class DBCreateDrop {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String PRIMARYKEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXTO = " TEXT";
    private static final String INTEIRO = " INTEGER";
    private static final String DECIMAL = " DECIMAL";
    private static final String ABREPARENTESES = "(";
    private static final String FECHAPARENTESES = ")";
    private static final String VIRGULA = ", ";
    private static final String FOREINGKEY = " FOREIGN KEY";
    private static final String REFERENCES = " REFERENCES ";
    private static final String CREATE_VIEWS = "CREATE VIEW IF NOT EXISTS ";

    //Criar Views
    public static final String CREATE_VIEW_VIAGENS = getCreateViews() + StringsNomes.getViewViagens() + " AS " + DBSelects.getSelecionarDadosViagens();

    // Criar tabelas
    private static final String CREATE_TABLE_CATEGORIAS = getCreateTable() + StringsNomes.getTabelaCategorias() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getCaNome() + getTEXTO() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_USUARIOS = getCreateTable() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsCod() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsNome() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsDtnasc() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsEmail() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsLattude() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsLongitude() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsCodarea() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsTelefone() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsSenha() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsConfirmesenha() + getTEXTO() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_SUBCATEGORIAS = getCreateTable() + StringsNomes.getTabelaSubcategorias() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getCaId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getScNome() + getTEXTO() + getVIRGULA() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getCaId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaCategorias() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getUsId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_TIPOSTRANSPORTE = getCreateTable() + StringsNomes.getTabelaTipostransporte() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getTrNome() + getTEXTO() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_TIPOSHOSPEDAGEM = getCreateTable() + StringsNomes.getTabelaTiposhospedagem() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getHoNome() + getTEXTO() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_TIPOSPAGAMENTO = getCreateTable() + StringsNomes.getTabelaTipospagamento() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getTpNome() + getTEXTO() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_VIAGENS = getCreateTable() + StringsNomes.getTabelaViagens() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViNome() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViDtini() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViDtfim() + getTEXTO() + getVIRGULA() +
            StringsNomes.getTrId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViTransporte() + getTEXTO() + getVIRGULA() +
            StringsNomes.getHoId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViHospedagem() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViLocal() + getTEXTO() + getVIRGULA() +
            StringsNomes.getViValortotal() + getDECIMAL() + getVIRGULA() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getUsId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getTrId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaTipostransporte() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getHoId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaTiposhospedagem() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_PLANEJAMENTO = getCreateTable() + StringsNomes.getTabelaPlanejamentos() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getScId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getPlValorcat() + getDECIMAL() + getVIRGULA() +
            StringsNomes.getPlValortot() + getDECIMAL() + getVIRGULA() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getUsId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getViId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaViagens() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getScId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaSubcategorias() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_GASTOS = getCreateTable() + StringsNomes.getTabelaGastos() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getScId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getGaValorcat() + getDECIMAL() + getVIRGULA() +
            StringsNomes.getGaValortot() + getDECIMAL() + getVIRGULA() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getUsId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getViId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaViagens() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getScId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaSubcategorias() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFECHAPARENTESES();
    private static final String CREATE_TABLE_METODOSPAGAMENTO = getCreateTable() + StringsNomes.getTabelaMetodospagamento() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getTpId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getMeDetalhe() + getTEXTO() + getVIRGULA() +
            StringsNomes.getMeValor() + getDECIMAL() + getVIRGULA() +
            StringsNomes.getMeVencimento() + getTEXTO() + getVIRGULA() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getUsId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaUsuarios() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getViId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaViagens() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFOREINGKEY() + getABREPARENTESES() + StringsNomes.getTpId() + getFECHAPARENTESES() + getREFERENCES() + StringsNomes.getTabelaTipospagamento() + getABREPARENTESES() + StringsNomes.getID() + getFECHAPARENTESES() +
            getFECHAPARENTESES();

    // Apagar tabelas
    private static final String DROP_TABLE_CATEGORIAS = getDropTable() + StringsNomes.getTabelaCategorias();
    private static final String DROP_TABLE_USUARIOS = getDropTable() + StringsNomes.getTabelaUsuarios();
    private static final String DROP_TABLE_SUBCATEGORIAS = getDropTable() + StringsNomes.getTabelaSubcategorias();
    private static final String DROP_TABLE_TIPOSTRANSPORTE = getDropTable() + StringsNomes.getTabelaTipostransporte();
    private static final String DROP_TABLE_TIPOSHOSPEDAGEM = getDropTable() + StringsNomes.getTabelaTiposhospedagem();
    private static final String DROP_TABLE_TIPOSPAGAMENTO = getDropTable() + StringsNomes.getTabelaTipospagamento();
    private static final String DROP_TABLE_VIAGENS = getDropTable() + StringsNomes.getTabelaViagens();
    private static final String DROP_TABLE_PLANEJAMENTOS = getDropTable() + StringsNomes.getTabelaPlanejamentos();
    private static final String DROP_TABLE_GASTOS = getDropTable() + StringsNomes.getTabelaGastos();
    private static final String DROP_TABLE_METODOSPAGAMENTO = getDropTable() + StringsNomes.getTabelaMetodospagamento();

    //getters
    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    public static String getDropTable() {
        return DROP_TABLE;
    }

    public static String getPRIMARYKEY() {
        return PRIMARYKEY;
    }

    public static String getTEXTO() {
        return TEXTO;
    }

    public static String getINTEIRO() {
        return INTEIRO;
    }

    public static String getDECIMAL() {
        return DECIMAL;
    }

    public static String getABREPARENTESES() {
        return ABREPARENTESES;
    }

    public static String getFECHAPARENTESES() {
        return FECHAPARENTESES;
    }

    public static String getVIRGULA() {
        return VIRGULA;
    }

    public static String getFOREINGKEY() {
        return FOREINGKEY;
    }

    public static String getREFERENCES() {
        return REFERENCES;
    }

    public static String getCreateTableCategorias() {
        return CREATE_TABLE_CATEGORIAS;
    }

    public static String getCreateTableUsuarios() {
        return CREATE_TABLE_USUARIOS;
    }

    public static String getCreateTableSubcategorias() {
        return CREATE_TABLE_SUBCATEGORIAS;
    }

    public static String getCreateTableTipostransporte() {
        return CREATE_TABLE_TIPOSTRANSPORTE;
    }

    public static String getCreateTableTiposhospedagem() {
        return CREATE_TABLE_TIPOSHOSPEDAGEM;
    }

    public static String getCreateTableTipospagamento() {
        return CREATE_TABLE_TIPOSPAGAMENTO;
    }

    public static String getCreateTableViagens() {
        return CREATE_TABLE_VIAGENS;
    }

    public static String getCreateTablePlanejamento() {
        return CREATE_TABLE_PLANEJAMENTO;
    }

    public static String getCreateTableGastos() {
        return CREATE_TABLE_GASTOS;
    }

    public static String getCreateTableMetodospagamento() {
        return CREATE_TABLE_METODOSPAGAMENTO;
    }

    public static String getDropTableCategorias() {
        return DROP_TABLE_CATEGORIAS;
    }

    public static String getDropTableUsuarios() {
        return DROP_TABLE_USUARIOS;
    }

    public static String getDropTableSubcategorias() {
        return DROP_TABLE_SUBCATEGORIAS;
    }

    public static String getDropTableTipostransporte() {
        return DROP_TABLE_TIPOSTRANSPORTE;
    }

    public static String getDropTableTiposhospedagem() {
        return DROP_TABLE_TIPOSHOSPEDAGEM;
    }

    public static String getDropTableTipospagamento() {
        return DROP_TABLE_TIPOSPAGAMENTO;
    }

    public static String getDropTableViagens() {
        return DROP_TABLE_VIAGENS;
    }

    public static String getDropTablePlanejamentos() {
        return DROP_TABLE_PLANEJAMENTOS;
    }

    public static String getDropTableGastos() {
        return DROP_TABLE_GASTOS;
    }

    public static String getDropTableMetodospagamento() {
        return DROP_TABLE_METODOSPAGAMENTO;
    }

    public static String getCreateViews() {
        return CREATE_VIEWS;
    }

    public static String getCreateViewViagens() {
        return CREATE_VIEW_VIAGENS;
    }
}
