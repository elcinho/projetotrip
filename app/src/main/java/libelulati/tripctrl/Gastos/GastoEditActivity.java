package libelulati.tripctrl.Gastos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.R;

public class GastoEditActivity extends AppCompatActivity {
    int novo = 0;
    EditText ed_gae_data, ed_gae_categoria, ed_gae_descricao, ed_gae_valor, ed_gae_pagamento;
    Spinner sp_gae_categoria, sp_gae_pagamento;
    Context context;
    int id_viagem, id_gasto;
    List<String> listacategorias, listapagamentos;
    MeuSpinner meuSpinner = new MeuSpinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_edit);

        context = GastoEditActivity.this;

        ed_gae_data = (EditText)findViewById(R.id.ed_gae_data);
        ed_gae_categoria = (EditText)findViewById(R.id.ed_gae_categoria);
        ed_gae_descricao = (EditText)findViewById(R.id.ed_gae_descricao);
        ed_gae_valor = (EditText)findViewById(R.id.ed_gae_valor);
        ed_gae_pagamento = (EditText)findViewById(R.id.ed_gae_pagamento);
        sp_gae_categoria = (Spinner)findViewById(R.id.sp_gae_categoria);
        sp_gae_pagamento = (Spinner)findViewById(R.id.sp_gae_pagamento);

        Intent it_ga_novo = getIntent();
        Bundle bundle = it_ga_novo.getExtras();

        novo = bundle.getInt("isnew");
        id_viagem = bundle.getInt(Nomes.getViId());

        if(novo == 1){
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_new));
            ed_gae_categoria.setVisibility(View.INVISIBLE);
            ed_gae_pagamento.setVisibility(View.INVISIBLE);
        }
        else {
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_edit));
            ed_gae_categoria.setVisibility(View.VISIBLE);
            ed_gae_pagamento.setVisibility(View.VISIBLE);
        }
    }

    public void Salvar(){

    }

    public void Editar(){

    }

    public void Deletar(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if(novo == 1){
            getMenuInflater().inflate(R.menu.salvar_menu, menu);
            return (true);
        }
        else{
            getMenuInflater().inflate(R.menu.edit_delet_menu, menu);
            return (true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mn_gb_salvar:
                Salvar();
                break;
            case R.id.mn_gb_editar:
                Editar();
                break;
            case R.id.mn_gb_deletar:
                Deletar();
                break;
        }
        return true;
    }
}
