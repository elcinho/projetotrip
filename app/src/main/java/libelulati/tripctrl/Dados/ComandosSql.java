package libelulati.tripctrl.Dados;


public class ComandosSql {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String PRIMARYKEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TEXTO = " TEXT";
    private static final String INTEIRO = " INTEGER";
    private static final String INSERT_INTO = "INSERT INTO ";
    private static final String VALUES = " VALUES ";
    private static final String UNIQUE = " UNIQUE ";

    //TABELA USUARIOS
    private static final String CREATE_TABLE_USUARIOS = getCreateTable() + Nomes.getTabelaUsuarios() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsUso() + getINTEIRO() + "," +
            " " + Nomes.getUsNome() + getTEXTO() + "," +
            " " + Nomes.getUsDtnasc() + getTEXTO() + "," +
            " " + Nomes.getUsEmail() + getTEXTO() + getUNIQUE() + "," +
            " " + Nomes.getUsSenha() + getTEXTO() + "," +
            " " + Nomes.getUsSemsenha() + getINTEIRO() + ")";

    // TABELA CATEGORIAS
    private static final String CREATE_TABLE_CATEGORIAS = getCreateTable() + Nomes.getTabelaCategorias() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getCaNome() + getTEXTO() + getUNIQUE() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + ")";

    // TABELA TIPOS DE PAGAMENTO
    private static final String CREATE_TABLE_TIPOSPAGAMENTO = getCreateTable() + Nomes.getTabelaTipospagamento() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getTpNome() + getTEXTO() + getUNIQUE() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + ")";

    // TABELA VIAGENS
    private static final String CREATE_TABLE_VIAGENS = getCreateTable() + Nomes.getTabelaViagens() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViNome() + getTEXTO() + "," +
            " " + Nomes.getViDtini() + getTEXTO() + "," +
            " " + Nomes.getViDtfim() + getTEXTO() + "," +
            " " + Nomes.getViValortotal() + getTEXTO() + ")";

    // TABELA PAGAMENTOS
    private static final String CREATE_TABLE_PAGAMENTOS = getCreateTable() + Nomes.getTabelaPagamentos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getINTEIRO() + "," +
            " " + Nomes.getTpId() + getTEXTO() + "," +
            " " + Nomes.getPaDescricao() + getTEXTO() + "," +
            " " + Nomes.getPaValor() + getTEXTO() + "," +
            " " + Nomes.getPaVencimento() + getTEXTO() + ")";

    // TABELA PLANEJAMENTO
    private static final String CREATE_TABLE_PLANEJAMENTOS = getCreateTable() + Nomes.getTabelaPlanejamentos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getTEXTO() + "," +
            " " + Nomes.getCaId() + getTEXTO() + "," +
            " " + Nomes.getPlValorcat() + getTEXTO() + ")";

    // TABELA GASTOS
    private static final String CREATE_TABLE_GASTOS = getCreateTable() + Nomes.getTabelaGastos() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getTEXTO() + "," +
            " " + Nomes.getCaId() + getTEXTO() + "," +
            " " + Nomes.getPaId() + getTEXTO() + "," +
            " " + Nomes.getGaValor() + getTEXTO() + "," +
            " " + Nomes.getGaData() + getTEXTO() + "," +
            " " + Nomes.getGaDescricao() + getTEXTO() + ")";

    // TABELA CONFIGURAÇÃO DE NOTIFICAÇÕES
    private static final String CREATE_TABLE_CNOTIFICACOES = getCreateTable() + Nomes.getTabelaCnotificacao() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getCnAtivar() + getINTEIRO() + "," +
            " " + Nomes.getCnTodas() + getINTEIRO() + "," +
            " " + Nomes.getCnViagens() + getINTEIRO() + "," +
            " " + Nomes.getCnPagamento() + getINTEIRO() + "," +
            " " + Nomes.getCnPlanejamento() + getINTEIRO() + "," +
            " " + Nomes.getCnGastos() + getINTEIRO() + ")";

    //TABELA TOTAIS
    private static final String CREATE_TABLE_TOTAIS = getCreateTable() + Nomes.getTabelaTotais() + "(" +
            " " + Nomes.getID() + getPRIMARYKEY() + "," +
            " " + Nomes.getUsId() + getINTEIRO() + "," +
            " " + Nomes.getViId() + getINTEIRO() + "," +
            " " + Nomes.getToNome() + getTEXTO() + "," +
            " " + Nomes.getToTotal() + getTEXTO() + "," +
            " " + Nomes.getToGasto() + getTEXTO() + "," +
            " " + Nomes.getToPlanejamento() + getTEXTO() + ")";


    // DROP TABLE
    private static final String DROP_TABLE_USUARIOS = getDropTable() + Nomes.getTabelaUsuarios();
    private static final String DROP_TABLE_CATEGORIAS = getDropTable() + Nomes.getTabelaCategorias();
    private static final String DROP_TABLE_TIPOSPAGAMENTO = getDropTable() + Nomes.getTabelaTipospagamento();
    private static final String DROP_TABLE_VIAGENS = getDropTable() + Nomes.getTabelaViagens();
    private static final String DROP_TABLE_PAGAMENTOS = getDropTable() + Nomes.getTabelaPagamentos();
    private static final String DROP_TABLE_PLANEJAMENTOS = getDropTable() + Nomes.getTabelaPlanejamentos();
    private static final String DROP_TABLE_GASTOS = getDropTable() + Nomes.getTabelaGastos();
    private static final String DROP_TABLE_CNOTIFICACOES = getDropTable() + Nomes.getTabelaCnotificacao();
    private static final String DROP_TABLE_TOTAIS = getDropTable() + Nomes.getTabelaTotais();

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

    //INSERT TABLE CNOTIFICACOES
    private static final String INSERT_CNOTIFICACOES = getInsertInto() + Nomes.getTabelaCnotificacao() + "(" +
            " " + Nomes.getUsId() + "," +
            " " + Nomes.getCnAtivar() + "," +
            " " + Nomes.getCnTodas() + "," +
            " " + Nomes.getCnViagens() + "," +
            " " + Nomes.getCnPagamento() + "," +
            " " + Nomes.getCnPlanejamento() + "," +
            " " + Nomes.getCnGastos() + ")" + getVALUES() + "(" +
            " " + "1, 1, 1, 1, 1, 1, 1" +
            ")";

    //INSERT TABLE USUÁRIO
    private static final String INSERT_USUARIO = getInsertInto() + Nomes.getTabelaUsuarios() + "(" +
            " " + Nomes.getUsUso() + "," +
            " " + Nomes.getUsNome() + "," +
            " " + Nomes.getUsEmail() +"," +
            " " + Nomes.getUsDtnasc() + "," +
            " " + Nomes.getUsSenha() + "," +
            " " + Nomes.getUsSemsenha() + ")" + getVALUES() + "(" +
            " " + "0, 'Desenvolvedor', 'desenvolvedor@libelulati.com', '06/10/2015', '102030', 0" +
            " )";


    //SELECIONAR TODOS POR USUÁRIO
    private static final String SELECT_VIAGENS_US = "SELECT * FROM " + Nomes.getTabelaViagens() + " WHERE " + Nomes.getUsId() + " = ";
    private static final String SELECT_CATEGORIAS_US = "SELECT * FROM " + Nomes.getTabelaCategorias() + " WHERE " + Nomes.getUsId() + " IS NULL OR " + Nomes.getUsId() + " = ";
    private static final String SELECT_PAGAMENTOS_US = "SELECT * FROM " + Nomes.getTabelaPagamentos() + " WHERE " + Nomes.getUsId() + " = ";
    private static final String SELECT_PLANEJAMENTOS_US = "SELECT * FROM " + Nomes.getTabelaPlanejamentos() + " WHERE " + Nomes.getUsId() + " = ";
    private static final String SELECT_TIPOSPAGAMENTO_US = "SELECT * FROM " + Nomes.getTabelaTipospagamento() + " WHERE " + Nomes.getUsId() + " IS NULL OR " + Nomes.getUsId() + " = ";
    private static final String SELECT_CNOTIFICACOES_US = "SELECT * FROM " + Nomes.getTabelaCnotificacao() + " WHERE " + Nomes.getUsId() + " IS NULL OR " + Nomes.getUsId() + " = ";


    //SELECIONAR TODOS POR VIAGEM
    private static final String SELECT_GASTOS_VI = "SELECT * FROM " + Nomes.getTabelaGastos() + " WHERE " + Nomes.getViId() + " = ";
    private static final String SELECT_PAGAMENTOS_VI = "SELECT * FROM " + Nomes.getTabelaPagamentos() + " WHERE " + Nomes.getViId() + " = ";
    private static final String SELECT_PLANEJAMENTOS_VI = "SELECT * FROM " + Nomes.getTabelaPlanejamentos() + " WHERE " + Nomes.getViId() + " = ";
    private static final String SELECT_TOTAIS_VI = "SELECT * FROM " + Nomes.getTabelaTotais() + " WHERE " + Nomes.getViId() + " = ";


    //SELECIONAR TODOS POR ID
    private static final String SELECT_ID_VIAGEM = "SELECT * FROM " + Nomes.getTabelaViagens() + " WHERE " + Nomes.getID() + " = ";
    private static final String SELECT_ID_GASTO = "SELECT * FROM " + Nomes.getTabelaGastos() + " WHERE " + Nomes.getID() + " = ";
    private static final String SELECT_ID_PAGAMENTO = "SELECT * FROM " + Nomes.getTabelaPagamentos() + " WHERE " + Nomes.getID() + " = ";
    private static final String SELECT_ID_PLANEJAMENTO = "SELECT * FROM " + Nomes.getTabelaPlanejamentos() + " WHERE " + Nomes.getID() + " = ";
    private static final String SELECT_ID_USUARIO = "SELECT * FROM " + Nomes.getTabelaUsuarios() + " WHERE " + Nomes.getID() + " = ";


    //SELECIONAR POR CAMPO ESPECÍFICO
    private static final String SELECT_EMAIL_USUARIO = "SELECT * FROM " + Nomes.getTabelaUsuarios() + " WHERE " + Nomes.getUsEmail() + " = ";
    private static final String SELECT_NOME_TOTAIS = "SELECT * FROM " + Nomes.getTabelaTotais() + " WHERE " + Nomes.getToNome() + " = ";


    //ATUALIZAÇÃO
    private static final String ATUALIZAR_WHERE = "_id = ?";


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

    public static String getInsertInto() {
        return INSERT_INTO;
    }

    public static String getVALUES() {
        return VALUES;
    }

    public static String getUNIQUE() {
        return UNIQUE;
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

    public static String getCreateTableCnotificacoes() {
        return CREATE_TABLE_CNOTIFICACOES;
    }

    public static String getCreateTableTotais() {
        return CREATE_TABLE_TOTAIS;
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

    public static String getDropTableCnotificacoes() {
        return DROP_TABLE_CNOTIFICACOES;
    }

    public static String getDropTableTotais() {
        return DROP_TABLE_TOTAIS;
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

    public static String getInsertCnotificacoes() {
        return INSERT_CNOTIFICACOES;
    }

    public static String getInsertUsuario() {
        return INSERT_USUARIO;
    }

    public static String getSelectViagensUs() {
        return SELECT_VIAGENS_US;
    }

    public static String getSelectCategoriasUs() {
        return SELECT_CATEGORIAS_US;
    }

    public static String getSelectPagamentosUs() {
        return SELECT_PAGAMENTOS_US;
    }

    public static String getSelectPlanejamentosUs() {
        return SELECT_PLANEJAMENTOS_US;
    }

    public static String getSelectTipospagamentoUs() {
        return SELECT_TIPOSPAGAMENTO_US;
    }

    public static String getSelectCnotificacoesUs() {
        return SELECT_CNOTIFICACOES_US;
    }

    public static String getSelectGastosVi() {
        return SELECT_GASTOS_VI;
    }

    public static String getSelectPagamentosVi() {
        return SELECT_PAGAMENTOS_VI;
    }

    public static String getSelectPlanejamentosVi() {
        return SELECT_PLANEJAMENTOS_VI;
    }

    public static String getSelectTotaisVi() {
        return SELECT_TOTAIS_VI;
    }

    public static String getSelectIdViagem() {
        return SELECT_ID_VIAGEM;
    }

    public static String getSelectIdGasto() {
        return SELECT_ID_GASTO;
    }

    public static String getSelectIdPagamento() {
        return SELECT_ID_PAGAMENTO;
    }

    public static String getSelectIdPlanejamento() {
        return SELECT_ID_PLANEJAMENTO;
    }

    public static String getSelectIdUsuario() {
        return SELECT_ID_USUARIO;
    }

    public static String getSelectEmailUsuario() {
        return SELECT_EMAIL_USUARIO;
    }

    public static String getSelectNomeTotais() {
        return SELECT_NOME_TOTAIS;
    }

    public static String getAtualizarWhere() {
        return ATUALIZAR_WHERE;
    }
}