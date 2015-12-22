package libelulati.tripctrl.Planejamentos;

public class Planejamento {
    private int pl_id;
    private int us_id;
    private int dv_id;
    private int sc_id;
    private float pl_valorcategoria;
    private float pl_valortotal;

    public Planejamento(int pl_id, int us_id, int dv_id, int sc_id, float pl_valorcategoria, float pl_valortotal) {
        this.pl_id = pl_id;
        this.us_id = us_id;
        this.dv_id = dv_id;
        this.sc_id = sc_id;
        this.pl_valorcategoria = pl_valorcategoria;
        this.pl_valortotal = pl_valortotal;
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

    public float getPl_valorcategoria() {
        return pl_valorcategoria;
    }

    public void setPl_valorcategoria(float pl_valorcategoria) {
        this.pl_valorcategoria = pl_valorcategoria;
    }

    public float getPl_valortotal() {
        return pl_valortotal;
    }

    public void setPl_valortotal(float pl_valortotal) {
        this.pl_valortotal = pl_valortotal;
    }
}
