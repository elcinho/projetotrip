package libelulati.tripctrl.Notificacoes;

public class Cnotificacoes {
    private int cn_id;
    private int us_id;
    private int cn_ativar;
    private int cn_todas;
    private int cn_viagens;
    private int cn_pagamentos;
    private int cn_planejamentos;
    private int cn_gastos;

    public Cnotificacoes() {
    }

    public Cnotificacoes(int cn_id, int us_id, int cn_ativar, int cn_todas, int cn_viagens, int cn_pagamentos, int cn_planejamentos, int cn_gastos) {
        this.cn_id = cn_id;
        this.us_id = us_id;
        this.cn_ativar = cn_ativar;
        this.cn_todas = cn_todas;
        this.cn_viagens = cn_viagens;
        this.cn_pagamentos = cn_pagamentos;
        this.cn_planejamentos = cn_planejamentos;
        this.cn_gastos = cn_gastos;
    }

    public int getCn_id() {
        return cn_id;
    }

    public void setCn_id(int cn_id) {
        this.cn_id = cn_id;
    }

    public int getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }

    public int getCn_ativar() {
        return cn_ativar;
    }

    public void setCn_ativar(int cn_ativar) {
        this.cn_ativar = cn_ativar;
    }

    public int getCn_todas() {
        return cn_todas;
    }

    public void setCn_todas(int cn_todas) {
        this.cn_todas = cn_todas;
    }

    public int getCn_viagens() {
        return cn_viagens;
    }

    public void setCn_viagens(int cn_viagens) {
        this.cn_viagens = cn_viagens;
    }

    public int getCn_pagamentos() {
        return cn_pagamentos;
    }

    public void setCn_pagamentos(int cn_pagamentos) {
        this.cn_pagamentos = cn_pagamentos;
    }

    public int getCn_planejamentos() {
        return cn_planejamentos;
    }

    public void setCn_planejamentos(int cn_planejamentos) {
        this.cn_planejamentos = cn_planejamentos;
    }

    public int getCn_gastos() {
        return cn_gastos;
    }

    public void setCn_gastos(int cn_gastos) {
        this.cn_gastos = cn_gastos;
    }
}
