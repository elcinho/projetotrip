package libelulati.tripctrl.Viagens;

public class Viagens {

    private int vi_id;
    private int us_id;
    private String vi_nome;
    private String vi_local;
    private String vi_dtini;
    private String vi_dtfim;
    private String tr_id;
    private String vi_transporte;
    private String ho_id;
    private String vi_hospedagem;
    private String vi_valortotal;

    public Viagens() {
    }

    public Viagens(int vi_id, String vi_nome, String vi_dtini, String vi_dtfim) {
        this.vi_id = vi_id;
        this.vi_nome = vi_nome;
        this.vi_dtini = vi_dtini;
        this.vi_dtfim = vi_dtfim;
    }

    public int getVi_id() {
        return vi_id;
    }

    public void setVi_id(int vi_id) {
        this.vi_id = vi_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public String getVi_nome() {
        return vi_nome;
    }

    public void setVi_nome(String vi_nome) {
        this.vi_nome = vi_nome;
    }

    public String getVi_local() {
        return vi_local;
    }

    public void setVi_local(String vi_local) {
        this.vi_local = vi_local;
    }

    public String getVi_dtini() {
        return vi_dtini;
    }

    public void setVi_dtini(String vi_dtini) {
        this.vi_dtini = vi_dtini;
    }

    public String getVi_dtfim() {
        return vi_dtfim;
    }

    public void setVi_dtfim(String vi_dtfim) {
        this.vi_dtfim = vi_dtfim;
    }

    public String getTr_id() {
        return tr_id;
    }

    public void setTr_id(String tr_id) {
        this.tr_id = tr_id;
    }

    public String getVi_transporte() {
        return vi_transporte;
    }

    public void setVi_transporte(String vi_transporte) {
        this.vi_transporte = vi_transporte;
    }

    public String getHo_id() {
        return ho_id;
    }

    public void setHo_id(String ho_id) {
        this.ho_id = ho_id;
    }

    public String getVi_hospedagem() {
        return vi_hospedagem;
    }

    public void setVi_hospedagem(String vi_hospedagem) {
        this.vi_hospedagem = vi_hospedagem;
    }

    public String getVi_valortotal() {
        return vi_valortotal;
    }

    public void setVi_valortotal(String vi_valortotal) {
        this.vi_valortotal = vi_valortotal;
    }
}