package libelulati.tripctrl.Categoria;

public class Categoria {
    private int sc_id;
    private int ca_id;
    private String sc_nome;
    private int us_id;

    public Categoria() {
    }

    public Categoria(int sc_id, int ca_id, String sc_nome, int us_id) {
        this.sc_id = sc_id;
        this.ca_id = ca_id;
        this.sc_nome = sc_nome;
        this.us_id = us_id;
    }

    public int getSc_id() {
        return sc_id;
    }

    public void setSc_id(int sc_id) {
        this.sc_id = sc_id;
    }

    public int getCa_id() {
        return ca_id;
    }

    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    public String getSc_nome() {
        return sc_nome;
    }

    public void setSc_nome(String sc_nome) {
        this.sc_nome = sc_nome;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
}
