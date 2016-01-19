package libelulati.tripctrl.Funcoes;


import java.text.SimpleDateFormat;
import java.util.Date;


public class Funcao {

    /*
     * Função F0001: Pega a data atual do telefone.
     *
     */

    public static String DataAtual() {

        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String dataatual = formatador.format(data);

        return dataatual;
    }
}