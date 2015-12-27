package libelulati.tripctrl.Funcoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validar {

    public static boolean ValidarNome(String nome){
       int num = 0, seq = 0;
        if(nome.length() < 5)
            return false;
        else{
            String numeros = "0123456789";
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

    public static boolean ValidarTelefone(int tamanho, String telefone) {
        int cont = 0;
        if (tamanho < 8) {
            return false;
        } else {
            for (int i = 0; i < tamanho - 1; i++) {
                if(telefone.charAt(i) == telefone.charAt(i + 1))
                    cont ++;
            }
            return cont < 5;
        }
    }

    public static boolean ValidarCodUsuario(String codDigi, String codCada) {
        return codCada.equals(codDigi);
    }

    public static boolean ValidarUsuarioSenha (String email_dig, String senha_dig, String email_cad, String senha_cad){
        if(email_cad.equals(email_dig)){
            return senha_cad.equals(senha_dig);
        } else {
            return false;
        }
    }

    public static boolean ValidarSenha(String senha){
        if(senha.length() < 6 || senha.length() > 10){
            return false;
        }
        else{
            String numeros = "0123456789";
            String maiusculas = "ABCDEFGHIJKLMNOPQRSTUWXYZ";
            String minusculas = "abcdefghijklmnopqrstuwxyz";
            String espaco = " ";
            int contnum = 0, contmai = 0, contmin = 0, contesp = 0;

            for(int i = 0; i < senha.length(); i++){
                for(int j = 0; j < espaco.length(); j++){
                    if(senha.charAt(i) == espaco.charAt(j)){
                        contesp ++;
                    }
                    break;
                }
            }
            for (int i = 0; i < senha.length(); i++ ){
                for (int j = 0; j < numeros.length(); j++){
                    if(senha.charAt(i) == numeros.charAt(j))
                        contnum ++;
                }
            }
            for (int i = 0; i < senha.length(); i++ ){
                for (int j = 0; j < maiusculas.length(); j++){
                    if(senha.charAt(i) == maiusculas.charAt(j))
                        contmai ++;
                }
            }
            for (int i = 0; i < senha.length(); i++ ){
                for (int j = 0; j < minusculas.length(); j++){
                    if(senha.charAt(i) == minusculas.charAt(j))
                        contmin ++;
                }
            }

            if (contesp > 0){
                return false;
            }
            else {
                return contnum > 0 && contmai > 0 && contmin > 0;
            }
        }
    }

    public static boolean ValidarConfirmeSenha(String senha, String confirmesenha) {
        return senha.equals(confirmesenha);
    }

    public static boolean ValidarTempoCodigo(String data) {
        String dataatual = Funcao.DataAtual();
        long tempo = 0;

        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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

    public static boolean ValidarDataNascimento(Date data) {
        Date atual = new Date();
        long tempo = 0;
        long limite = 471726000;
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
}
