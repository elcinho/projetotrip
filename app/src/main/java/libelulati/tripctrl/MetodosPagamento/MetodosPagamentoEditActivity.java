package libelulati.tripctrl.MetodosPagamento;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;



public class MetodosPagamentoEditActivity extends AppCompatActivity {

    private Spinner spinnerTipoPagamento, spinnerViagem;
    private List<String>  listaTipoPagamento, listaViagem;
    MetodosPagamentoDAO data;
    private EditText mp_viagem, mp_tipoPagamento;
    int usuario = InicioActivity.getId_uslogado();
    boolean validar, valido;
    Context context;
    EditText mp_detalhe, mp_valor, mp_vencimento;
    int ano, mes, dia, dataclick;
    StringBuilder dataformatada;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_edit);

        context = MetodosPagamentoEditActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            spinnerViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
            spinnerTipoPagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
            mp_viagem = (EditText) findViewById(R.id.ed_mp_viagem);
            mp_tipoPagamento = (EditText) findViewById(R.id.ed_mp_tipoPagamento);
            mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
            mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
            mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);

        mp_tipoPagamento.setInputType(InputType.TYPE_NULL);


        mp_viagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_viagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificaViagem();
                }
            }
        });
        mp_vencimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_vencimento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
            getMenuInflater().inflate(R.menu.menu_global, menu);
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
            MetodosPagamento metodosPagamento = new MetodosPagamento();

            metodosPagamento.setUs_id(usuario);
            metodosPagamento.setDv_id(Integer.parseInt(mp_viagem.getText().toString()));
            metodosPagamento.setTp_id(Integer.parseInt(mp_tipoPagamento.getText().toString()));
            metodosPagamento.setMe_detalhes(mp_detalhe.getText().toString());
            metodosPagamento.setMe_valor(Integer.parseInt(mp_valor.getText().toString()));
            metodosPagamento.setMe_vencimento(mp_vencimento.getText().toString());

            if(valido){
                boolean sucesso = new MetodosPagamentoDAO(context).criar(metodosPagamento);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getMETODOPAGAMENTO() + MensagensUsuario.getCriado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_criar() + MensagensUsuario.getMETODOPAGAMENTO(), Toast.LENGTH_LONG).show();
                }

                finish();
            }
            else{
                Toast.makeText(context, "Há campos inválidos. O registro não será gravado;", Toast.LENGTH_SHORT).show();
            }


        }

    public void verificaViagem(){
            validar = Validar.ValidarNome(String.valueOf(mp_viagem.getText().toString()));
            if(!validar){
                mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                Toast.makeText(context, "Nome da viagem inválido", Toast.LENGTH_SHORT).show();
                valido = false;
            }
            else{
                mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                valido = true;
            }
        }


        public void verificarvalor(){
            double valor = 0;
            valor = Double.parseDouble(mp_valor.getText().toString());
            if(valor == 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                    Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();
                    valido = false;
                }
                else{
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    valido = true;
                }
            }
            else{
                mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();
            }
        }

    public void verificardatavencimento(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(mp_vencimento.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(context, "A data não pode ser anterior à atual", Toast.LENGTH_SHORT).show();
            valido = false;
        }
        else{
            mp_vencimento.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
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
            case 2:
                mp_vencimento.setText(dataformatada);
                break;
            default:
                dataclick = 0;
                break;
        }
    }

    public void showDatePickerDialog_vin_dtini(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
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
