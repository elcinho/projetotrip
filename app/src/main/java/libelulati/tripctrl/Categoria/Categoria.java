package libelulati.tripctrl.Categoria;

public class Categoria {
    private int ca_id;
    private String ca_nome;
    private int us_id;

    public Categoria() {
    }

    public Categoria(int ca_id, String ca_nome) {
        this.ca_id = ca_id;
        this.ca_nome = ca_nome;
    }

    public int getCa_id() {
        return ca_id;
    }

    public void setCa_id(int ca_id) {
        this.ca_id = ca_id;
    }

    public String getCa_nome() {
        return ca_nome;
    }

    public void setCa_nome(String ca_nome) {
        this.ca_nome = ca_nome;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
}

