package libelulati.tripctrl.Gastos;

public class Gasto {
    private int ga_id;
    private int us_id;
    private int dv_id;
    private int sc_id;
    private float ga_valorcategoria;
    private float ga_valortotal;

    public Gasto(int ga_id, int us_id, int dv_id, int sc_id, float ga_valorcategoria, float ga_valortotal) {
        this.ga_id = ga_id;
        this.us_id = us_id;
        this.dv_id = dv_id;
        this.sc_id = sc_id;
        this.ga_valorcategoria = ga_valorcategoria;
        this.ga_valortotal = ga_valortotal;
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

    public int getDv_id() {
        return dv_id;
    }

    public void setDv_id(int dv_id) {
        this.dv_id = dv_id;
    }

    public int getSc_id() {
        return sc_id;
    }

    public void setSc_id(int sc_id) {
        this.sc_id = sc_id;
    }

    public float getGa_valorcategoria() {
        return ga_valorcategoria;
    }

    public void setGa_valorcategoria(float ga_valorcategoria) {
        this.ga_valorcategoria = ga_valorcategoria;
    }

    public float getGa_valortotal() {
        return ga_valortotal;
    }

    public void setGa_valortotal(float ga_valortotal) {
        this.ga_valortotal = ga_valortotal;
    }
}
