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

public class MetodosPagamentoNovoActivity extends AppCompatActivity {

    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, idviagem = 0;
    StringBuilder dataformatada;
    boolean validar, v_detalhe, v_dtvenc, v_valor;
    Context context;
    EditText mp_detalhe, mp_viagem, mp_tipopagamento, mp_dtvenc, mp_valor;
    Spinner mp_sp_tppagamento, mp_sp_viagem;
    List<String> listatipopagamento, listaviagem;
    MeuSpinner meuSpinner = new MeuSpinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodosdepagamento_novo);

        context = MetodosPagamentoNovoActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mp_detalhe = (EditText)findViewById(R.id.ed_mpn_detalhe);
        mp_viagem = (EditText)findViewById(R.id.ed_mpn_viagem);
        mp_tipopagamento = (EditText)findViewById(R.id.ed_mpn_tipopagamento);
        mp_dtvenc = (EditText)findViewById(R.id.ed_mpn_dtvenc);
        mp_valor = (EditText)findViewById(R.id.ed_mpn_valortotal);
        mp_sp_tppagamento = (Spinner)findViewById(R.id.sp_mpn_tipopagamento);
        mp_sp_viagem = (Spinner)findViewById(R.id.sp_mpn_viagem);

        Intent viagem = getIntent();
        Bundle bundle = viagem.getExtras();
        if(bundle == null){
            idviagem = 0;
        }
        else{
            idviagem = bundle.getInt(StringsNomes.getID());
        }

        listatipopagamento = new MetodosPagamentoDAO(context).sp_tipospagamento();
        listaviagem = new MetodosPagamentoDAO(context).sp_viagens(usuario);

        mp_viagem.setInputType(InputType.TYPE_NULL);
        mp_tipopagamento.setInputType(InputType.TYPE_NULL);
        mp_dtvenc.setInputType(InputType.TYPE_NULL);

        if(idviagem == 0){
            meuSpinner.preencherSpinner(context, listaviagem, mp_sp_viagem);
            meuSpinner.selecionarItem(mp_sp_viagem, mp_viagem);
        }
        else{
            relacionarViagem();
        }


        mp_viagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_viagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        meuSpinner.preencherSpinner(context, listatipopagamento, mp_sp_tppagamento);
        meuSpinner.selecionarItem(mp_sp_tppagamento, mp_tipopagamento);
        mp_tipopagamento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_tipopagamento.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        mp_detalhe.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_detalhe.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardetalhe();
                }
            }
        });

        mp_dtvenc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_dtvenc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_dtvenc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardtvenc();
                }
            }
        });

        mp_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
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
        metodosPagamento.setMp_detalhe(mp_detalhe.getText().toString());
        metodosPagamento.setVi_id(mp_viagem.getText().toString());
        metodosPagamento.setTp_id(mp_tipopagamento.getText().toString());
        metodosPagamento.setMp_dtvenc(mp_dtvenc.getText().toString());
        metodosPagamento.setMp_valor(mp_valor.getText().toString());

        if(IsValido()){
            boolean sucesso = new MetodosPagamentoDAO(context).criar(metodosPagamento);

            if (sucesso) {
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_metodopagamento), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_metodopagamento), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void relacionarViagem(){
        String viagemrelacionada = new MetodosPagamentoDAO(context).relacionarViagem(idviagem);
        mp_sp_viagem.setVisibility(View.INVISIBLE);
        mp_viagem.setText(viagemrelacionada);
        mp_viagem.setEnabled(false);
        mp_viagem.setTextColor(context.getResources().getColor(R.color.cinza));
    }

    public void verificardetalhe(){
        validar = Validar.ValidarNome(String.valueOf(mp_detalhe.getText().toString()));
        if(!validar){
            mp_detalhe.setError(context.getResources().getString(R.string.validar_detalhe));
            v_detalhe = false;
        }
        else{
            v_detalhe = true;
        }
    }

    public void verificardtvenc(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(mp_dtvenc.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar) {
            mp_dtvenc.setError(context.getResources().getString(R.string.validar_dtinicio));
            v_dtvenc = false;
        }
        else{
            v_dtvenc = true;
        }
    }

    public void verificarvalor(){
        double valor = 0;
        if(mp_valor.length()>0){
            valor = Double.parseDouble(mp_valor.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mp_valor.setError(context.getResources().getString(R.string.validar_valor));
                    v_valor = false;
                }
                else{
                    v_valor = true;
                }
            }
        }
        else{
            mp_valor.setError(context.getResources().getString(R.string.validar_valor));
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
        mp_dtvenc.setText(dataformatada);
    }

    public void showDatePickerDialog_mpn_dtvenc(View view) {
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
