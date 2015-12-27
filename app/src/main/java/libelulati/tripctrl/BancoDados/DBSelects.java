package libelulati.tripctrl.BancoDados;

public class DBSelects {

    private static final String ATUALIZAR_WHERE = "_id = ?";

    private static final String SELECIONAR_TODOS_USUARIOS = "select * from " + StringsNomes.getTabelaUsuarios();
    private static final String SELECIONAR_ID_USUARIO = "select * from " + StringsNomes.getTabelaUsuarios() + " where _id = ";
    private static final String SELECIONAR_SPINNER_USUARIO = "select _ID, US_NOME from " + StringsNomes.getTabelaUsuarios();
    private static final String SELECIONAR_EMAIL_USUARIO = "select * from " + StringsNomes.getTabelaUsuarios() + " where " + StringsNomes.getUsEmail() + " = ";
    private static final String SELECIONAR_LISTA_VIAGENS = "select * from " + StringsNomes.getTabelaViagens();
    private static final String SELECIONAR_ID_VIAGENS = "select * from " + StringsNomes.getTabelaViagens() + " where _id = ";
    private static final String SELECIONAR_SPINNER_VIAGENS = "select _id, vi_nome from " + StringsNomes.getTabelaViagens();
    private static final String SELECIONAR_DADOS_VIAGENS = "SELECT v._id, v.vi_nome, v.vi_local, v.vi_dtini, v.vi_dtfim, t.tr_nome, v.vi_transporte, h.ho_nome, v.vi_hospedagem, v.vi_valortotal FROM viagens v INNER JOIN tipostransporte t on t._id = v.tr_id INNER JOIN tiposhospedagem h on h._id = v.ho_id";
    private static final String SELECIONAR_DADOS_METODOS_PAGAMENTO = "SELECT mp._id, v.dv_id, t.tp_id, mp.me_detalhe, mp.me_valor, mp.me_vencimento FROM metodospagamento mp INNER JOIN tipopagamento t on t._id = mp.tp_id INNER JOIN viagens v on v._id = mp.dv_id ";
    private static final String SELECIONAR_LISTA_METODOS_PAGAMENTO = "select * from " +StringsNomes.getTabelaMetodospagamento();
    private static final String SELECIONAR_ID_METODOS_PAGAMENTO = "select * from " + StringsNomes.getTabelaMetodospagamento() + "where _id = ";
    private static final String SELECIONAR_LISTA_TIPO_PAGAMENTO = "select * from " + StringsNomes.getTabelaTiposhospedagem();
    private static final String SELECIONAR_SPINNER_TIPO_PAGAMENTO = "select _id, tp_nome from " + StringsNomes.getTabelaTipospagamento();
    private static final String SELECIONAR_ID_TIPO_PAGAMENTO = "select _id, tp_nome from " + StringsNomes.getTabelaTipospagamento() + "WHERE _id=";


    public static String getAtualizarWhere() {
        return ATUALIZAR_WHERE;
    }

    public static String getSelecionarSpinnerUsuario(){return SELECIONAR_SPINNER_USUARIO;}


    public static String getSelecionarTodosUsuarios() {
        return SELECIONAR_TODOS_USUARIOS;
    }
    public static String getSelecionarIdUsuario() {
        return SELECIONAR_ID_USUARIO;
    }
    public static String getSelecionarEmailUsuario() {
        return SELECIONAR_EMAIL_USUARIO;
    }
    public static String getSelecionarListaViagens() {
        return SELECIONAR_LISTA_VIAGENS;
    }
    public static String getSelecionarIdViagens() {
        return SELECIONAR_ID_VIAGENS;
    }
    public static String getSelecionarDadosViagens() {return SELECIONAR_DADOS_VIAGENS;    }
    public static String getSelecionarSpinnerViagens(){return SELECIONAR_SPINNER_VIAGENS;}
    public static String getSelecionarDadosMetodosPagamento() {return  SELECIONAR_DADOS_METODOS_PAGAMENTO; }
    public static String getSelecionarListaMetodosPagamento() {return SELECIONAR_LISTA_METODOS_PAGAMENTO;}
    public static String getSelecionarIdMetodosPagamento(){return SELECIONAR_ID_METODOS_PAGAMENTO;}
    public static String getSelecionarListaTipoPagamento(){return SELECIONAR_LISTA_TIPO_PAGAMENTO;}
    public static String getSelecionarIdTipoPagamento(){return SELECIONAR_ID_TIPO_PAGAMENTO;}
    public static String getSelecionarSpinnerTipoPagamento(){return SELECIONAR_ID_TIPO_PAGAMENTO;}



}
