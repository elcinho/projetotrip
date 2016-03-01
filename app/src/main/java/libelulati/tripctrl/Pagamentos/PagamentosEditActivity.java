package libelulati.tripctrl.Pagamentos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
import libelulati.tripctrl.Gastos.Gastos_DAO;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PagamentosEditActivity extends AppCompatActivity {

    int novo = 0;
    int vis_menu = 0;
    int id_usuario = InicioActivity.getId_usuario();
    EditText ed_pae_descricao, ed_pae_tipopagamento, ed_pae_valor, ed_pae_vencimento;
    Spinner sp_pae_tipopagamento;
    Context context;
    int id_viagem, id_pagamento;
    List<String> listatipopagamentos;
    MeuSpinner meuSpinner = new MeuSpinner();
    String nulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos_edit);

        context = PagamentosEditActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed_pae_descricao = (EditText)findViewById(R.id.ed_pae_descricao);
        ed_pae_tipopagamento = (EditText)findViewById(R.id.ed_pae_tipopagamento);
        ed_pae_valor = (EditText)findViewById(R.id.ed_pae_valor);
        ed_pae_vencimento = (EditText)findViewById(R.id.ed_pae_dtvenc);
        sp_pae_tipopagamento = (Spinner)findViewById(R.id.sp_pae_tipopagamento);

        ed_pae_vencimento.setInputType(InputType.TYPE_NULL);
        ed_pae_tipopagamento.setInputType(InputType.TYPE_NULL);

        Intent it_pa_novo = getIntent();
        Bundle bundle = it_pa_novo.getExtras();

        novo = bundle.getInt("novo");
        id_pagamento = bundle.getInt(Nomes.getID());
        id_viagem = bundle.getInt(Nomes.getViId());
        ed_pae_descricao.setText(bundle.getString(Nomes.getPaDescricao()));
        ed_pae_tipopagamento.setText(bundle.getString(Nomes.getTpId()));
        ed_pae_valor.setText(bundle.getString(Nomes.getPaValor()));
        ed_pae_vencimento.setText(bundle.getString(Nomes.getPaVencimento()));

        nulo = context.getResources().getString(R.string.encontrado_registro);

        listatipopagamentos = new Pagamentos_DAO(context).sp_tipopagamentos();

        switch (novo){
            case 1:
                PagamentoNovo();
                break;
            case 2:
                PagamentoShow();
                break;
        }
    }

    public void PagamentoNovo(){
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_pagamentos_new));

        vis_menu = 1;
        invalidateOptionsMenu();

        meuSpinner.preencherSpinner(context, listatipopagamentos, sp_pae_tipopagamento);
        meuSpinner.selecionarItem(sp_pae_tipopagamento, ed_pae_tipopagamento);
        ed_pae_tipopagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ed_pae_tipopagamento.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        ed_pae_vencimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_pae_vencimento);
            }
        });

        ed_pae_vencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ed_pae_vencimento.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
    }

    public void PagamentoShow(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_pae_descricao.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 2;
        invalidateOptionsMenu();

        getSupportActionBar().setTitle(getResources().getString(R.string.pagamento) + " " + ed_pae_descricao.getText().toString());
        ed_pae_tipopagamento.setVisibility(View.VISIBLE);
        sp_pae_tipopagamento.setVisibility(View.INVISIBLE);

        ed_pae_descricao.setInputType(InputType.TYPE_NULL);
        ed_pae_vencimento.setInputType(InputType.TYPE_NULL);
        ed_pae_valor.setInputType(InputType.TYPE_NULL);
        ed_pae_tipopagamento.setInputType(InputType.TYPE_NULL);

        ed_pae_tipopagamento.setTextColor(context.getResources().getColor(R.color.colorBlack));
    }

    public void PagamentoEditar(){

        vis_menu = 3;
        invalidateOptionsMenu();

        sp_pae_tipopagamento.setVisibility(View.VISIBLE);

        ed_pae_descricao.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
        ed_pae_valor.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ed_pae_tipopagamento.setTextColor(context.getResources().getColor(R.color.colorWhite));

        meuSpinner.preencherSpinner(context, listatipopagamentos, sp_pae_tipopagamento);
        meuSpinner.posicaoSelecionada(sp_pae_tipopagamento, ed_pae_tipopagamento.getText().toString());
        meuSpinner.selecionarItem(sp_pae_tipopagamento, ed_pae_tipopagamento);

        ed_pae_vencimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_pae_vencimento);
            }
        });

        ed_pae_vencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ed_pae_vencimento.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        ed_pae_descricao.requestFocus();
    }

    public void showDatePicker(View view, EditText ed_data){
        DialogFragment dialogFragment = new DatePicker(ed_data);
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public void Salvar(){
        Pagamento pagamento = new Pagamento();

        pagamento.setUs_id(id_usuario);
        pagamento.setVi_id(id_viagem);
        pagamento.setTp_id(ed_pae_tipopagamento.getText().toString());
        pagamento.setPa_descricao(ed_pae_descricao.getText().toString());
        pagamento.setPa_valor(ed_pae_valor.getText().toString());
        pagamento.setPa_dtvenc(ed_pae_vencimento.getText().toString());

        if(IsValido()){
            boolean sucesso = new Pagamentos_DAO(context).criar(pagamento);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_pagamento), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_pagamento), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void Atualizar(){
        Pagamento pagamento = new Pagamento();

        pagamento.setUs_id(id_usuario);
        pagamento.setVi_id(id_viagem);
        pagamento.setTp_id(ed_pae_tipopagamento.getText().toString());
        pagamento.setPa_descricao(ed_pae_descricao.getText().toString());
        pagamento.setPa_valor(ed_pae_valor.getText().toString());
        pagamento.setPa_dtvenc(ed_pae_vencimento.getText().toString());

        if(IsValido()){
            boolean sucesso = new Pagamentos_DAO(context).atualizar(pagamento, id_pagamento);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_alterar_pagamento), Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_pagamento), Toast.LENGTH_LONG).show();
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
                Pagamentos_DAO pagamentos_dao = new Pagamentos_DAO(context);
                boolean sucesso = pagamentos_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_pagamento), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_pagamento), Toast.LENGTH_LONG).show();
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
        return true;
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
                PagamentoEditar();
                break;
            case R.id.mn_gb_deletar:
                Deletar(id_pagamento);
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
