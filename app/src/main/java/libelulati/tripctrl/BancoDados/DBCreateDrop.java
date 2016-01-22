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

    // Criar tabelas

    //CATEGORIAS
    private static final String CREATE_TABLE_CATEGORIAS = getCreateTable() + StringsNomes.getTabelaCategorias() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getCaNome() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() +
            getFECHAPARENTESES();

    //USUÁRIOS
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
            StringsNomes.getUsUso() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getUsSenha() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsConfirmesenha() + getTEXTO() +
            getFECHAPARENTESES();

    //TIPOS DE TRANSPORTE
    private static final String CREATE_TABLE_TIPOSTRANSPORTE = getCreateTable() + StringsNomes.getTabelaTipostransporte() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getTrNome() + getTEXTO() +
            getFECHAPARENTESES();

    //TIPOS DE HOSPEDAGEM
    private static final String CREATE_TABLE_TIPOSHOSPEDAGEM = getCreateTable() + StringsNomes.getTabelaTiposhospedagem() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getHoNome() + getTEXTO() +
            getFECHAPARENTESES();

    //TIPOS DE PAGAMENTO
    private static final String CREATE_TABLE_TIPOSPAGAMENTO = getCreateTable() + StringsNomes.getTabelaTipospagamento() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getTpNome() + getTEXTO() +
            getFECHAPARENTESES();

    //VIAGENS
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
            StringsNomes.getViValortotal() + getDECIMAL() +
            getFECHAPARENTESES();

    //PLANEJAMENTO
    private static final String CREATE_TABLE_PLANEJAMENTO = getCreateTable() + StringsNomes.getTabelaPlanejamentos() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getPlDescricao() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getCaId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getPlValorcat() + getDECIMAL() +
            getFECHAPARENTESES();

    //GASTOS
    private static final String CREATE_TABLE_GASTOS = getCreateTable() + StringsNomes.getTabelaGastos() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getGaDescricao() + getTEXTO() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getCaId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getGaValorcat() + getDECIMAL() +
            getFECHAPARENTESES();

    //MÉTODOS DE PAGAMENTO
    private static final String CREATE_TABLE_METODOSPAGAMENTO = getCreateTable() + StringsNomes.getTabelaMetodospagamento() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getTpId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getMeDetalhe() + getTEXTO() + getVIRGULA() +
            StringsNomes.getMeValor() + getDECIMAL() + getVIRGULA() +
            StringsNomes.getMeVencimento() + getTEXTO() +
            getFECHAPARENTESES();

    //LISTA
    private static final String CREATE_TABLE_LISTAS = getCreateTable() + StringsNomes.getTabelaListas() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getUsId() + getINTEIRO() + getVIRGULA() +
            StringsNomes.getViId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getLiNome() + getTEXTO() +
            getFECHAPARENTESES();

    //ITEM LISTA
    private static final String CREATE_TABLE_ITEMLISTA = getCreateTable() + StringsNomes.getTabelaListaitem() + getABREPARENTESES() +
            StringsNomes.getID() + getPRIMARYKEY() + getVIRGULA() +
            StringsNomes.getLiId() + getTEXTO() + getVIRGULA() +
            StringsNomes.getIlNome() + getTEXTO() + getVIRGULA() +
            StringsNomes. getIlCheck() + getINTEIRO() + getVIRGULA() +
            getFECHAPARENTESES();



    // Apagar tabelas
    private static final String DROP_TABLE_CATEGORIAS = getDropTable() + StringsNomes.getTabelaCategorias();
    private static final String DROP_TABLE_USUARIOS = getDropTable() + StringsNomes.getTabelaUsuarios();
    private static final String DROP_TABLE_TIPOSTRANSPORTE = getDropTable() + StringsNomes.getTabelaTipostransporte();
    private static final String DROP_TABLE_TIPOSHOSPEDAGEM = getDropTable() + StringsNomes.getTabelaTiposhospedagem();
    private static final String DROP_TABLE_TIPOSPAGAMENTO = getDropTable() + StringsNomes.getTabelaTipospagamento();
    private static final String DROP_TABLE_VIAGENS = getDropTable() + StringsNomes.getTabelaViagens();
    private static final String DROP_TABLE_PLANEJAMENTOS = getDropTable() + StringsNomes.getTabelaPlanejamentos();
    private static final String DROP_TABLE_GASTOS = getDropTable() + StringsNomes.getTabelaGastos();
    private static final String DROP_TABLE_METODOSPAGAMENTO = getDropTable() + StringsNomes.getTabelaMetodospagamento();
    private static final String DROP_TABLE_LISTAS = getDropTable() + StringsNomes.getTabelaListas();
    private static final String DROP_TABLE_ITEMLISTA = getDropTable() + StringsNomes.getTabelaListaitem();


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

    public static String getCreateTableListas() {
        return CREATE_TABLE_LISTAS;
    }

    public static String getCreateTableItemlista() {
        return CREATE_TABLE_ITEMLISTA;
    }

    public static String getDropTableCategorias() {
        return DROP_TABLE_CATEGORIAS;
    }

    public static String getDropTableUsuarios() {
        return DROP_TABLE_USUARIOS;
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

    public static String getDropTableListas() {
        return DROP_TABLE_LISTAS;
    }

    public static String getDropTableItemlista() {
        return DROP_TABLE_ITEMLISTA;
    }
}
