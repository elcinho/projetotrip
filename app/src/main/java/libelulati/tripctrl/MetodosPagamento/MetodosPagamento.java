package libelulati.tripctrl.MetodosPagamento;

public class MetodosPagamento {

    private int me_id;
    private int us_id;
    private String dv_id;
    private String tp_id;
    private String me_valor;
    private String me_detalhes;
    private String me_vencimento;

    public MetodosPagamento() {

    }

    public MetodosPagamento(int me_id, String me_detalhe, String me_valor, String me_vencimento) {
        this.me_id = me_id;
        this.me_valor = me_valor;
        this.me_detalhes = me_detalhe;
        this.me_vencimento = me_vencimento;
    }


    public int getMe_id () {return me_id;}

    public void setMe_id(int me_id) {
        this.me_id = me_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getDv_id() {
        return dv_id;
    }

    public void setDv_id(String dv_id) {
        this.dv_id = dv_id;
    }

    public String getTp_id() {
        return tp_id;
    }

    public void setTp_id(String tp_id) {
        this.tp_id = tp_id;
    }

    public String getMe_valor() {
        return me_valor;
    }

    public void setMe_valor(String me_valor) {
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
