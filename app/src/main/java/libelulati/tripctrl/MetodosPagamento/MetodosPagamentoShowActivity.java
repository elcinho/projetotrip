package libelulati.tripctrl.MetodosPagamento;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import libelulati.tripctrl.Viagens.Viagens;
import libelulati.tripctrl.Viagens.ViagensDAO;

public class MetodosPagamentoShowActivity extends AppCompatActivity {

    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, dataclick , mp_id , mp_menu;
    StringBuilder dataformatada;
    boolean validar, valido;
    Context context;
    MenuItem mp_salvar;
    Spinner spinnerTipoPagamento , spinnerViagem;
    private List<String> listaTipoPagamento, listaViagem;
    private EditText mp_valor , mp_detalhe, mp_viagem, mp_vencimento , mp_tipopagamento;
    MeuSpinner meuSpinner = new MeuSpinner();
    FloatingActionButton fab_editar;
    String titulo_show, titulo_edit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_show);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = MetodosPagamentoShowActivity.this;
        mp_menu = 1;
        invalidateOptionsMenu();

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_me_editar);
        mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
        mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
        mp_tipopagamento = (EditText) findViewById(R.id.ed_mp_tipoPagamento);
        mp_viagem = (EditText) findViewById(R.id.ed_mp_viagem);
        mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);
        spinnerViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
        spinnerTipoPagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mp_viagem.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent it_vis_show = getIntent();
        Bundle bundle = it_vis_show.getExtras();

        mp_id = bundle.getInt(StringsNomes.getMeId());
        mp_viagem.setText(bundle.getString(StringsNomes.getViId()));
        mp_tipopagamento.setText(bundle.getString(StringsNomes.getTpId()));
        mp_detalhe.setText(bundle.getString(StringsNomes.getMeDetalhe()));
        mp_valor.setText(bundle.getString(StringsNomes.getMeValor()));
        mp_vencimento.setText(bundle.getString(StringsNomes.getMeVencimento()));

        titulo_show = context.getResources().getString(R.string.title_activity_metodos_pagamento_show) + " " + mp_viagem.getText().toString();
        titulo_edit = context.getResources().getString(R.string.title_activity_metodos_pagamento_edit) + " " + mp_viagem.getText().toString();

        getSupportActionBar().setTitle(titulo_show);

        listaViagem = new MetodosPagamentoDAO(context).SpinerViagem();
        listaTipoPagamento = new MetodosPagamentoDAO(context).SpinerTipoPagamento();


        mp_vencimento.setInputType(InputType.TYPE_NULL);
        mp_viagem.setInputType(InputType.TYPE_NULL);
        mp_tipopagamento.setInputType(InputType.TYPE_NULL);

      mp_viagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
              if (hasFocus) {
                  valido = false;
                  mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
              } else {
                  ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_viagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                  verificarnome();
              }
          }
      });

        mp_vencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_vencimento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
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

        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportActionBar().setTitle(titulo_edit);

                mp_valor.setEnabled(true);
                mp_vencimento.setEnabled(true);
                mp_detalhe.setEnabled(true);
                mp_tipopagamento.setEnabled(true);
                mp_viagem.setEnabled(true);
                spinnerTipoPagamento.setVisibility(View.VISIBLE);
                spinnerViagem.setVisibility(View.VISIBLE);

                mp_tipopagamento.setTextColor(getResources().getColor(R.color.colorBranco));
                mp_viagem.setTextColor(getResources().getColor(R.color.colorBranco));

                meuSpinner.preencherSpinner(context, listaTipoPagamento, spinnerTipoPagamento);
                meuSpinner.posicaoSelecionada(spinnerTipoPagamento, mp_tipopagamento.getText().toString());
                meuSpinner.selecionarItem(spinnerTipoPagamento, mp_tipopagamento);

                meuSpinner.preencherSpinner(context, listaViagem,spinnerViagem);
                meuSpinner.posicaoSelecionada(spinnerViagem, mp_vencimento.getText().toString());
                meuSpinner.selecionarItem(spinnerViagem, mp_viagem);

                mp_viagem.requestFocus();

                fab_editar.setVisibility(View.INVISIBLE);
                mp_menu = 2;
                invalidateOptionsMenu();
            }
        });
    }

    public void salvar(){




        MetodosPagamento metodosPagamento = new MetodosPagamento();

        metodosPagamento.setUs_id(usuario);
        metodosPagamento.setMe_detalhes(mp_detalhe.getText().toString());
        metodosPagamento.setMe_vencimento(mp_vencimento.getText().toString());
        metodosPagamento.setTp_id(Integer.parseInt(mp_tipopagamento.getText().toString()));
        metodosPagamento.setDv_id(Integer.parseInt(mp_viagem.getText().toString()));
        metodosPagamento.setMe_valor(Integer.parseInt(mp_valor.getText().toString()));


        boolean sucesso = new MetodosPagamentoDAO(context).atualizar(metodosPagamento, mp_id);

        if (sucesso) {
            Toast.makeText(context, context.getResources().getString(R.string.metodospagamento) + " " + metodosPagamento.getMe_id() + " " + context.getResources().getString(R.string.sucesso_editado) + ".", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.erro_editar) + " " + context.getResources().getString(R.string.metodospagamento) + " " + metodosPagamento.getMe_id(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        mp_salvar = menu.findItem(R.id.mn_gb_salvar);
        switch (mp_menu){
            case 1:
                mp_salvar.setVisible(false);
                break;
            case 2:
                mp_salvar.setVisible(true);
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

    public void verificarnome(){
        validar = Validar.ValidarNome(String.valueOf(mp_viagem.getText().toString()));
        if(!validar){
            mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, context.getResources().getString(R.string.viagem) + " " + context.getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        }
        else{
            mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }

    public void verificardtini(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(mp_vencimento.getText().toString()));
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
            mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }


    public void verificarvalor(){
        double valor = 0;
        if(mp_valor.length() > 1){
            valor = Double.parseDouble(mp_valor.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                    Toast.makeText(context, context.getResources().getString(R.string.valortotal) + " " + context.getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
                    valido = false;
                }
                else{
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    valido = true;
                }
            }
        }
        else{
            mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
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
                mp_valor.setText(dataformatada);
                break;
            default:
                dataclick = 0;
                break;
        }
    }

    public void showDatePickerDialog_mp_dataVencimento(View view) {
        android.app.DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getFragmentManager(), "datepicker");
        dataclick = 1;
    }

    public class DatePickerFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
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
