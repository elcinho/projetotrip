package libelulati.tripctrl.Notificacoes;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class NotificacoesConfiguracaoActivity extends AppCompatActivity {

    Switch sw_no_ativar, sw_no_todas, sw_no_viagem, sw_no_pagamento, sw_no_planejamento, sw_no_gasto;
    Context context;
    int id_usuario = InicioActivity.getId_usuario();
    int id_cnotificacoes;
    int a_cnativar, a_cntodas, a_cnviagem, a_cnpagamento, a_cnplanejamento, a_cngasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes_configuracao);

        context = NotificacoesConfiguracaoActivity.this;

        sw_no_ativar = (Switch) findViewById(R.id.sw_no_ativar);
        sw_no_todas = (Switch) findViewById(R.id.sw_no_todas);
        sw_no_viagem = (Switch) findViewById(R.id.sw_no_viagem);
        sw_no_pagamento = (Switch) findViewById(R.id.sw_no_pagamentos);
        sw_no_planejamento = (Switch) findViewById(R.id.sw_no_planejamento);
        sw_no_gasto = (Switch) findViewById(R.id.sw_no_gasto);

        sw_no_ativar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    a_cnativar = 0;
                    a_cntodas = 0;
                    a_cnviagem = 0;
                    a_cnpagamento = 0;
                    a_cnplanejamento = 0;
                    a_cngasto = 0;

                    Atualizar();

                    sw_no_todas.setChecked(false);
                    sw_no_viagem.setChecked(false);
                    sw_no_pagamento.setChecked(false);
                    sw_no_planejamento.setChecked(false);
                    sw_no_gasto.setChecked(false);

                    sw_no_todas.setEnabled(false);
                    sw_no_viagem.setEnabled(false);
                    sw_no_pagamento.setEnabled(false);
                    sw_no_planejamento.setEnabled(false);
                    sw_no_gasto.setEnabled(false);
                }
                else{
                    a_cnativar = 1;

                    Atualizar();

                    sw_no_todas.setEnabled(true);
                    sw_no_viagem.setEnabled(true);
                    sw_no_pagamento.setEnabled(true);
                    sw_no_planejamento.setEnabled(true);
                    sw_no_gasto.setEnabled(true);
                }
            }
        });

        sw_no_todas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a_cntodas = 1;
                    a_cnviagem = 1;
                    a_cnpagamento = 1;
                    a_cnplanejamento = 1;
                    a_cngasto = 1;

                    Atualizar();

                    sw_no_viagem.setChecked(true);
                    sw_no_pagamento.setChecked(true);
                    sw_no_planejamento.setChecked(true);
                    sw_no_gasto.setChecked(true);

                    sw_no_viagem.setEnabled(false);
                    sw_no_pagamento.setEnabled(false);
                    sw_no_planejamento.setEnabled(false);
                    sw_no_gasto.setEnabled(false);
                }
                else {
                    a_cntodas = 0;

                    Atualizar();

                    sw_no_viagem.setEnabled(true);
                    sw_no_pagamento.setEnabled(true);
                    sw_no_planejamento.setEnabled(true);
                    sw_no_gasto.setEnabled(true);
                }
            }
        });

        sw_no_viagem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a_cnviagem = 1;
                    Atualizar();
                }
                else {
                    a_cnviagem = 0;
                    Atualizar();
                }
            }
        });

        sw_no_pagamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a_cnpagamento = 1;
                    Atualizar();
                }
                else {
                    a_cnpagamento = 0;
                    Atualizar();
                }
            }
        });

        sw_no_planejamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a_cnplanejamento = 1;
                    Atualizar();
                }
                else {
                    a_cnplanejamento = 0;
                    Atualizar();
                }
            }
        });

        sw_no_viagem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    a_cnviagem = 1;
                    Atualizar();
                }
                else {
                    a_cnviagem = 0;
                    Atualizar();
                }
            }
        });

    }

    @Override
    public void onResume(){
        Listar();
        super.onResume();
    }

    public void Listar(){
        List<Cnotificacoes> cnotificacoes = new Cnotificacoes_DAO(context).listar(id_usuario);
        for(final Cnotificacoes cnotificacao : cnotificacoes){
            id_cnotificacoes = cnotificacao.getCn_id();
            int cn_ativar = cnotificacao.getCn_ativar();
            int cn_todos = cnotificacao.getCn_todas();
            int cn_viagens = cnotificacao.getCn_viagens();
            int cn_pagamentos = cnotificacao.getCn_pagamentos();
            int cn_planejamentos = cnotificacao.getCn_planejamentos();
            int cn_gastos = cnotificacao.getCn_gastos();

            if(cn_ativar == 0){
                sw_no_ativar.setChecked(false);
            }
            else{
                sw_no_ativar.setChecked(true);
            }

            if(cn_todos == 0){
                sw_no_todas.setChecked(false);
            }
            else{
                sw_no_todas.setChecked(true);
            }

            if(cn_viagens == 0){
                sw_no_viagem.setChecked(false);
            }
            else{
                sw_no_viagem.setChecked(true);
            }

            if(cn_pagamentos == 0){
                sw_no_pagamento.setChecked(false);
            }
            else{
                sw_no_pagamento.setChecked(true);
            }

            if(cn_planejamentos == 0){
                sw_no_planejamento.setChecked(false);
            }
            else{
                sw_no_planejamento.setChecked(true);
            }

            if(cn_gastos == 0){
                sw_no_gasto.setChecked(false);
            }
            else{
                sw_no_gasto.setChecked(true);
            }
        }
    }

    public void Atualizar(){
        Cnotificacoes cnotificacoes = new Cnotificacoes();
        cnotificacoes.setUs_id(id_usuario);
        cnotificacoes.setCn_ativar(a_cnativar);
        cnotificacoes.setCn_todas(a_cntodas);
        cnotificacoes.setCn_viagens(a_cnviagem);
        cnotificacoes.setCn_pagamentos(a_cnpagamento);
        cnotificacoes.setCn_planejamentos(a_cnplanejamento);
        cnotificacoes.setCn_gastos(a_cngasto);

        Cnotificacoes_DAO cnotificacoes_dao = new Cnotificacoes_DAO(context);
        cnotificacoes_dao.atualizar(cnotificacoes, id_cnotificacoes);
    }
}
