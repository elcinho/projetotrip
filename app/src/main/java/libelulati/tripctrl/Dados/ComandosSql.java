package libelulati.tripctrl.Dados;


public class ComandosSql {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String PRIMARYKEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXTO = " TEXT";
    private static final String INTEIRO = " INTEGER";
    private static final String DECIMAL = " DECIMAL";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String VALUES = " VALUES ";

    //TABELA USUARIOS
    private static final String CREATE_TABLE_USUARIOS = getCreateTable() + Nomes.getTabelaUsuarios() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsUso() + getINTEIRO() + "," +
            " " + Nomes.getUsNome() + getTEXTO() + "," +
            " " + Nomes.getUsDtnasc() + getTEXTO() + "," +
            " " + Nomes.getUsEmail() + getTEXTO() + "," +
            " " + Nomes.getUsSenha() + getTEXTO() + ")";

    // TABELA CATEGORIAS
    private static final String CREATE_TABLE_CATEGORIAS = getCreateTable() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getCaNome() + getTEXTO() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + ")";


    // TABELA TIPOS DE PAGAMENTO
    private static final String CREATE_TABLE_TIPOSPAGAMENTO = getCreateTable() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getTpNome() + getTEXTO() + ")";

    // TABELA VIAGENS
    private static final String CREATE_TABLE_VIAGENS = getCreateTable() + Nomes.getTabelaViagens() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViNome() + getTEXTO() + "," +
            " " + Nomes.getViDtini() + getTEXTO() + "," +
            " " + Nomes.getViDtfim() + getTEXTO() + "," +
            " " + Nomes.getViValortotal() + getDECIMAL() + ")";

    // TABELA PAGAMENTOS
    private static final String CREATE_TABLE_PAGAMENTOS = getCreateTable() + Nomes.getTabelaPagamentos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getTpId() + getTEXTO() + "," +
            " " + Nomes.getPaValor() + getDECIMAL() + "," +
            " " + Nomes.getPaVencimento() + getTEXTO() + ")";

    // TABELA PLANEJAMENTO
    private static final String CREATE_TABLE_PLANEJAMENTOS = getCreateTable() + Nomes.getTabelaPlanejamentos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getTEXTO() + "," +
            " " + Nomes.getCaId() + getTEXTO() + "," +
            " " + Nomes.getPlValorcat() + getDECIMAL() + ")";

    // TABELA GASTOS
    private static final String CREATE_TABLE_GASTOS = getCreateTable() + Nomes.getTabelaGastos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getTEXTO() + "," +
            " " + Nomes.getCaId() + getTEXTO() + "," +
            " " + Nomes.getPaId() + getTEXTO() + "," +
            " " + Nomes.getGaValor() + getDECIMAL() + "," +
            " " + Nomes.getGaDescricao() + getTEXTO() + ")";


    // DROP TABLE
    private static final String DROP_TABLE_USUARIOS = getDropTable() + Nomes.getTabelaUsuarios();
    private static final String DROP_TABLE_CATEGORIAS = getDropTable() + Nomes.getTabelaCategorias();
    private static final String DROP_TABLE_TIPOSPAGAMENTO = getDropTable() + Nomes.getTabelaTipospagamento();
    private static final String DROP_TABLE_VIAGENS = getDropTable() + Nomes.getTabelaViagens();
    private static final String DROP_TABLE_PAGAMENTOS = getDropTable() + Nomes.getTabelaPagamentos();
    private static final String DROP_TABLE_PLANEJAMENTOS = getDropTable() + Nomes.getTabelaPlanejamentos();
    private static final String DROP_TABLE_GASTOS = getDropTable() + Nomes.getTabelaGastos();

    // INSERT TABLE CATEGORIAS
    private static final String INSERT_CATEGORIAS_ALIMENTACAO = getInsertInto() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getCaNome() + ")" + getVALUES() + "('Alimentação')";
    private static final String INSERT_CATEGORIAS_TRANSPORTE = getInsertInto() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getCaNome() + ")" + getVALUES() + "('Transporte')";
    private static final String INSERT_CATEGORIAS_HOSPEDAGEM = getInsertInto() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getCaNome() + ")" + getVALUES() + "('Hospedagem')";
    private static final String INSERT_CATEGORIAS_LAZER = getInsertInto() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getCaNome() + ")" + getVALUES() + "('Lazer')";
    private static final String INSERT_CATEGORIAS_COMPRAS = getInsertInto() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getCaNome() + ")" + getVALUES() + "('Compras')";

    //INSERT TABLE TIPOS PAGAMENTO
    private static final String INSERT_TIPOSPAGAMENTO_DINHEIRO = getInsertInto() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getTpNome() + ")" + getVALUES() + "('Dinheiro')";
    private static final String INSERT_TIPOSPAGAMENTO_CREDITO = getInsertInto() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getTpNome() + ")" + getVALUES() + "('Cartão de Crédito')";
    private static final String INSERT_TIPOSPAGAMENTO_DEBITO = getInsertInto() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getTpNome() + ")" + getVALUES() + "('Cartão de Débito')";
    private static final String INSERT_TIPOSPAGAMENTO_MILHAS = getInsertInto() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getTpNome() + ")" + getVALUES() + "('Milhas')";

    //SELECIONAR TODOS POR USUÁRIO
    private static final String SELECT_VIAGENS = "SELECT * FROM " + Nomes.getTabelaViagens() + " WHERE " + Nomes.getUsId() + " = ";


    //SELECIONAR TODOS POR ID
    private static final String SELECT_ID_VIAGEM = "SELECT * FROM " + Nomes.getTabelaViagens() + " WHERE " + Nomes.getID() + " = ";

    //GETTERS


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

    public static String getInsertInto() {
        return INSERT_INTO;
    }

    public static String getVALUES() {
        return VALUES;
    }

    public static String getCreateTableUsuarios() {
        return CREATE_TABLE_USUARIOS;
    }

    public static String getCreateTableCategorias() {
        return CREATE_TABLE_CATEGORIAS;
    }

    public static String getCreateTableTipospagamento() {
        return CREATE_TABLE_TIPOSPAGAMENTO;
    }

    public static String getCreateTableViagens() {
        return CREATE_TABLE_VIAGENS;
    }

    public static String getCreateTablePagamentos() {
        return CREATE_TABLE_PAGAMENTOS;
    }

    public static String getCreateTablePlanejamentos() {
        return CREATE_TABLE_PLANEJAMENTOS;
    }

    public static String getCreateTableGastos() {
        return CREATE_TABLE_GASTOS;
    }

    public static String getDropTableUsuarios() {
        return DROP_TABLE_USUARIOS;
    }

    public static String getDropTableCategorias() {
        return DROP_TABLE_CATEGORIAS;
    }

    public static String getDropTableTipospagamento() {
        return DROP_TABLE_TIPOSPAGAMENTO;
    }

    public static String getDropTableViagens() {
        return DROP_TABLE_VIAGENS;
    }

    public static String getDropTablePagamentos() {
        return DROP_TABLE_PAGAMENTOS;
    }

    public static String getDropTablePlanejamentos() {
        return DROP_TABLE_PLANEJAMENTOS;
    }

    public static String getDropTableGastos() {
        return DROP_TABLE_GASTOS;
    }

    public static String getInsertCategoriasAlimentacao() {
        return INSERT_CATEGORIAS_ALIMENTACAO;
    }

    public static String getInsertCategoriasTransporte() {
        return INSERT_CATEGORIAS_TRANSPORTE;
    }

    public static String getInsertCategoriasHospedagem() {
        return INSERT_CATEGORIAS_HOSPEDAGEM;
    }

    public static String getInsertCategoriasLazer() {
        return INSERT_CATEGORIAS_LAZER;
    }

    public static String getInsertCategoriasCompras() {
        return INSERT_CATEGORIAS_COMPRAS;
    }

    public static String getInsertTipospagamentoDinheiro() {
        return INSERT_TIPOSPAGAMENTO_DINHEIRO;
    }

    public static String getInsertTipospagamentoCredito() {
        return INSERT_TIPOSPAGAMENTO_CREDITO;
    }

    public static String getInsertTipospagamentoDebito() {
        return INSERT_TIPOSPAGAMENTO_DEBITO;
    }

    public static String getInsertTipospagamentoMilhas() {
        return INSERT_TIPOSPAGAMENTO_MILHAS;
    }

    public static String getSelectViagens() {
        return SELECT_VIAGENS;
    }

    public static String getSelectIdViagem() {
        return SELECT_ID_VIAGEM;
    }
}
