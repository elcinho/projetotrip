package libelulati.tripctrl.BancoDados;

public class StringsNomes {

    private static final String NOME_BANCO = "tripctrl";
    private static final int VERSAO = 1;

    private static final String TABELA_CATEGORIAS = "categorias";
    private static final String TABELA_USUARIOS = "usuarios";
    private static final String TABELA_SUBCATEGORIAS = "subcategorias";
    private static final String TABELA_TIPOSTRANSPORTE = "tipostransporte";
    private static final String TABELA_TIPOSPAGAMENTO = "tipospagamento";
    private static final String TABELA_TIPOSHOSPEDAGEM = "tiposhospedagem";
    private static final String TABELA_VIAGENS = "viagens";
    private static final String TABELA_PLANEJAMENTOS = "planejamentos";
    private static final String TABELA_METODOSPAGAMENTO = "metodospagamento";
    private static final String TABELA_GASTOS = "gastos";

    private static final String VIEW_CATEGORIAS = "v_categorias";
    private static final String VIEW_USUARIOS = "v_usuarios";
    private static final String VIEW_SUBCATEGORIAS = "v_subcategorias";
    private static final String VIEW_TIPOSTRANSPORTE = "v_tipostransporte";
    private static final String VIEW_TIPOSPAGAMENTO = "v_tipospagamento";
    private static final String VIEW_TIPOSHOSPEDAGEM = "v_tiposhospedagem";
    private static final String VIEW_VIAGENS = "v_viagens";
    private static final String VIEW_PLANEJAMENTOS = "v_planejamentos";
    private static final String VIEW_METODOSPAGAMENTO = "v_metodospagamento";
    private static final String VIEW_GASTOS = "v_gastos";

    private static final String ID = "_id";

    private static final String CA_NOME = "ca_nome";
    private static final String CA_ID = "ca_id";

    private static final String US_COD = "us_cod";
    private static final String US_NOME = "us_nome";
    private static final String US_DTNASC = "us_dtnasc";
    private static final String US_EMAIL = "us_email";
    private static final String US_LATTUDE = "us_latitude";
    private static final String US_LONGITUDE = "us_longitude";
    private static final String US_CODAREA = "us_codarea";
    private static final String US_TELEFONE = "us_telefone";
    private static final String US_SENHA = "us_senha";
    private static final String US_CONFIRMESENHA = "us_confirmesenha";
    private static final String US_ID = "us_id";
    private static final String US_USO = "us_uso";

    private static final String SC_NOME = "sc_nome";
    private static final String SC_ID = "sc_id";

    private static final String TR_NOME = "tr_nome";
    private static final String TR_ID = "tr_id";

    private static final String TP_NOME = "tp_nome";
    private static final String TP_ID = "tp_id";

    private static final String HO_NOME = "ho_nome";
    private static final String HO_ID = "ho_id";

    private static final String VI_NOME = "vi_nome";
    private static final String VI_LOCAL = "vi_local";
    private static final String VI_DTINI = "vi_dtini";
    private static final String VI_DTFIM = "vi_dtfim";
    private static final String VI_TRANSPORTE = "vi_transporte";
    private static final String VI_HOSPEDAGEM = "vi_hospedagem";
    private static final String VI_VALORTOTAL = "vi_valortotal";
    private static final String VI_ID = "vi_id";

    private static final String PL_VALORCAT = "pl_valorcat";
    private static final String PL_VALORTOT = "pl_valortot";
    private static final String PL_ID = "pl_id";

    private static final String ME_DETALHE = "me_detalhe";
    private static final String ME_VALOR = "me_valor";
    private static final String ME_VENCIMENTO = "me_vencimento";
    private static final String ME_ID = "me_id";

    private static final String GA_VALORCAT = "ga_valorcat";
    private static final String GA_VALORTOT = "ga_valortot";
    private static final String GA_ID = "ga_id";


    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static int getVERSAO() {
        return VERSAO;
    }

    public static String getTabelaCategorias() {
        return TABELA_CATEGORIAS;
    }

    public static String getTabelaUsuarios() {
        return TABELA_USUARIOS;
    }

    public static String getTabelaSubcategorias() {
        return TABELA_SUBCATEGORIAS;
    }

    public static String getTabelaTipostransporte() {
        return TABELA_TIPOSTRANSPORTE;
    }

    public static String getTabelaTipospagamento() {
        return TABELA_TIPOSPAGAMENTO;
    }

    public static String getTabelaTiposhospedagem() {
        return TABELA_TIPOSHOSPEDAGEM;
    }

    public static String getTabelaViagens() {
        return TABELA_VIAGENS;
    }

    public static String getTabelaPlanejamentos() {
        return TABELA_PLANEJAMENTOS;
    }

    public static String getTabelaMetodospagamento() {
        return TABELA_METODOSPAGAMENTO;
    }

    public static String getTabelaGastos() {
        return TABELA_GASTOS;
    }

    public static String getID() {
        return ID;
    }

    public static String getCaNome() {
        return CA_NOME;
    }

    public static String getCaId() {
        return CA_ID;
    }

    public static String getUsCod() {
        return US_COD;
    }

    public static String getUsNome() {
        return US_NOME;
    }

    public static String getUsDtnasc() {
        return US_DTNASC;
    }

    public static String getUsEmail() {
        return US_EMAIL;
    }

    public static String getUsLattude() {
        return US_LATTUDE;
    }

    public static String getUsLongitude() {
        return US_LONGITUDE;
    }

    public static String getUsCodarea() {
        return US_CODAREA;
    }

    public static String getUsTelefone() {
        return US_TELEFONE;
    }

    public static String getUsSenha() {
        return US_SENHA;
    }

    public static String getUsConfirmesenha() {
        return US_CONFIRMESENHA;
    }

    public static String getUsId() {
        return US_ID;
    }

    public static String getScNome() {
        return SC_NOME;
    }

    public static String getScId() {
        return SC_ID;
    }

    public static String getTrNome() {
        return TR_NOME;
    }

    public static String getTrId() {
        return TR_ID;
    }

    public static String getTpNome() {
        return TP_NOME;
    }

    public static String getTpId() {
        return TP_ID;
    }

    public static String getHoNome() {
        return HO_NOME;
    }

    public static String getHoId() {
        return HO_ID;
    }

    public static String getViNome() {
        return VI_NOME;
    }

    public static String getViLocal() {
        return VI_LOCAL;
    }

    public static String getViDtini() {
        return VI_DTINI;
    }

    public static String getViDtfim() {
        return VI_DTFIM;
    }

    public static String getViTransporte() {
        return VI_TRANSPORTE;
    }

    public static String getViHospedagem() {
        return VI_HOSPEDAGEM;
    }

    public static String getViValortotal() {
        return VI_VALORTOTAL;
    }

    public static String getViId() {
        return VI_ID;
    }

    public static String getPlValorcat() {
        return PL_VALORCAT;
    }

    public static String getPlValortot() {
        return PL_VALORTOT;
    }

    public static String getPlId() {
        return PL_ID;
    }

    public static String getMeDetalhe() {
        return ME_DETALHE;
    }

    public static String getMeValor() {
        return ME_VALOR;
    }

    public static String getMeVencimento() {
        return ME_VENCIMENTO;
    }

    public static String getMeId() {
        return ME_ID;
    }

    public static String getGaValorcat() {
        return GA_VALORCAT;
    }

    public static String getGaValortot() {
        return GA_VALORTOT;
    }

    public static String getGaId() {
        return GA_ID;
    }

    public static String getViewCategorias() {
        return VIEW_CATEGORIAS;
    }

    public static String getViewUsuarios() {
        return VIEW_USUARIOS;
    }

    public static String getViewSubcategorias() {
        return VIEW_SUBCATEGORIAS;
    }

    public static String getViewTipostransporte() {
        return VIEW_TIPOSTRANSPORTE;
    }

    public static String getViewTipospagamento() {
        return VIEW_TIPOSPAGAMENTO;
    }

    public static String getViewTiposhospedagem() {
        return VIEW_TIPOSHOSPEDAGEM;
    }

    public static String getViewViagens() {
        return VIEW_VIAGENS;
    }

    public static String getViewPlanejamentos() {
        return VIEW_PLANEJAMENTOS;
    }

    public static String getViewMetodospagamento() {
        return VIEW_METODOSPAGAMENTO;
    }

    public static String getViewGastos() {
        return VIEW_GASTOS;
    }

    public static String getUsUso() {
        return US_USO;
    }
}
