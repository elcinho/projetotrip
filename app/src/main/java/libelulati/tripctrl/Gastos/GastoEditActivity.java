package libelulati.tripctrl.Gastos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class GastoEditActivity extends AppCompatActivity {
    int novo = 0;
    int id_usuario = InicioActivity.getId_usuario();
    EditText ed_gae_data, ed_gae_categoria, ed_gae_descricao, ed_gae_valor, ed_gae_pagamento;
    Spinner sp_gae_categoria, sp_gae_pagamento;
    Context context;
    int id_viagem, id_gasto;
    List<String> listacategorias, listapagamentos;
    MeuSpinner meuSpinner = new MeuSpinner();
    String nulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_edit);

        context = GastoEditActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        nulo = context.getResources().getString(R.string.encontrado_registro);

        listacategorias = new Gastos_DAO(context).sp_categorias(id_usuario);
        listapagamentos = new Gastos_DAO(context).sp_pagamentos(id_usuario, nulo);

        switch (novo){
            case 1:
                getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_new));

                meuSpinner.preencherSpinner(context, listacategorias, sp_gae_categoria);
                meuSpinner.selecionarItem(sp_gae_categoria, ed_gae_categoria);
                ed_gae_categoria.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                    .hideSoftInputFromWindow(ed_gae_categoria.getWindowToken(),
                                            InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                });

                meuSpinner.preencherSpinner(context, listapagamentos, sp_gae_pagamento);
                meuSpinner.selecionarItem(sp_gae_pagamento, ed_gae_pagamento);
                ed_gae_pagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                    .hideSoftInputFromWindow(ed_gae_categoria.getWindowToken(),
                                            InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }
                });

                ed_gae_data.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 2:
                getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_edit));
                ed_gae_categoria.setVisibility(View.VISIBLE);
                ed_gae_pagamento.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void Salvar(){
        Gasto gasto = new Gasto();

        gasto.setUs_id(id_usuario);
        gasto.setVi_id(id_viagem);
        gasto.setPa_id(ed_gae_pagamento.getText().toString());
        gasto.setCa_id(ed_gae_categoria.getText().toString());
        gasto.setGa_descricao(ed_gae_descricao.getText().toString());
        gasto.setGa_valor(ed_gae_valor.getText().toString());
        gasto.setGa_data(ed_gae_data.getText().toString());

        if(IsValido()){
            boolean sucesso = new Gastos_DAO(context).criar(gasto);

            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_gasto), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_gasto), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos),Toast.LENGTH_LONG).show();
        }
    }

    public void Editar(){

    }

    public void Atualizar(){

    }

    public void Deletar(){

    }

    public boolean IsValido(){
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        switch (novo) {
            case 1:
                getMenuInflater().inflate(R.menu.salvar_menu, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.edit_delet_menu, menu);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if(getActionBar() == null){
                    onBackPressed();
                }
                else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                break;
            case R.id.mn_gb_salvar:
                Salvar();
                break;
            case R.id.mn_gb_editar:
                Editar();
                break;
            case R.id.mn_gb_deletar:
                Deletar();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
