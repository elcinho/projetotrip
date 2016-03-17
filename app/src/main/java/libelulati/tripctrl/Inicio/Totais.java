package libelulati.tripctrl.Inicio;

public class Totais {
    private int to_id;
    private int us_id;
    private int vi_id;
    private String to_nome;
    private String to_total;
    private String to_gasto;
    private String to_planejamento;

    public Totais(int to_id, int us_id, int vi_id, String to_nome, String to_total, String to_gasto, String to_planejamento) {
        this.to_id = to_id;
        this.us_id = us_id;
        this.vi_id = vi_id;
        this.to_nome = to_nome;
        this.to_total = to_total;
        this.to_gasto = to_gasto;
        this.to_planejamento = to_planejamento;
    }

    public Totais() {
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
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

    public String getTo_nome() {
        return to_nome;
    }

    public void setTo_nome(String to_nome) {
        this.to_nome = to_nome;
    }

    public String getTo_total() {
        return to_total;
    }

    public void setTo_total(String to_total) {
        this.to_total = to_total;
    }

    public String getTo_gasto() {
        return to_gasto;
    }

    public void setTo_gasto(String to_gasto) {
        this.to_gasto = to_gasto;
    }

    public String getTo_planejamento() {
        return to_planejamento;
    }

    public void setTo_planejamento(String to_planejamento) {
        this.to_planejamento = to_planejamento;
    }
}
