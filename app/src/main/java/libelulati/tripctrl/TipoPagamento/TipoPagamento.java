package libelulati.tripctrl.TipoPagamento;

public class TipoPagamento {
    private int tp_id;
    private int us_id;
    private String tp_nome;

    public TipoPagamento() {
    }

    public TipoPagamento(int tp_id, int us_id, String tp_nome) {
        this.tp_id = tp_id;
        this.us_id = us_id;
        this.tp_nome = tp_nome;
    }

    public int getTp_id() {
        return tp_id;
    }

    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getTp_nome() {
        return tp_nome;
    }

    public void setTp_nome(String tp_nome) {
        this.tp_nome = tp_nome;
    }
}
