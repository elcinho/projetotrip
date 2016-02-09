package libelulati.tripctrl.Gastos;

public class Gasto {

    private int ga_id;
    private int us_id;
    private int vi_id;
    private String pa_id;
    private String ca_id;
    private String ga_valor;
    private String ga_descricao;
    private String ga_data;

    public Gasto() {
    }

    public Gasto(int ga_id, String pa_id, String ca_id, String ga_valor, String ga_descricao, String ga_data) {
        this.ga_id = ga_id;
        this.pa_id = pa_id;
        this.ca_id = ca_id;
        this.ga_valor = ga_valor;
        this.ga_descricao = ga_descricao;
        this.ga_data = ga_data;
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

    public String getGa_valor() {
        return ga_valor;
    }

    public void setGa_valor(String ga_valor) {
        this.ga_valor = ga_valor;
    }

    public String getGa_descricao() {
        return ga_descricao;
    }

    public void setGa_descricao(String ga_descricao) {
        this.ga_descricao = ga_descricao;
    }

    public String getGa_data() {
        return ga_data;
    }

    public void setGa_data(String ga_data) {
        this.ga_data = ga_data;
    }
}
