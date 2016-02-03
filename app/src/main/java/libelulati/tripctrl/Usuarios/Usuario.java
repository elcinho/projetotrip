package libelulati.tripctrl.Usuarios;

public class Usuario {

    private int us_id;
    private int us_uso;
    private String us_nome;
    private String us_email;
    private String us_dtnasc;
    private String us_senha;

    public Usuario() {
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public int getUs_uso() {
        return us_uso;
    }

    public void setUs_uso(int us_uso) {
        this.us_uso = us_uso;
    }

    public String getUs_nome() {
        return us_nome;
    }

    public void setUs_nome(String us_nome) {
        this.us_nome = us_nome;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    public String getUs_dtnasc() {
        return us_dtnasc;
    }

    public void setUs_dtnasc(String us_dtnasc) {
        this.us_dtnasc = us_dtnasc;
    }

    public String getUs_senha() {
        return us_senha;
    }

    public void setUs_senha(String us_senha) {
        this.us_senha = us_senha;
    }
}
