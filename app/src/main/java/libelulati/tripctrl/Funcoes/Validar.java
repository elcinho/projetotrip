package libelulati.tripctrl.Funcoes;

public class Validar {
    public static boolean ValidarNome(String nome){
        int num = 0, seq = 0, esp = 0;
        String espaco = " ";
        String numeros = "0123456789";

        if(nome.length() < 5)
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
}
