package libelulati.tripctrl.Funcoes;

import java.util.Random;

public class Codigos {
    public String AltSenha() {

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
