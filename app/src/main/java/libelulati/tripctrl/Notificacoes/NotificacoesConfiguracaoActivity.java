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

import libelulati.tripctrl.R;

public class NotificacoesConfiguracaoActivity extends AppCompatActivity {

    Switch sw_no_ativar, sw_no_todas, sw_no_viagem, sw_no_pagamento, sw_no_planejamento, sw_no_gasto;
    Context context;

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
                if(! isChecked){
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
                    sw_no_viagem.setEnabled(true);
                    sw_no_pagamento.setEnabled(true);
                    sw_no_planejamento.setEnabled(true);
                    sw_no_gasto.setEnabled(true);
                }
            }
        });
    }
}
