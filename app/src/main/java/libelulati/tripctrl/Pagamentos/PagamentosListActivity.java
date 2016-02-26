package libelulati.tripctrl.Pagamentos;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PagamentosListActivity extends AppCompatActivity {

    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    Context context;
    int id_pagamento;
    FloatingActionButton fab_pa_new;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
