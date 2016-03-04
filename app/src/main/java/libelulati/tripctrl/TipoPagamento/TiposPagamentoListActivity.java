package libelulati.tripctrl.TipoPagamento;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class TiposPagamentoListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    Context context;
    EditText ed_tp_novo, ed_di_editar;
    TextView tx_tp_nome;
    ImageView iv_tp_novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_pagamento_list);

        context = TiposPagamentoListActivity.this;

        ed_tp_novo = (EditText) findViewById(R.id.ed_tp_novo);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_tp_novo.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        iv_tp_novo = (ImageView) findViewById(R.id.iv_tp_novo);
        iv_tp_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar();
            }
        });
    }


    public void Salvar(){

    }
}
