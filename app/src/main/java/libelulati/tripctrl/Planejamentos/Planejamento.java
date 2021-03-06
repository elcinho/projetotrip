package libelulati.tripctrl.Planejamentos;

public class Planejamento {
    private int pl_id;
    private int us_id;
    private int vi_id;
    private String ca_id;
    private String pl_valor;

    public Planejamento() {
    }

    public Planejamento(int pl_id, String ca_id, String pl_valor) {
        this.pl_id = pl_id;
        this.ca_id = ca_id;
        this.pl_valor = pl_valor;
    }

    public int getPl_id() {
        return pl_id;
    }

    public void setPl_id(int pl_id) {
        this.pl_id = pl_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }

    public String getCa_id() {
        return ca_id;
    }

    public void setCa_id(String ca_id) {
        this.ca_id = ca_id;
    }

    public String getPl_valor() {
        return pl_valor;
    }

    public void setPl_valor(String pl_valor) {
        this.pl_valor = pl_valor;
    }
}
