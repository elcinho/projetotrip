package libelulati.tripctrl.Listas;

public class Lista {

    private int li_id;
    private String li_nome;
    private int us_id;

    public Lista() {
    }

    public Lista(int li_id, String li_nome) {
        this.li_id = li_id;
        this.li_nome = li_nome;
    }

    public int getLi_id() {
        return li_id;
    }

    public void setLi_id(int li_id) {
        this.li_id = li_id;
    }

    public String getLi_nome() {
        return li_nome;
    }

    public void setLi_nome(String li_nome) {
        this.li_nome = li_nome;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
}
