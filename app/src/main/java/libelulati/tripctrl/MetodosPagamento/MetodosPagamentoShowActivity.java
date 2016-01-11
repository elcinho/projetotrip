package libelulati.tripctrl.MetodosPagamento;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class MetodosPagamentoShowActivity extends AppCompatActivity {
    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, mps_id, mps_menu;
    String titulo_show, titulo_edit;
    StringBuilder dataformatada;
    boolean validar, v_detalhe, v_dtvenc, v_valor;
    Context context;
    MenuItem mps_salvar;
    EditText mps_detalhe, mps_viagem, mps_tipopagamento, mps_dtvenc, mps_valor;
    Spinner mps_sp_tppagamento, mps_sp_viagem;
    FloatingActionButton fab_editar;
    List<String> listatipopagamento, listaviagem;
    MeuSpinner meuSpinner = new MeuSpinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodospagamento_show);

        context = MetodosPagamentoShowActivity.this;
        mps_menu = 1;
        invalidateOptionsMenu();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_mp_edit);
        mps_detalhe = (EditText)findViewById(R.id.ed_mps_detalhe);
        mps_viagem = (EditText)findViewById(R.id.ed_mps_viagem);
        mps_tipopagamento = (EditText)findViewById(R.id.ed_mps_tipopagamento);
        mps_dtvenc = (EditText)findViewById(R.id.ed_mps_dtvenc);
        mps_valor = (EditText)findViewById(R.id.ed_mps_valortotal);
        mps_sp_tppagamento = (Spinner)findViewById(R.id.sp_mps_tipopagamento);
        mps_sp_viagem = (Spinner)findViewById(R.id.sp_mps_viagem);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mps_detalhe.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent it_mps_show = getIntent();
        Bundle bundle = it_mps_show.getExtras();

        mps_id = bundle.getInt(StringsNomes.getID());
        mps_detalhe.setText(bundle.getString(StringsNomes.getMeDetalhe()));
        mps_dtvenc.setText(bundle.getString(StringsNomes.getMeVencimento()));
        mps_tipopagamento.setText(bundle.getString(StringsNomes.getTpId()));
        mps_viagem.setText(bundle.getString(StringsNomes.getViId()));
        mps_valor.setText(bundle.getString(StringsNomes.getMeValor()));

        titulo_show = context.getResources().getString(R.string.title_activity_metodos_pagamento_show) + " " + mps_detalhe.getText().toString();
        titulo_edit = context.getResources().getString(R.string.title_activity_metodos_pagamento_edit) + " " + mps_detalhe.getText().toString();

        getSupportActionBar().setTitle(titulo_show);

        listatipopagamento = new MetodosPagamentoDAO(context).sp_tipospagamento();
        listaviagem = new MetodosPagamentoDAO(context).sp_viagens();

        mps_viagem.setInputType(InputType.TYPE_NULL);
        mps_tipopagamento.setInputType(InputType.TYPE_NULL);
        mps_dtvenc.setInputType(InputType.TYPE_NULL);

        mps_detalhe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mps_detalhe.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardetalhe();
                }
            }
        });

        mps_dtvenc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mps_dtvenc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mps_dtvenc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardtvenc();
                }
            }
        });

        mps_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mps_valor.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarvalor();
                }
            }
        });

        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSupportActionBar().setTitle(titulo_edit);

                mps_detalhe.setEnabled(true);
                mps_dtvenc.setEnabled(true);
                mps_valor.setEnabled(true);
                mps_tipopagamento.setEnabled(true);
                mps_viagem.setEnabled(true);
                mps_sp_tppagamento.setVisibility(View.VISIBLE);
                mps_sp_viagem.setVisibility(View.VISIBLE);

                mps_viagem.setTextColor(getResources().getColor(R.color.colorBranco));
                mps_tipopagamento.setTextColor(getResources().getColor(R.color.colorBranco));

                meuSpinner.preencherSpinner(context, listatipopagamento, mps_sp_tppagamento);
                meuSpinner.posicaoSelecionada(mps_sp_tppagamento, mps_tipopagamento.getText().toString());
                meuSpinner.selecionarItem(mps_sp_tppagamento, mps_tipopagamento);

                meuSpinner.preencherSpinner(context, listaviagem, mps_sp_viagem);
                meuSpinner.posicaoSelecionada(mps_sp_viagem, mps_viagem.getText().toString());
                meuSpinner.selecionarItem(mps_sp_viagem, mps_viagem);

                mps_detalhe.requestFocus();

                fab_editar.setVisibility(View.INVISIBLE);
                mps_menu = 2;
                invalidateOptionsMenu();
            }
        });
    }

    public void salvar(){

        mps_valor.clearFocus();

        MetodosPagamento metodosPagamento = new MetodosPagamento();

        metodosPagamento.setUs_id(usuario);
        metodosPagamento.setMp_detalhe(mps_detalhe.getText().toString());
        metodosPagamento.setVi_id(mps_viagem.getText().toString());
        metodosPagamento.setTp_id(mps_tipopagamento.getText().toString());
        metodosPagamento.setMp_dtvenc(mps_dtvenc.getText().toString());
        metodosPagamento.setMp_valor(mps_valor.getText().toString());

        if(IsValido()){
            boolean sucesso = new MetodosPagamentoDAO(context).atualizar(metodosPagamento, mps_id);

            if (sucesso) {
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_alterar_metodopagamento), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_metodopagamento), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        mps_salvar = menu.findItem(R.id.mn_gb_salvar);
        switch (mps_menu){
            case 1:
                mps_salvar.setVisible(false);
                break;
            case 2:
                mps_salvar.setVisible(true);
                break;
        }
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mn_gb_salvar:
                salvar();
            case android.R.id.home:
                if(getActionBar() == null){
                    onBackPressed();
                }
                else{
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void verificardetalhe(){
        validar = Validar.ValidarNome(String.valueOf(mps_detalhe.getText().toString()));
        if(!validar){
            mps_detalhe.setError(context.getResources().getString(R.string.validar_detalhe));
            v_detalhe = false;
        }
        else{
            v_detalhe = true;
        }
    }

    public void verificardtvenc(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(mps_dtvenc.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar) {
            mps_dtvenc.setError(context.getResources().getString(R.string.validar_dtinicio));
            v_dtvenc = false;
        }
        else{
            v_dtvenc = true;
        }
    }

    public void verificarvalor(){
        double valor = 0;
        if(mps_valor.length()>0){
            valor = Double.parseDouble(mps_valor.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mps_valor.setError(context.getResources().getString(R.string.validar_valor));
                    v_valor = false;
                }
                else{
                    v_valor = true;
                }
            }
        }
        else{
            mps_valor.setError(context.getResources().getString(R.string.validar_valor));
            v_valor = false;
        }
    }

    public boolean IsValido(){
        if(v_detalhe && v_dtvenc && v_valor){
            return true;
        }
        else{
            return false;
        }
    }

    public void calendario() {
        final Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void AtualizarData() {
        dataformatada = (new StringBuilder().append(dia).append("/").append(mes).append("/").append(ano));
        mps_dtvenc.setText(dataformatada);
    }

    public void showDatePickerDialog_mps_dtvenc(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            calendario();
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ano = year;
            mes = month + 1;
            dia = day;

            AtualizarData();
        }
    }

}
