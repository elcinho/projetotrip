package libelulati.tripctrl.Funcoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    /*
     * Função Validar V0001 - Validação de nomes próprios.
     *
     */

    public static boolean ValidarNome(String nome){
        int num = 0, seq = 0, esp = 0;
        String espaco = " ";
        String numeros = "0123456789";

        //verifica se tamanho é menor que 5
        if(nome.length() < 5)
            return false;
        else{
            //verifica se o primeiro ou o último caractere é espaço em branco
            for(int i = 0; i < nome.length(); i++){
                for(int j = 0; j < espaco.length(); j++){
                    if(i == 0 || i == nome.length() - 1){
                        if(nome.charAt(i) == espaco.charAt(j)){
                            esp++;
                        }
                    }
                }
            }
            //verifica se possui números
            for(int i = 0; i < nome.length(); i++){
                for(int j = 0; j < numeros.length(); j++){
                    if(nome.charAt(i) == numeros.charAt(j))
                        num++;
                }
            }
            //verifica se foi digitado uma sequencia
            for (int i = 0; i < nome.length() - 1; i++) {
                if(nome.charAt(i) == nome.charAt(i+1))
                   seq++;
            }
            return !(num > 0 || seq > 2);
        }
    }

    /*
     * Função Validar V0002 - Validação de e-mails.
     *
     */
    public static boolean ValidarEmail(int tamanho, String email) {
        //verifica se tamanho é menor que 5
        if (tamanho < 5) {
            return false;
        } else {
            //regex para verificar o formato do email: (letras_numeros_underscore_ponto@letras_numeros_underscore_ponto.letras)
            Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
            Matcher m = p.matcher(email);
            return m.find();
        }
    }

    /*
     * Função Validar V0003 - Validação de telefones.
     *
     */

    public static boolean ValidarTelefone(int tamanho, String telefone) {
        int seq = 0;
        String numeros = "0123456789";
        //verifica se tamanho é menor que 8 caracteres
        if (tamanho < 8) {
            return false;
        } else {
            //verifica se foi digitado sequencias
            for (int i = 0; i < tamanho - 1; i++) {
                if(telefone.charAt(i) == telefone.charAt(i + 1))
                    seq ++;
            }
            return seq < 5;
        }
    }

    /*
     * Função Validar V0004 - Validação de código de usuário.
     *
     */

    public static boolean ValidarCodUsuario(String codDigi, String codCada) {
        //verifica se o código digitado é igual ao código cadastrado
        return codCada.equals(codDigi);
    }

    /*
     * Função Validar V0005 - Validação de autenticidade de usuário.
     *
     */

    public static boolean ValidarUsuarioSenha (String email_dig, String senha_dig, String email_cad, String senha_cad){
        //verifica se o e-mail digitado é igual ao e-mail cadastrado
        if(email_cad.equals(email_dig)){
            //verifica se senha digitado é igual a senha cadastrada
            return senha_cad.equals(senha_dig);
        } else {
            return false;
        }
    }

    /*
     * Função Validar V0006 - Validação de senha.
     *
     */

    public static boolean ValidarSenha(String senha){
        //verifica se a senha possui entre 6 a 10 caracteres
        if(senha.length() < 6 || senha.length() > 10){
            return false;
        }
        else{
            String numeros = "0123456789";
            String maiusculas = "ABCDEFGHIJKLMNOPQRSTUWXYZ";
            String minusculas = "abcdefghijklmnopqrstuwxyz";
            String espaco = " ";
            int contnum = 0, contmai = 0, contmin = 0, contesp = 0;

            //verifica se foi digitado espaço
            for(int i = 0; i < senha.length(); i++){
                for(int j = 0; j < espaco.length(); j++){
                    if(senha.charAt(i) == espaco.charAt(j)){
                        contesp ++;
                    }
                    break;
                }
            }
            //verifica se foi digitado algum caractere numérico
            for (int i = 0; i < senha.length(); i++ ){
                for (int j = 0; j < numeros.length(); j++){
                    if(senha.charAt(i) == numeros.charAt(j))
                        contnum ++;
                }
            }
            //verifica se foi digitado algum caractere maiúsculo
            for (int i = 0; i < senha.length(); i++ ){
                for (int j = 0; j < maiusculas.length(); j++){
                    if(senha.charAt(i) == maiusculas.charAt(j))
                        contmai ++;
                }
            }
            //verifica se foi digitado algum caractere minúsculo
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

    /*
     * Função Validar V0007 - Validação de confirme senha.
     *
     */

    public static boolean ValidarConfirmeSenha(String senha, String confirmesenha) {
            //verifica se a senha digitada é igual a confirmação da senha
            return senha.equals(confirmesenha);
    }

    /*
     * Função Validar V0008 - Validação de tempo de expiração do código de alterar senha.
     *
     */

    public static boolean ValidarTempoCodigo(String data) {
        //verifica se o tempo entre o envio do código e a digitação do código é maior que 5 minutos.
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

    /*
     * Função Validar V0009 - Validação de código alterar senha.
     *
     */

    public static boolean ValidarCodigoAltSenha(String coddig, String codcad) {
        //verifica se o código digitado corresponde ao código enviado
        return codcad.equals(coddig);
    }

    /*
     * Função Validar V0010 - Validação de data de nascimento.
     *
     */

    public static boolean ValidarDataNascimento(Date data) {
        //verifica se o usuário é maior que 15 anos
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

    /*
     * Função Validar V0011 - Validação de data inicial.
     *
     */

    public static boolean ValidarDataInicio(Date data){
        // verifica se a data digitada é anterior à data atual
        Date atual = new Date();
        if(atual.getTime() <= data.getTime()){
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * Função Validar V0012 - Validação de data final.
     *
     */

    public static boolean ValidarDataFim(Date inicio, Date fim){
        // verifica se a data digitada é anterior à data inicial
        if(inicio.getTime() < fim.getTime()){
            return true;
        }
        else{
            return false;
        }
    }

    /*
     * Função Validar V0013 - Validação de valor total.
     *
     */

    public static boolean ValidarValor(double valor){
        //verifica se o valor digitado está entre 0.99 e 999999.99
        double vmin = 0.99;
        double vmax = 999999.99;
        if(valor < vmin || valor > vmax){
            return false;
        }
        else{
            return true;
        }
    }

    /*
     * Função Validar V0014 - Validação de código de área.
     *
     */

    public static boolean ValidarCodArea(String codarea){
        //verifica se possui menos que 2 caracteres
        if(codarea.length() < 2){
            return false;
        }
        else {
            //verifica se foi digitado caracteres não numericos
            String numeros = "0123456789";
            int cont = 0;
            for(int i = 0; i < codarea.length(); i++){
                for(int j = 0; j < numeros.length(); j++){
                    if(codarea.charAt(i) != numeros.charAt(j)){
                        cont++;
                    }
                }
            }
            return cont != 0;
        }
    }
}
