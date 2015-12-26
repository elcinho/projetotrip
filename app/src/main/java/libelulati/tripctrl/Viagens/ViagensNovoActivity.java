package libelulati.tripctrl.Viagens;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class ViagensNovoActivity extends AppCompatActivity {

    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, dataclick;
    StringBuilder dataformatada;
    boolean validar, valido;
    Context context;
    EditText vi_nome, vi_localizacao, vi_dtinicio, vi_dtfim, vi_tipotransporte, vi_transporte, vi_tipohospedagem, vi_hospedagem, vi_valortotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_novo);

        context = ViagensNovoActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vi_nome = (EditText) findViewById(R.id.ed_vin_nome);
        vi_localizacao = (EditText) findViewById(R.id.ed_vin_localizacao);
        vi_dtinicio = (EditText) findViewById(R.id.ed_vin_inicio);
        vi_dtfim = (EditText) findViewById(R.id.ed_vin_fim);
        vi_tipotransporte = (EditText) findViewById(R.id.ed_vin_tipotransporte);
        vi_transporte = (EditText) findViewById(R.id.ed_vin_transporte);
        vi_tipohospedagem = (EditText) findViewById(R.id.ed_vin_tipohospedagem);
        vi_hospedagem = (EditText) findViewById(R.id.ed_vin_hospedagem);
        vi_valortotal = (EditText) findViewById(R.id.ed_vin_valortotal);

        vi_dtinicio.setInputType(InputType.TYPE_NULL);
        vi_dtfim.setInputType(InputType.TYPE_NULL);

        vi_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    vi_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_nome.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnome();
                }
            }
        });

        vi_dtinicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_dtinicio.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    vi_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else{
                    verificardtini();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_dtinicio.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        vi_dtfim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    vi_dtfim.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardtfim();
                }
            }
        });

        vi_valortotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    valido = false;
                    vi_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vi_valortotal.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
        Viagens viagens = new Viagens();

        viagens.setUs_id(usuario);
        viagens.setVi_nome(vi_nome.getText().toString());
        viagens.setVi_local(vi_localizacao.getText().toString());
        viagens.setVi_dtini(vi_dtinicio.getText().toString());
        viagens.setVi_dtfim(vi_dtfim.getText().toString());
        viagens.setTr_id(vi_tipotransporte.getText().toString());//mudar para o ID do SPINNER
        viagens.setVi_transporte(vi_transporte.getText().toString());
        viagens.setHo_id(vi_tipohospedagem.getText().toString());//mudar para o ID do SPINNER
        viagens.setVi_hospedagem(vi_hospedagem.getText().toString());
        viagens.setVi_valortotal(vi_valortotal.getText().toString());

        if(valido){
            boolean sucesso = new ViagensDAO(context).criar(viagens);

            if (sucesso) {
                Toast.makeText(context, MensagensUsuario.getVIAGEM() + MensagensUsuario.getCriado_sucesso(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, MensagensUsuario.getErro_criar() + MensagensUsuario.getVIAGEM(), Toast.LENGTH_LONG).show();
            }

            finish();
        }
        else{
            Toast.makeText(context, "Há campos inválidos. O registro não será gravado;", Toast.LENGTH_SHORT).show(); //ALTERAR PARA STRING DO SISTEMA
        }


    }

    public void verificarnome(){
        validar = Validar.ValidarNome(String.valueOf(vi_nome.getText().toString()));
        if(!validar){
            vi_nome.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, "Nome da viagem inválido", Toast.LENGTH_SHORT).show(); //ALTERAR PARA STRINGS DO SISTEMA
            valido = false;
        }
        else{
            vi_nome.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }

    public void verificardtini(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(vi_dtinicio.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            vi_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, "A data não pode ser anterior à atual", Toast.LENGTH_SHORT).show(); //ALTERAR PARA STRINGS DO SISTEMA
            valido = false;
        }
        else{
            vi_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }

    public void verificardtfim(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inicio = sdf.parse(String.valueOf(vi_dtinicio.getText().toString()));
            Date fim = sdf.parse(String.valueOf(vi_dtfim.getText().toString()));
            validar = Validar.ValidarDataFim(inicio, fim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            vi_dtfim.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(context, "O fim da viagem deve ser após o início", Toast.LENGTH_SHORT).show(); //ALTERAR PARA STRINGS DO SISTEMA
            valido = false;
        }
        else{
            vi_dtfim.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }

    public void verificarvalor(){
        double valor = 0;
        if(vi_valortotal.length()>0){
            valor = Double.parseDouble(vi_valortotal.getText().toString());
        }
        else{
            vi_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, "Valor não pode ser vazio", Toast.LENGTH_SHORT).show();//ALTERAR PARA STRINGS DO SISTEMA
        }
        if(valor != 0){
            validar = Validar.ValidarValor(valor);
            if(!validar){
                vi_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();//ALTERAR PARA STRINGS DO SISTEMA
                valido = false;
            }
            else{
                vi_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                valido = true;
            }
        }
        else{
            vi_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();//ALTERAR PARA STRINGS DO SISTEMA
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
                vi_dtinicio.setText(dataformatada);
                break;
            case 2:
                vi_dtfim.setText(dataformatada);
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

    public void showDatePickerDialog_vin_dtfim(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
        dataclick = 2;
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
