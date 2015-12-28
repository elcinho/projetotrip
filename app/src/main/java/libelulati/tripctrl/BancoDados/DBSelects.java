package libelulati.tripctrl.BancoDados;

public class DBSelects {

    private static final String ATUALIZAR_WHERE = "_id = ?";

    //TODOS
    private static final String SELECIONAR_TODOS_USUARIOS = "select * from " + StringsNomes.getTabelaUsuarios();
    private static final String SELECIONAR_TODOS_VIAGENS = "select * from " + StringsNomes.getTabelaViagens();
    private static final String SELECIONAR_TODOS_METODOS_PAGAMENTO = "select * from " + StringsNomes.getTabelaMetodospagamento();
    private static final String SELECIONAR_TODOS_TIPO_PAGAMENTO = "select * from " + StringsNomes.getTabelaTiposhospedagem();


    //DADOS
    private static final String SELECIONAR_DADOS_VIAGENS = "select v._id, v.vi_nome, v.vi_local, v.vi_dtini, v.vi_dtfim, t.tr_nome, v.vi_transporte, h.ho_nome, v.vi_hospedagem, v.vi_valortotal from viagens v inner join tipostransporte t on t._id = v.tr_id inner join tiposhospedagem h on h._id = v.ho_id";
    private static final String SELECIONAR_DADOS_METODOS_PAGAMENTO = "select mp._id, v.dv_id, t.tp_id, mp.me_detalhe, mp.me_valor, mp.me_vencimento from metodospagamento mp inner join tipopagamento t on t._id = mp.tp_id inner join viagens v on v._id = mp.dv_id ";


    //ID
    private static final String SELECIONAR_ID_USUARIO = "select * from " + StringsNomes.getTabelaUsuarios() + " where _id = ";
    private static final String SELECIONAR_ID_VIAGENS = "select * from " + StringsNomes.getTabelaViagens() + " where _id = ";
    private static final String SELECIONAR_ID_METODOS_PAGAMENTO = "select * from " + StringsNomes.getTabelaMetodospagamento() + "where _id = ";
    private static final String SELECIONAR_ID_TIPO_PAGAMENTO = "select * from " + StringsNomes.getTabelaTipospagamento() + " where _id = ";


    //SPINNER
    private static final String SELECIONAR_SPINNER_USUARIO = "select " + StringsNomes.getID() + ", " + StringsNomes.getUsNome() + " from " + StringsNomes.getTabelaUsuarios();
    private static final String SELECIONAR_SPINNER_VIAGENS = "select " + StringsNomes.getID() + ", " + StringsNomes.getViNome() + " from " + StringsNomes.getTabelaViagens();
    private static final String SELECIONAR_SPINNER_TIPO_PAGAMENTO = "select " + StringsNomes.getID() + ", " + StringsNomes.getTpNome() + " from " + StringsNomes.getTabelaTipospagamento();


    //CAMPO ISOLADO
    private static final String SELECIONAR_EMAIL_USUARIO = "select * from " + StringsNomes.getTabelaUsuarios() + " where " + StringsNomes.getUsEmail() + " = ";



    public static String getAtualizarWhere() {
        return ATUALIZAR_WHERE;
    }

    public static String getSelecionarTodosUsuarios() {
        return SELECIONAR_TODOS_USUARIOS;
    }

    public static String getSelecionarTodosViagens() {
        return SELECIONAR_TODOS_VIAGENS;
    }

    public static String getSelecionarTodosMetodosPagamento() {
        return SELECIONAR_TODOS_METODOS_PAGAMENTO;
    }

    public static String getSelecionarTodosTipoPagamento() {
        return SELECIONAR_TODOS_TIPO_PAGAMENTO;
    }

    public static String getSelecionarDadosViagens() {
        return SELECIONAR_DADOS_VIAGENS;
    }

    public static String getSelecionarDadosMetodosPagamento() {
        return SELECIONAR_DADOS_METODOS_PAGAMENTO;
    }

    public static String getSelecionarIdUsuario() {
        return SELECIONAR_ID_USUARIO;
    }

    public static String getSelecionarIdViagens() {
        return SELECIONAR_ID_VIAGENS;
    }

    public static String getSelecionarIdMetodosPagamento() {
        return SELECIONAR_ID_METODOS_PAGAMENTO;
    }

    public static String getSelecionarIdTipoPagamento() {
        return SELECIONAR_ID_TIPO_PAGAMENTO;
    }

    public static String getSelecionarSpinnerUsuario() {
        return SELECIONAR_SPINNER_USUARIO;
    }

    public static String getSelecionarSpinnerViagens() {
        return SELECIONAR_SPINNER_VIAGENS;
    }

    public static String getSelecionarSpinnerTipoPagamento() {
        return SELECIONAR_SPINNER_TIPO_PAGAMENTO;
    }

    public static String getSelecionarEmailUsuario() {
        return SELECIONAR_EMAIL_USUARIO;
    }
}
