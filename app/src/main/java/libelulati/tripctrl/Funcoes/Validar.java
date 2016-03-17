package libelulati.tripctrl.Funcoes;

import android.content.Context;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import libelulati.tripctrl.R;

public class Validar {

    Context context;

    String caracMin = "abcdefghijklmnopqrstuvwxyz";
    String caracMai = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String numeros = "0123456789";
    Character espaco = ' ';
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    public Validar(Context context) {
        this.context = context;
    }

    public boolean ValidarEmail(String email, EditText ed_email) {
        if (email.length() < 5) {
            return false;
        } else {
            Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
            Matcher m = p.matcher(email);
            boolean retorno = m.find();
            if(retorno){
                return true;
            }
            else {
                ed_email.setError(context.getResources().getString(R.string.validar_email));
                return false;
            }
        }
    }

    public boolean ValidarUsuarioSenha (String email_dig, String senha_dig, String email_cad, String senha_cad){
        if(email_cad.equals(email_dig)){
            return senha_cad.equals(senha_dig);
        } else {
            return false;
        }
    }

    public static boolean ValidarTempoCodigo(String data) {
        Date date = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataatual = formatador.format(date);
        long tempo = 0;
        try {
            Date anterior = new Date(formatador.parse(data).getTime());
            Date atual = new Date(formatador.parse(dataatual).getTime());
            tempo = atual.getTime() - anterior.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tempo <= 300000;
    }

    public static boolean ValidarCodigoAltSenha(String coddig, String codcad) {
        return codcad.equals(coddig);
    }

    public boolean ValidarTexto(String texto, EditText ed_texto) {
        int seq = 0;
        if (texto.charAt(0) == espaco) {
            ed_texto.setError(context.getResources().getString(R.string.validar_texto_espaco));
            return false;
        } else {
            for (int i = 0; i < texto.length() - 1; i++) {
                if(texto.charAt(i) == texto.charAt(i+1)){
                    seq++;
                }
            }
            if (seq > 3) {
                ed_texto.setError(context.getResources().getString(R.string.validar_texto_sequencia));
                return false;
            } else {
                if (texto.length() < 5) {
                    ed_texto.setError(context.getResources().getString(R.string.validar_texto_tamanho));
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public  boolean ValidarDataInicio(String dtini, EditText ed_dtini){
        Date atual = new Date();
        boolean retorno = false;
        try {
            Date data = (Date)formatador.parse(dtini);
            if(atual.getTime() <= data.getTime()){
                retorno =  true;
            }
            else{
                ed_dtini.setError(context.getResources().getString(R.string.validar_datainicial));
                retorno =  false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public boolean ValidarDataFim(String dtini, String dtfim, EditText ed_dtfim){
        boolean retorno = false;
        try {
            Date inicio = (Date)formatador.parse(dtini);
            Date fim = (Date)formatador.parse(dtfim);

            if(inicio.getTime() < fim.getTime()){
                retorno =  true;
            }
            else{
                ed_dtfim.setError(context.getResources().getString(R.string.validar_datafinal));
                retorno =  false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public boolean ValidarValorTotal(String valorTotal, EditText ed_valorTotal){
        double valor;
        if(valorTotal.length() == 0){
            valor = 0;
        }
        else {
            valor = Double.parseDouble(valorTotal);
        }
        double vmin = 99.99;
        double vmax = 999999.99;
        if(valor == 0){
            return true;
        }
        if(valor < vmin || valor > vmax){
            ed_valorTotal.setError(context.getResources().getString(R.string.validar_valortotal));
            return false;
        }
        else{
            return true;
        }
    }

    public boolean ValidarDataTransacao(String dataTransacao, String dataInicioViagem, String dataFimViagem, EditText ed_dataTransacao){
        boolean retorno = false;
        try {
            Date transacao = (Date)formatador.parse(dataTransacao);
            Date inicio = (Date)formatador.parse(dataInicioViagem);
            Date fim = (Date)formatador.parse(dataFimViagem);

            if(transacao.getTime() < inicio.getTime() || transacao.getTime() > fim.getTime() ){
                retorno = false;
                ed_dataTransacao.setError(context.getResources().getString(R.string.validar_datatransacao));
            }
            else {
                retorno = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public boolean ValidarValor(String valor, EditText ed_valor){
        if(valor.length() == 0){
            ed_valor.setError(context.getResources().getString(R.string.validar_valor));
            return false;
        }
        else {
            return true;
        }
    }




}
