package libelulati.tripctrl.Pagamentos;

public class Pagamento {

    private int pa_id;
    private int us_id;
    private int vi_id;
    private String tp_id;
    private String pa_valor;
    private String pa_dtvenc;

    public Pagamento(int pa_id, String tp_id, String pa_valor, String pa_dtvenc) {
        this.pa_id = pa_id;
        this.tp_id = tp_id;
        this.pa_valor = pa_valor;
        this.pa_dtvenc = pa_dtvenc;
    }

    public int getPa_id() {
        return pa_id;
    }

    public void setPa_id(int pa_id) {
        this.pa_id = pa_id;
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

    public String getTp_id() {
        return tp_id;
    }

    public void setTp_id(String tp_id) {
        this.tp_id = tp_id;
    }

    public String getPa_valor() {
        return pa_valor;
    }

    public void setPa_valor(String pa_valor) {
        this.pa_valor = pa_valor;
    }

    public String getPa_dtvenc() {
        return pa_dtvenc;
    }

    public void setPa_dtvenc(String pa_dtvenc) {
        this.pa_dtvenc = pa_dtvenc;
    }
}
