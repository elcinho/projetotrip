package libelulati.tripctrl.Gastos;

public class Gasto {

    private int ga_id;
    private int us_id;
    private int vi_id;
    private String pa_id;
    private String ca_id;
    private double ga_valor;
    private String ga_descricao;

    public Gasto() {
    }

    public int getGa_id() {
        return ga_id;
    }

    public void setGa_id(int ga_id) {
        this.ga_id = ga_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getPa_id() {
        return pa_id;
    }

    public void setPa_id(String pa_id) {
        this.pa_id = pa_id;
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

    public double getGa_valor() {
        return ga_valor;
    }

    public void setGa_valor(double ga_valor) {
        this.ga_valor = ga_valor;
    }

    public String getGa_descricao() {
        return ga_descricao;
    }

    public void setGa_descricao(String ga_descricao) {
        this.ga_descricao = ga_descricao;
    }
}
