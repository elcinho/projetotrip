package libelulati.tripctrl.Usuario;

public class Usuario {

    private int us_id;
    private String us_cod;
    private String us_nome;
    private String us_dtnasc;
    private String us_email;
    private String us_latitude;
    private String us_longitude;
    private String us_codarea;
    private String us_telefone;
    private String us_senha;
    private String us_confirmesenha;

    public Usuario() {
    }

    public Usuario(int us_id) {
        this.us_id = us_id;
    }

    public Usuario(String us_email) {
        this.us_email = us_email;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getUs_cod() {
        return us_cod;
    }

    public void setUs_cod(String us_cod) {
        this.us_cod = us_cod;
    }

    public String getUs_nome() {
        return us_nome;
    }

    public void setUs_nome(String us_nome) {
        this.us_nome = us_nome;
    }

    public String getUs_dtnasc() {
        return us_dtnasc;
    }

    public void setUs_dtnasc(String us_dtnasc) {
        this.us_dtnasc = us_dtnasc;
    }

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    public String getUs_latitude() {
        return us_latitude;
    }

    public void setUs_latitude(String us_latitude) {
        this.us_latitude = us_latitude;
    }

    public String getUs_longitude() {
        return us_longitude;
    }

    public void setUs_longitude(String us_longitude) {
        this.us_longitude = us_longitude;
    }

    public String getUs_codarea() {
        return us_codarea;
    }

    public void setUs_codarea(String us_codarea) {
        this.us_codarea = us_codarea;
    }

    public String getUs_telefone() {
        return us_telefone;
    }

    public void setUs_telefone(String us_telefone) {
        this.us_telefone = us_telefone;
    }

    public String getUs_senha() {
        return us_senha;
    }

    public void setUs_senha(String us_senha) {
        this.us_senha = us_senha;
    }

    public String getUs_confirmesenha() {
        return us_confirmesenha;
    }

    public void setUs_confirmesenha(String us_confirmesenha) {
        this.us_confirmesenha = us_confirmesenha;
    }
}



