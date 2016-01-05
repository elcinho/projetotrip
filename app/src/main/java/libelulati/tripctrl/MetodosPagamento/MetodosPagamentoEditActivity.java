package libelulati.tripctrl.MetodosPagamento;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.ParseException;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;


public class MetodosPagamentoEditActivity extends AppCompatActivity {

    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, dataclick;
    StringBuilder dataformatada;
    boolean validar, valido;
    Context context;
    Spinner spinnerTipoPagamento , spinnerViagem;
    List<String> listaTipoPagamento, listaViagem;
    EditText mp_valor , mp_detalhe, mp_viagem, mp_vencimento , mp_tipopagamento;
    MeuSpinner meuSpinner = new MeuSpinner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_edit);

        context = MetodosPagamentoEditActivity.this;

             getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
        mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);
        mp_viagem = (EditText) findViewById(R.id.ed_mp_viagem);
        mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
        mp_tipopagamento = (EditText) findViewById(R.id.ed_mp_tipoPagamento);
        spinnerTipoPagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
        spinnerViagem = (Spinner) findViewById(R.id.sp_mp_viagem);

        listaViagem = new MetodosPagamentoDAO(context).SpinerViagem();
        listaTipoPagamento = new MetodosPagamentoDAO(context).SpinerTipoPagamento();

        mp_vencimento.setInputType(InputType.TYPE_NULL);
        mp_viagem.setInputType(InputType.TYPE_NULL);
        mp_tipopagamento.setInputType(InputType.TYPE_NULL);

        meuSpinner.preencherSpinner(context, listaTipoPagamento, spinnerTipoPagamento);
        meuSpinner.selecionarItem(spinnerTipoPagamento, mp_tipopagamento);
        mp_tipopagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_tipopagamento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

            }
        });

        meuSpinner.preencherSpinner(context, listaViagem, spinnerViagem);
        meuSpinner.selecionarItem(spinnerViagem, mp_viagem);
        mp_viagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {


            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_viagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });


        mp_vencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_vencimento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else{
                    verificardtini();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_vencimento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });


        mp_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_valor.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarvalor();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
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

    public void salvar(){

        mp_valor.clearFocus();

        MetodosPagamento metodosPagamento = new MetodosPagamento();

        metodosPagamento.setUs_id(usuario);
        metodosPagamento.setDv_id(Integer.parseInt(mp_viagem.getText().toString()));
        metodosPagamento.setTp_id(Integer.parseInt(mp_tipopagamento.getText().toString()));
        metodosPagamento.setMe_detalhes(mp_detalhe.getText().toString());
        metodosPagamento.setMe_valor(Integer.parseInt(mp_valor.getText().toString()));
        metodosPagamento.setMe_vencimento(mp_vencimento.getText().toString());

        if(valido){
            boolean sucesso = new MetodosPagamentoDAO(context).criar(metodosPagamento);

            if (sucesso) {
                Toast.makeText(context, context.getResources().getString(R.string.metodopagameto)+ " " + context.getResources().getString(R.string.sucesso_criado) + ".", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar) + " " + context.getResources().getString(R.string.metodopagameto) + ".", Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.campos_invalidos) + ". " + context.getResources().getString(R.string.registro_nao_salvo) + ".", Toast.LENGTH_LONG).show();
        }
    }



    public void verificardtini(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = null;
            try {
                data = sdf.parse(String.valueOf(mp_vencimento.getText().toString()));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, context.getResources().getString(R.string.datainicio) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        }
        else{
            mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }


    public void verificarvalor(){
        double valor = 0;
        if(mp_valor.length()>0){
            valor = Double.parseDouble(mp_valor.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
                    Toast.makeText(context, context.getResources().getString(R.string.valortotal) + " " + context.getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
                    valido = false;
                }
                else{
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    valido = true;
                }
            }
        }
        else{
            mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(context, context.getResources().getString(R.string.valortotal) + " " + context.getResources().getString(R.string.nao_vazio) + ".", Toast.LENGTH_LONG).show();
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

        switch (dataclick){
            case 1:
                mp_vencimento.setText(dataformatada);
                break;
            default:
                dataclick = 0;
                break;
        }
    }

    public void showDatePickerDialog_mp_dataVencimento(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getFragmentManager(), "datepicker");
        dataclick = 1;
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
