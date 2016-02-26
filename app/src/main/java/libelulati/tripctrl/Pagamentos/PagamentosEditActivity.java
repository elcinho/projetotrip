package libelulati.tripctrl.Pagamentos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PagamentosEditActivity extends AppCompatActivity {

    int novo = 0;
    int vis_menu = 0;
    int id_usuario = InicioActivity.getId_usuario();
    EditText ed_pae_descricao, ed_pae_tipopagamento, ed_pae_valor, ed_pae_vencimento;
    Spinner sp_pae_tipopagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos_edit);
    }

}
