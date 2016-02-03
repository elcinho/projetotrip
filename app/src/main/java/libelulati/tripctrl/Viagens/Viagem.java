package libelulati.tripctrl.Viagens;

public class Viagem {
    
    private int vi_id;
    private String vi_nome;
    private String vi_dtinic;
    private String vi_dtfim;
    private double vi_valor;

    public Viagem(int vi_id, String vi_nome, String vi_dtinic, String vi_dtfim, double vi_valor) {
        this.vi_id = vi_id;
        this.vi_nome = vi_nome;
        this.vi_dtinic = vi_dtinic;
        this.vi_dtfim = vi_dtfim;
        this.vi_valor = vi_valor;
    }

    public Viagem() {
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }

    public String getVi_nome() {
        return vi_nome;
    }

    public void setVi_nome(String vi_nome) {
        this.vi_nome = vi_nome;
    }

    public String getVi_dtinic() {
        return vi_dtinic;
    }

    public void setVi_dtinic(String vi_dtinic) {
        this.vi_dtinic = vi_dtinic;
    }

    public String getVi_dtfim() {
        return vi_dtfim;
    }

    public void setVi_dtfim(String vi_dtfim) {
        this.vi_dtfim = vi_dtfim;
    }

    public double getVi_valor() {
        return vi_valor;
    }

    public void setVi_valor(double vi_valor) {
        this.vi_valor = vi_valor;
    }
}
