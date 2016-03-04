package libelulati.tripctrl.Dados;

public class Nomes {
    // NOME E VERSÃO DO BANCO DE DADOS
    private static final String NOME_BANCO = "tripctrl";
    private static final int VERSAO = 1;

    // NOMES DAS TABELAS
    private static final String TABELA_CATEGORIAS = "categorias";
    private static final String TABELA_USUARIOS = "usuarios";
    private static final String TABELA_TIPOSPAGAMENTO = "tipospagamento";
    private static final String TABELA_VIAGENS = "viagens";
    private static final String TABELA_PLANEJAMENTOS = "planejamentos";
    private static final String TABELA_PAGAMENTOS = "pagamentos";
    private static final String TABELA_GASTOS = "gastos";
    private static final String TABELA_CNOTIFICACAO = "cnotificacao";

    // CAMPO ID : CHAVE PRIMÁRIA DE TODAS AS TABELAS
    private static final String ID = "_id";

    //CAMPOS DE CATEGORIAS
    private static final String CA_NOME = "ca_nome";
    private static final String CA_ID = "ca_id";

    //CAMPOS DE USUÁRIO

    private static final String US_NOME = "us_nome";
    private static final String US_DTNASC = "us_dtnasc";
    private static final String US_EMAIL = "us_email";
    private static final String US_SENHA = "us_senha";
    private static final String US_ID = "us_id";
    private static final String US_USO = "us_uso";
    private static final String US_SEMSENHA = "us_semsenha";


    //CAMPOS DE TIPOS DE PAGAMENTO
    private static final String TP_NOME = "tp_nome";
    private static final String TP_ID = "tp_id";


    //CAMPOS DE VIAGENS
    private static final String VI_NOME = "vi_nome";
    private static final String VI_DTINI = "vi_dtini";
    private static final String VI_DTFIM = "vi_dtfim";
    private static final String VI_VALORTOTAL = "vi_valortotal";
    private static final String VI_ID = "vi_id";

    //CAMPOS DE PLANEJAMENTO
    private static final String PL_VALORCAT = "pl_valorcat";
    private static final String PL_ID = "pl_id";

    //CAMPOS DE PAGAMENTOS
    private static final String PA_DESCRICAO = "pa_descricao";
    private static final String PA_VALOR = "pa_valor";
    private static final String PA_VENCIMENTO = "pa_vencimento";
    private static final String PA_ID = "pa_id";

    //CAMPOS DE GASTOS
    private static final String GA_VALOR = "ga_valor";
    private static final String GA_DESCRICAO = "ga_descricao";
    private static final String GA_ID = "ga_id";
    private static final String GA_DATA = "ga_data";

    //CAMPOS DE CNOTIFICACAO
    private static final String CN_ATIVAR = "cn_ativar";
    private static final String CN_TODAS = "cn_todas";
    private static final String CN_VIAGENS = "cn_viagens";
    private static final String CN_PAGAMENTO = "cn_pagamento";
    private static final String CN_PLANEJAMENTO = "cn_planejamento";
    private static final String CN_GASTOS = "cn_gastos";


    //GETTES
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

    public static String getTabelaTipospagamento() {
        return TABELA_TIPOSPAGAMENTO;
    }

    public static String getTabelaViagens() {
        return TABELA_VIAGENS;
    }

    public static String getTabelaPlanejamentos() {
        return TABELA_PLANEJAMENTOS;
    }

    public static String getTabelaPagamentos() {
        return TABELA_PAGAMENTOS;
    }

    public static String getTabelaGastos() {
        return TABELA_GASTOS;
    }

    public static String getTabelaCnotificacao() {
        return TABELA_CNOTIFICACAO;
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

    public static String getUsNome() {
        return US_NOME;
    }

    public static String getUsDtnasc() {
        return US_DTNASC;
    }

    public static String getUsEmail() {
        return US_EMAIL;
    }

    public static String getUsSenha() {
        return US_SENHA;
    }

    public static String getUsId() {
        return US_ID;
    }

    public static String getUsUso() {
        return US_USO;
    }

    public static String getUsSemsenha() {
        return US_SEMSENHA;
    }

    public static String getTpNome() {
        return TP_NOME;
    }

    public static String getTpId() {
        return TP_ID;
    }

    public static String getViNome() {
        return VI_NOME;
    }

    public static String getViDtini() {
        return VI_DTINI;
    }

    public static String getViDtfim() {
        return VI_DTFIM;
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

    public static String getPlId() {
        return PL_ID;
    }

    public static String getPaDescricao() {
        return PA_DESCRICAO;
    }

    public static String getPaValor() {
        return PA_VALOR;
    }

    public static String getPaVencimento() {
        return PA_VENCIMENTO;
    }

    public static String getPaId() {
        return PA_ID;
    }

    public static String getGaValor() {
        return GA_VALOR;
    }

    public static String getGaDescricao() {
        return GA_DESCRICAO;
    }

    public static String getGaId() {
        return GA_ID;
    }

    public static String getGaData() {
        return GA_DATA;
    }

    public static String getCnAtivar() {
        return CN_ATIVAR;
    }

    public static String getCnTodas() {
        return CN_TODAS;
    }

    public static String getCnViagens() {
        return CN_VIAGENS;
    }

    public static String getCnPagamento() {
        return CN_PAGAMENTO;
    }

    public static String getCnPlanejamento() {
        return CN_PLANEJAMENTO;
    }

    public static String getCnGastos() {
        return CN_GASTOS;
    }
}
