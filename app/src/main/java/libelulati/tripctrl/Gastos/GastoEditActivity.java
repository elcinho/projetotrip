package libelulati.tripctrl.Gastos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagens_DAO;

public class GastoEditActivity extends AppCompatActivity {
    int novo = 0;
    int vis_menu = 0;
    int id_usuario = InicioActivity.getId_usuario();
    EditText ed_gae_data, ed_gae_categoria, ed_gae_descricao, ed_gae_valor, ed_gae_pagamento;
    Spinner sp_gae_categoria, sp_gae_pagamento;
    Context context;
    int id_viagem, id_gasto;
    List<String> listacategorias, listapagamentos;
    MeuSpinner meuSpinner = new MeuSpinner();
    String nulo;
    boolean v_data, v_descricao, v_valor;
    Validar validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_edit);

        context = GastoEditActivity.this;
        validar = new Validar(context);

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

        novo = bundle.getInt("novo");
        id_gasto = bundle.getInt(Nomes.getID());
        id_viagem = bundle.getInt(Nomes.getViId());
        ed_gae_descricao.setText(bundle.getString(Nomes.getGaDescricao()));
        ed_gae_categoria.setText(bundle.getString(Nomes.getCaId()));
        ed_gae_data.setText(bundle.getString(Nomes.getGaData()));
        ed_gae_pagamento.setText(bundle.getString(Nomes.getPaId()));
        ed_gae_valor.setText(bundle.getString(Nomes.getGaValor()));

        ed_gae_data.setInputType(InputType.TYPE_NULL);

        nulo = context.getResources().getString(R.string.encontrado_registro);

        listacategorias = new Gastos_DAO(context).sp_categorias(id_usuario);
        listapagamentos = new Gastos_DAO(context).sp_pagamentos(id_viagem, nulo);

        switch (novo){
            case 1:
                GastoNovo();
                break;
            case 2:
                GastoShow();
                break;
        }
    }

    public void showDatePicker(View view, EditText ed_data){
        DialogFragment dialogFragment = new DatePicker(ed_data);
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
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

    public void GastoNovo(){
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_new));

        vis_menu = 1;
        invalidateOptionsMenu();

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
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_gae_pagamento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        ed_gae_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_gae_data);
            }
        });

        ed_gae_data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_gae_data.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    showDatePicker(v, ed_gae_data);
                } else {
                    Viagem viagem = new Viagens_DAO(context).buscarID(id_viagem);
                    v_data = validar.ValidarDataTransacao(ed_gae_data.getText().toString(), viagem.getVi_dtinic(), viagem.getVi_dtfim(), ed_gae_data);
                }
            }
        });

        ed_gae_descricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v_descricao = validar.ValidarTexto(ed_gae_descricao.getText().toString(), ed_gae_descricao);
                }
            }
        });

        ed_gae_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v_valor = validar.ValidarValor(ed_gae_valor.getText().toString(), ed_gae_valor);
                }
            }
        });
    }

    public void GastoShow(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_gae_data.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 2;
        invalidateOptionsMenu();

        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_gasto_edit));
        ed_gae_categoria.setVisibility(View.VISIBLE);
        ed_gae_pagamento.setVisibility(View.VISIBLE);
        sp_gae_pagamento.setVisibility(View.INVISIBLE);
        sp_gae_categoria.setVisibility(View.INVISIBLE);

        ed_gae_descricao.setEnabled(false);
        ed_gae_categoria.setEnabled(false);
        ed_gae_valor.setEnabled(false);
        ed_gae_pagamento.setEnabled(false);
        ed_gae_data.setEnabled(false);

        ed_gae_categoria.setTextColor(context.getResources().getColor(R.color.colorBlack));
        ed_gae_pagamento.setTextColor(context.getResources().getColor(R.color.colorBlack));
        ed_gae_descricao.setTextColor(context.getResources().getColor(R.color.colorBlack));
        ed_gae_data.setTextColor(context.getResources().getColor(R.color.colorBlack));
        ed_gae_valor.setTextColor(context.getResources().getColor(R.color.colorBlack));
    }

    public void GastoEditar(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_gae_data.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 3;
        invalidateOptionsMenu();

        sp_gae_pagamento.setVisibility(View.VISIBLE);
        sp_gae_categoria.setVisibility(View.VISIBLE);

        ed_gae_descricao.setEnabled(true);
        ed_gae_categoria.setEnabled(true);
        ed_gae_valor.setEnabled(true);
        ed_gae_pagamento.setEnabled(true);
        ed_gae_data.setEnabled(true);
        ed_gae_data.setInputType(InputType.TYPE_NULL);

        ed_gae_categoria.setTextColor(context.getResources().getColor(R.color.colorWhite));
        ed_gae_pagamento.setTextColor(context.getResources().getColor(R.color.colorWhite));

        meuSpinner.preencherSpinner(context, listacategorias, sp_gae_categoria);
        meuSpinner.posicaoSelecionada(sp_gae_categoria, ed_gae_categoria.getText().toString());
        meuSpinner.selecionarItem(sp_gae_categoria, ed_gae_categoria);

        meuSpinner.preencherSpinner(context, listapagamentos, sp_gae_pagamento);
        meuSpinner.posicaoSelecionada(sp_gae_pagamento, ed_gae_pagamento.getText().toString());
        meuSpinner.selecionarItem(sp_gae_pagamento, ed_gae_pagamento);

        ed_gae_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_gae_data);
            }
        });

        ed_gae_data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_gae_data.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    showDatePicker(v, ed_gae_data);
                } else {
                    Viagem viagem = new Viagens_DAO(context).buscarID(id_viagem);
                    v_data = validar.ValidarDataTransacao(ed_gae_data.getText().toString(), viagem.getVi_dtinic(), viagem.getVi_dtfim(), ed_gae_data);
                }
            }
        });

        ed_gae_descricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    v_descricao = validar.ValidarTexto(ed_gae_descricao.getText().toString(), ed_gae_categoria);
                }
            }
        });

        ed_gae_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    v_valor = validar.ValidarValor(ed_gae_valor.getText().toString(), ed_gae_valor);
                }
            }
        });
    }

    public void Atualizar(){
        Gasto gasto = new Gasto();

        gasto.setUs_id(id_usuario);
        gasto.setVi_id(id_viagem);
        gasto.setCa_id(ed_gae_categoria.getText().toString());
        gasto.setPa_id(ed_gae_pagamento.getText().toString());
        gasto.setGa_descricao(ed_gae_descricao.getText().toString());
        gasto.setGa_data(ed_gae_data.getText().toString());
        gasto.setGa_valor(ed_gae_valor.getText().toString());

        if(IsValido()){
            boolean sucesso = new Gastos_DAO(context).atualizar(gasto, id_gasto);

            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_atualizar_gasto), Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_atualizar_gasto), Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void Deletar(int id){
        final int del_id = id;

        AlertDialog confirme;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.opcao_confirmar));
        builder.setMessage(context.getResources().getString(R.string.excluir_registro));

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Gastos_DAO gastos_dao = new Gastos_DAO(context);
                boolean sucesso = gastos_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_gasto), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_gasto), Toast.LENGTH_LONG).show();
                    finish();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        confirme = builder.create();
        confirme.show();
    }

    public boolean IsValido(){
        if(v_data && v_descricao && v_valor){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        switch (vis_menu) {
            case 1:
                getMenuInflater().inflate(R.menu.salvar_menu, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.edit_delet_menu, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.atualizar_menu, menu);
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
                GastoEditar();
                break;
            case R.id.mn_gb_deletar:
                Deletar(id_gasto);
                break;
            case R.id.mn_gb_atualizar:
                Atualizar();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
