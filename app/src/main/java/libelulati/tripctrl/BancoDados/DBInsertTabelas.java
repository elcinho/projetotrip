package libelulati.tripctrl.BancoDados;

public class DBInsertTabelas {

        private static final String INSERT_TABELA_CATEGORIAS_TRANSPORTE = "insert into categorias(ca_nome) values ('TRANSPORTE')";
        private static final String INSERT_TABELA_CATEGORIAS_HOSPEDAGEM = "insert into categorias(ca_nome) values ('HOSPEDAGEM')";
        private static final String INSERT_TABELA_CATEGORIAS_ALIMENTACAO = "insert into categorias(ca_nome) values ('ALIMENTAÇÃO')";
        private static final String INSERT_TABELA_CATEGORIAS_LAZER =  "insert into categorias(ca_nome) values ('LAZER')";
        private static final String INSERT_TABELA_CATEGORIAS_COMPRAS = "insert into categorias(ca_nome) values ('COMPRAS')";

        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_HOTEL = "insert into tiposhospedagem(ho_nome) values ('Hotel')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_HOSTEL = "insert into tiposhospedagem(ho_nome) values ('Hostel')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_POUSADA = "insert into tiposhospedagem(ho_nome) values ('Pousada')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_ACAMPAMENTO =  "insert into tiposhospedagem(ho_nome) values ('Acampamento')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_ALUGADO = "insert into tiposhospedagem(ho_nome) values ('Alugado')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_PROPRIO =  "insert into tiposhospedagem(ho_nome) values ('Próprio')";
        private static final String INSERT_TABELA_TIPOSHOSPEDAGEM_OUTRO = "insert into tiposhospedagem(ho_nome) values ('Outro')";

        private static final String INSERT_TABELA_TIPOSPAGAMENTO_DINHEIRO = "insert into tipospagamento (tp_nome) values ('Dinheiro')";
        private static final String INSERT_TABELA_TIPOSPAGAMENTO_CC = "insert into tipospagamento (tp_nome) values ('Cartão de Crédito')";
        private static final String INSERT_TABELA_TIPOSPAGAMENTO_CD = "insert into tipospagamento (tp_nome) values ('Cartão de Débito')";
        private static final String INSERT_TABELA_TIPOSPAGAMENTO_VP =  "insert into tipospagamento (tp_nome) values ('Vale Promocional')";
        private static final String INSERT_TABELA_TIPOSPAGAMENTO_TERCEIROS =  "insert into tipospagamento (tp_nome) values ('Terceiros')";
        private static final String INSERT_TABELA_TIPOSPAGAMENTO_OUTRO =  "insert into tipospagamento (tp_nome) values ('Outro')";

        private static final String INSERT_TABELA_TIPOSTRANSPORTE_AVIAO = "insert into tipostransporte(tr_nome) values ('Avião')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_NAVIO =  "insert into tipostransporte(tr_nome) values ('Navio')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_ONIBUS =  "insert into tipostransporte(tr_nome) values ('Ônibus')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_EXCURSAO =  "insert into tipostransporte(tr_nome) values ('Excursão')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_VP =  "insert into tipostransporte(tr_nome) values ('Veículo Próprio')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_VA = "insert into tipostransporte(tr_nome) values ('Veículo Alugado')";
        private static final String INSERT_TABELA_TIPOSTRANSPORTE_OUTRO = "insert into tipostransporte(tr_nome) values ('Outro')";

        private static final String INSERT_TABELA_USUARIO_GERAL = "insert into usuarios(us_cod, us_nome, us_dtnasc, us_email, us_latitude, us_longitude, us_codarea, us_telefone, us_senha, us_confirmesenha) values ('122015TRIPadm','Administrador', '2015-10-06', 'marcelaamelo1986@gmail.com', '102030', '302010', '+55 31', '971720145', 'teste123A', 'teste123A')";

        // getters


        public static String getInsertTabelaCategoriasTransporte() {
                return INSERT_TABELA_CATEGORIAS_TRANSPORTE;
        }

        public static String getInsertTabelaCategoriasHospedagem() {
                return INSERT_TABELA_CATEGORIAS_HOSPEDAGEM;
        }

        public static String getInsertTabelaCategoriasAlimentacao() {
                return INSERT_TABELA_CATEGORIAS_ALIMENTACAO;
        }

        public static String getInsertTabelaCategoriasLazer() {
                return INSERT_TABELA_CATEGORIAS_LAZER;
        }

        public static String getInsertTabelaCategoriasCompras() {
                return INSERT_TABELA_CATEGORIAS_COMPRAS;
        }

        public static String getInsertTabelaTiposhospedagemHotel() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_HOTEL;
        }

        public static String getInsertTabelaTiposhospedagemHostel() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_HOSTEL;
        }

        public static String getInsertTabelaTiposhospedagemPousada() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_POUSADA;
        }

        public static String getInsertTabelaTiposhospedagemAcampamento() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_ACAMPAMENTO;
        }

        public static String getInsertTabelaTiposhospedagemAlugado() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_ALUGADO;
        }

        public static String getInsertTabelaTiposhospedagemProprio() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_PROPRIO;
        }

        public static String getInsertTabelaTiposhospedagemOutro() {
                return INSERT_TABELA_TIPOSHOSPEDAGEM_OUTRO;
        }

        public static String getInsertTabelaTipospagamentoDinheiro() {
                return INSERT_TABELA_TIPOSPAGAMENTO_DINHEIRO;
        }

        public static String getInsertTabelaTipospagamentoCc() {
                return INSERT_TABELA_TIPOSPAGAMENTO_CC;
        }

        public static String getInsertTabelaTipospagamentoCd() {
                return INSERT_TABELA_TIPOSPAGAMENTO_CD;
        }

        public static String getInsertTabelaTipospagamentoVp() {
                return INSERT_TABELA_TIPOSPAGAMENTO_VP;
        }

        public static String getInsertTabelaTipospagamentoTerceiros() {
                return INSERT_TABELA_TIPOSPAGAMENTO_TERCEIROS;
        }

        public static String getInsertTabelaTipospagamentoOutro() {
                return INSERT_TABELA_TIPOSPAGAMENTO_OUTRO;
        }

        public static String getInsertTabelaTipostransporteAviao() {
                return INSERT_TABELA_TIPOSTRANSPORTE_AVIAO;
        }

        public static String getInsertTabelaTipostransporteNavio() {
                return INSERT_TABELA_TIPOSTRANSPORTE_NAVIO;
        }

        public static String getInsertTabelaTipostransporteOnibus() {
                return INSERT_TABELA_TIPOSTRANSPORTE_ONIBUS;
        }

        public static String getInsertTabelaTipostransporteExcursao() {
                return INSERT_TABELA_TIPOSTRANSPORTE_EXCURSAO;
        }

        public static String getInsertTabelaTipostransporteVp() {
                return INSERT_TABELA_TIPOSTRANSPORTE_VP;
        }

        public static String getInsertTabelaTipostransporteVa() {
                return INSERT_TABELA_TIPOSTRANSPORTE_VA;
        }

        public static String getInsertTabelaTipostransporteOutro() {
                return INSERT_TABELA_TIPOSTRANSPORTE_OUTRO;
        }

        public static String getInsertTabelaUsuarioGeral() {
                return INSERT_TABELA_USUARIO_GERAL;
        }
}
