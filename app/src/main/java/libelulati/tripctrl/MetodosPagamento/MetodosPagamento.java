package libelulati.tripctrl.MetodosPagamento;

import android.content.Context;

public class MetodosPagamento {

    private int me_id;
    private int us_id;
    private int dv_id;
    private int tp_id;
    private float me_valor;
    private String me_detalhes;
    private String me_vencimento;

    public MetodosPagamento(int me_id, int us_id, float me_valor, String me_detalhes) {
        this.me_id = me_id;
        this.us_id = us_id;
        this.dv_id = dv_id;
        this.tp_id = tp_id;
        this.me_valor = me_valor;
        this.me_detalhes = me_detalhes;
        this.me_vencimento = me_vencimento;
    }

    public MetodosPagamento() {
    }

    public MetodosPagamento(Context context) {

    }


    public int getMe_id() {
        return me_id;
    }

    public void setMe_id(int me_id) {
        this.me_id = me_id;
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

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public float getMe_valor() {
        return me_valor;
    }

    public void setMe_valor(float me_valor) {
        this.me_valor = me_valor;
    }

    public String getMe_vencimento() {
        return me_vencimento;
    }

    public void setMe_vencimento(String me_vencimento) {
        this.me_vencimento = me_vencimento;
    }

    public String getMe_detalhes() { return me_detalhes; }

    public void setMe_detalhes (String me_detalhe) {this.me_detalhes = me_detalhe;}



}
