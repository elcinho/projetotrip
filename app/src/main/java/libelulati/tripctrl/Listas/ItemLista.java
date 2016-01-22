package libelulati.tripctrl.Listas;

public class ItemLista {

    int il_id;
    String il_nome;
    String il_lista;
    int il_check;

    public ItemLista() {
    }

    public ItemLista(int il_id, String il_nome, int il_check) {
        this.il_id = il_id;
        this.il_nome = il_nome;
        this.il_check = il_check;
    }

    public int getIl_id() {
        return il_id;
    }

    public void setIl_id(int il_id) {
        this.il_id = il_id;
    }

    public String getIl_nome() {
        return il_nome;
    }

    public void setIl_nome(String il_nome) {
        this.il_nome = il_nome;
    }

    public String getIl_lista() {
        return il_lista;
    }

    public void setIl_lista(String il_lista) {
        this.il_lista = il_lista;
    }

    public int getIl_check() {
        return il_check;
    }

    public void setIl_check(int il_check) {
        this.il_check = il_check;
    }
}
