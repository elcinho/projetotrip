package libelulati.tripctrl.MetodosPagamento;

public class MetodosPagamento {
    int mp_id;
    int us_id;
    String vi_id;
    String tp_id;
    String mp_detalhe;
    String mp_dtvenc;
    String mp_valor;

    public MetodosPagamento() {
    }

    public MetodosPagamento(int mp_id, String mp_detalhe, String mp_dtvenc, String mp_valor) {
        this.mp_id = mp_id;
        this.mp_detalhe = mp_detalhe;
        this.mp_dtvenc = mp_dtvenc;
        this.mp_valor = mp_valor;
    }

    public int getMp_id() {
        return mp_id;
    }

    public void setMp_id(int mp_id) {
        this.mp_id = mp_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getVi_id() {
        return vi_id;
    }

    public void setVi_id(String vi_id) {
        this.vi_id = vi_id;
    }

    public String getTp_id() {
        return tp_id;
    }

    public void setTp_id(String tp_id) {
        this.tp_id = tp_id;
    }

    public String getMp_detalhe() {
        return mp_detalhe;
    }

    public void setMp_detalhe(String mp_detalhe) {
        this.mp_detalhe = mp_detalhe;
    }

    public String getMp_dtvenc() {
        return mp_dtvenc;
    }

    public void setMp_dtvenc(String mp_dtvenc) {
        this.mp_dtvenc = mp_dtvenc;
    }

    public String getMp_valor() {
        return mp_valor;
    }

    public void setMp_valor(String mp_valor) {
        this.mp_valor = mp_valor;
    }
}
