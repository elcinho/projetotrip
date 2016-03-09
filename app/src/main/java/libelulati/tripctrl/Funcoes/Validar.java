package libelulati.tripctrl.Funcoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {
    public static boolean ValidarNome(String nome){
        int num = 0, seq = 0, esp = 0;
        String espaco = " ";
        String numeros = "0123456789";

        if(nome.length() < 4)
            return false;
        else{
            for(int i = 0; i < nome.length(); i++){
                for(int j = 0; j < espaco.length(); j++){
                    if(i == 0 || i == nome.length() - 1){
                        if(nome.charAt(i) == espaco.charAt(j)){
                            esp++;
                        }
                    }
                }
            }
            for(int i = 0; i < nome.length(); i++){
                for(int j = 0; j < numeros.length(); j++){
                    if(nome.charAt(i) == numeros.charAt(j))
                        num++;
                }
            }
            for (int i = 0; i < nome.length() - 1; i++) {
                if(nome.charAt(i) == nome.charAt(i+1))
                    seq++;
            }
            return !(num > 0 || seq > 2);
        }
    }

    public static boolean ValidarEmail(int tamanho, String email) {
        if (tamanho < 5) {
            return false;
        } else {
            Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
            Matcher m = p.matcher(email);
            return m.find();
        }
    }

    public static boolean ValidarUsuarioSenha (String email_dig, String senha_dig, String email_cad, String senha_cad){
        if(email_cad.equals(email_dig)){
            return senha_cad.equals(senha_dig);
        } else {
            return false;
        }
    }

    public static boolean ValidarDataNascimento(Date data) {
        Date atual = new Date();
        long tempo = 0;
        long limite = 471726000; //15 anos
        int indicador = 0;

        if (data.getTime() > atual.getTime()) {
            indicador = 0;
        } else {
            tempo = (atual.getTime() - data.getTime()) / 1000;
            if (tempo < limite) {
                indicador = 0;
            } else {
                indicador = 1;
            }
        }
        return indicador == 1;
    }

    public static boolean ValidarDataInicio(Date data){
        Date atual = new Date();
        if(atual.getTime() <= data.getTime()){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean ValidarDataFim(Date inicio, Date fim){
        if(inicio.getTime() < fim.getTime()){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean ValidarValor(double valor){
        double vmin = 0.99;
        double vmax = 999999.99;
        if(valor < vmin || valor > vmax){
            return false;
        }
        else{
            return true;
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






}
