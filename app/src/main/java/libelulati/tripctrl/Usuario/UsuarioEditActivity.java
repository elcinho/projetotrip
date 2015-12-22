package libelulati.tripctrl.Usuario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Senha.NovaSenhaActivity;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class UsuarioEditActivity extends AppCompatActivity {
    int usuarioid = InicioActivity.getId_uslogado();
    boolean valido, validar;
    int ano, mes, dia;

    EditText use_nome, use_dtnasc, use_codarea, use_telefone, use_localizacao;
    Button use_salvar, use_alterarsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        use_salvar = (Button) findViewById(R.id.bt_use_salvar);
        use_alterarsenha = (Button) findViewById(R.id.bt_use_alterarsenha);

        use_nome = (EditText) findViewById(R.id.ed_use_nome);
        use_dtnasc = (EditText) findViewById(R.id.ed_use_dtnasc);
        use_codarea = (EditText) findViewById(R.id.ed_use_codarea);
        use_telefone = (EditText) findViewById(R.id.ed_use_telefone);
        use_localizacao = (EditText) findViewById(R.id.ed_use_localizacao);

        use_dtnasc.setInputType(InputType.TYPE_NULL);

        Intent it_use_edusuario = getIntent();
        Bundle bundle = it_use_edusuario.getExtras();

        use_nome.setText(bundle.getString(StringsNomes.getUsNome()));
        use_dtnasc.setText(bundle.getString(StringsNomes.getUsDtnasc()));
        use_codarea.setText(bundle.getString(StringsNomes.getUsCodarea()));
        use_telefone.setText(bundle.getString(StringsNomes.getUsTelefone()));
        use_localizacao.setText(bundle.getString(StringsNomes.getUsLongitude()));

        use_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    use_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(use_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnome();
                }
            }
        });

        use_dtnasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(use_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    validar = false;
                    use_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(use_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnascimento();
                }
            }
        });

        use_telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    use_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(use_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificartelefone();
                }
            }
        });

        use_alterarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent itns = new Intent(UsuarioEditActivity.this, NovaSenhaActivity.class);
                Bundle bundle = new Bundle();

                bundle.putInt("usid", usuarioid);

                itns.putExtras(bundle);

                startActivityForResult(itns, 1);
            }
        });

        use_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = v.getContext();
                Usuario usuario = new Usuario();

                usuario.setUs_nome(use_nome.getText().toString());
                usuario.setUs_dtnasc(use_dtnasc.getText().toString());
                usuario.setUs_latitude(use_localizacao.getText().toString());
                usuario.setUs_longitude(use_localizacao.getText().toString());
                usuario.setUs_codarea(use_codarea.getText().toString());
                usuario.setUs_telefone(use_telefone.getText().toString());

                boolean sucesso = new UsuarioDAO(context).atualizar(usuario, usuarioid);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getUSUARIO() + " " + usuario.getUs_nome() + " " + MensagensUsuario.getEditado_sucesso(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UsuarioEditActivity.this, InicioActivity.class));
                    finish();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_editar() + MensagensUsuario.getUSUARIO() + " " + usuario.getUs_nome(), Toast.LENGTH_LONG).show();
                    use_nome.requestFocus();
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
        validar = Validar.ValidarNome(String.valueOf(use_nome.getText().toString()));
        if (!validar) {
            use_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getNome_invalido(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            use_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificarnascimento() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date data = sdf.parse(String.valueOf(use_dtnasc.getText().toString()));
            validar = Validar.ValidarDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!validar) {
            use_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), "Data inválida", Toast.LENGTH_LONG).show(); //alterar para string do sistema
            valido = false;
        } else {
            use_dtnasc.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificartelefone() {
        validar = Validar.ValidarTelefone(use_telefone.length(), String.valueOf(use_telefone.getText().toString()));
        if (!validar) {
            use_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), MensagensUsuario.getTelefone_invalido(), Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            use_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
        use_dtnasc.setText(new StringBuilder().append(dia).append("-").append(mes).append("-").append(ano));
    }

    public void showDatePickerDialog_edt(View view) {
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
