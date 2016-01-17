package libelulati.tripctrl.Funcoes;

import java.util.Random;

public class Codigos {

    /*
     * Função C0001: Código aleatório e unico por usuário
     *
     */

    public String CodUsuario(){
        //gera um código aleatório de 10 caracteres alfanuméricos

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuwxyz1234567890";

        Random random = new Random();

        String chave = "";
        int index = -1;
        for(int i = 0; i < 10; i++){
            index = random.nextInt(caracteres.length());
            chave += caracteres.substring(index,index +1);
        }

        return chave;
    }

    /*
     * Função C0002: Código aleatório para alteração de senha
     *
     */

    public String AltSenha() {
        //gera um código aleatório de 6 caracteres alfanuméricos

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUWXYZabcdefghijklmnopqrstuwxyz1234567890";
        Random random = new Random();

        String chave = "";
        int index = -1;
        for (int i = 0; i < 6; i++) {
            index = random.nextInt(caracteres.length());
            chave += caracteres.substring(index, index + 1);
        }

        return chave;
    }
}
