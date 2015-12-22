package libelulati.tripctrl.Usuario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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

import libelulati.tripctrl.Funcoes.Codigos;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class UsuarioNovoActivity extends AppCompatActivity {

    EditText us_nome, us_dtnasc, us_email, us_localizacao, us_codarea, us_telefone, us_senha, us_confirmesenha;
    Button us_salvar;
    int ano, mes, dia;
    boolean valido, validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_novo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        us_nome = (EditText) findViewById(R.id.ed_nu_nome);
        us_dtnasc = (EditText) findViewById(R.id.ed_nu_dtnasc);
        us_email = (EditText) findViewById(R.id.ed_nu_email);
        us_localizacao = (EditText) findViewById(R.id.ed_nu_localizacao);
        us_codarea = (EditText) findViewById(R.id.ed_nu_codarea);
        us_telefone = (EditText) findViewById(R.id.ed_nu_telefone);
        us_senha = (EditText) findViewById(R.id.ed_nu_senha);
        us_confirmesenha = (EditText) findViewById(R.id.ed_nu_confirmesenha);
        us_salvar = (Button) findViewById(R.id.bt_nu_salvar);

        us_dtnasc.setInputType(InputType.TYPE_NULL);

        us_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    us_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnome();
                }
            }
        });

        us_dtnasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    validar = false;
                    us_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnascimento();
                }
            }
        });

        us_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    validar = false;
                    us_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificaremail();
                }
            }
        });

        us_telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    validar = false;
                    us_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificartelefone();
                }
            }
        });

        us_senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    validar = false;
                    us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarsenha();
                }
            }
        });

        us_confirmesenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    validar = false;
                    us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    us_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(us_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarconfirmesenha();
                }
            }
        });

        us_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valido){
                Codigos codigos = new Codigos();

                final Context context = v.getContext();

                Usuario usuario = new Usuario();

                usuario.setUs_nome(us_nome.getText().toString());
                usuario.setUs_cod(codigos.CodUsuario());
                usuario.setUs_dtnasc(us_dtnasc.getText().toString());
                usuario.setUs_email(us_email.getText().toString());
                usuario.setUs_latitude(us_localizacao.getText().toString());
                usuario.setUs_longitude(us_localizacao.getText().toString());
                usuario.setUs_codarea(us_codarea.getText().toString());
                usuario.setUs_telefone(us_telefone.getText().toString());
                usuario.setUs_senha(us_senha.getText().toString());
                usuario.setUs_confirmesenha(us_confirmesenha.getText().toString());

                boolean sucesso = new UsuarioDAO(context).criar(usuario);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getUSUARIO() + MensagensUsuario.getCriado_sucesso(), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_criar() + MensagensUsuario.getUSUARIO(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
                else{
                    Toast.makeText(getApplicationContext(), MensagensUsuario.getCampos_invalidos(), Toast.LENGTH_LONG).show();
                    us_nome.requestFocus();
                }
                }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mn_ac_voltar) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void verificarnome() {
        validar = Validar.ValidarNome(String.valueOf(us_nome.getText().toString()));
        if (!validar) {
            us_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getNome_invalido(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificaremail() {
        validar = Validar.ValidarEmail(us_email.length(), String.valueOf(us_email.getText().toString()));
        if (!validar) {
            us_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getEmail_invalido(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificarnascimento() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date data = sdf.parse(String.valueOf(us_dtnasc.getText().toString()));
            validar = Validar.ValidarDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!validar) {
            us_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), "Data inválida", Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificartelefone() {
        validar = Validar.ValidarTelefone(us_telefone.length(), String.valueOf(us_telefone.getText().toString()));
        if (!validar) {
            us_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getTelefone_invalido(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificarsenha() {
        validar = Validar.ValidarSenha(String.valueOf(us_senha.getText().toString()));
        if (!validar) {
            us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getSenha_invalida(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificarconfirmesenha() {
        validar = Validar.ValidarConfirmeSenha(String.valueOf(us_senha.getText().toString()), String.valueOf(us_confirmesenha.getText().toString()));
        if (!validar) {
            us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            us_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getSenhas_diferentes(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            us_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            us_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
        us_dtnasc.setText(new StringBuilder().append(dia).append("-").append(mes).append("-").append(ano));
    }

    public void showDatePickerDialog(View view) {
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
            mes = month;
            dia = day;

            AtualizarData();
        }
    }


}
