package libelulati.tripctrl.Senha;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import libelulati.tripctrl.Funcoes.Codigos;
import libelulati.tripctrl.Funcoes.Funcao;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;


public class EsqueciSenhaActivity extends AppCompatActivity {

    final int[] usuarioid = {0};
    RadioButton es_enviarsms, es_enviaremail, es_enviarcodusuario;
    Button es_enviar;
    EditText es_telefone, es_email, es_codusuario;
    int envio = 0, usid = 0;
    Context context;
    String codigogerado, datasolicitacao;
    boolean valido, validar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        es_enviarsms = (RadioButton) findViewById(R.id.rb_es_enviarsms);
        es_enviaremail = (RadioButton) findViewById(R.id.rb_es_enviaremail);
        es_enviarcodusuario = (RadioButton) findViewById(R.id.rb_es_codusuario);

        es_enviar = (Button) findViewById(R.id.bt_es_enviar);

        es_telefone = (EditText) findViewById(R.id.ed_es_telefone);
        es_email = (EditText) findViewById(R.id.ed_es_email);
        es_codusuario = (EditText) findViewById(R.id.ed_es_codusuario);

        es_email.requestFocus();


        es_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    es_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    verificaremail();
                }
            }
        });

        es_telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    es_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });

        es_codusuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    validar = false;
                    es_codusuario.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        });

        es_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();

                switch (envio) {
                    case 1:
                        verificaremail();
                        if (valido) {
                            enviarcodigoemail();
                            caixadialogo();
                        }
                        break;
                    case 2:
                        verificartelefone();
                        if (valido) {
                            enviarcodigosms();
                            caixadialogo();
                        }
                        break;
                    case 3:
                        verificarcodigousuario();
                        if (valido) {
                            Intent itns = new Intent(EsqueciSenhaActivity.this, NovaSenhaActivity.class);
                            Bundle bundle = new Bundle();

                            bundle.putInt("usid", usid);

                            itns.putExtras(bundle);

                            startActivityForResult(itns, 1);
                        }
                        break;
                }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
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

    public void onRadioButtonClicked(View view) {
        boolean check = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_es_enviaremail:
                if (check) {
                    envio = 1;
                    es_enviaremail.setChecked(true);
                    es_telefone.setVisibility(View.INVISIBLE);
                    es_codusuario.setVisibility(View.INVISIBLE);
                    es_telefone.setText("");
                    es_codusuario.setText("");
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(es_email.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (es_email.length() < 0) {
                        es_email.requestFocus();
                    } else {
                        verificaremail();
                        if (!valido) {
                            es_email.requestFocus();
                        }
                    }
                }
                break;
            case R.id.rb_es_enviarsms:
                if (check) {
                    envio = 2;
                    es_enviarsms.setChecked(true);
                    es_telefone.setVisibility(View.VISIBLE);
                    es_codusuario.setVisibility(View.INVISIBLE);
                    es_codusuario.setText("");
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(es_telefone.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (es_telefone.length() == 0) {
                        es_telefone.requestFocus();
                    } else {
                        verificartelefone();
                        if (!valido) {
                            es_telefone.requestFocus();
                        }
                    }
                }
                break;
            case R.id.rb_es_codusuario:
                if (check) {
                    envio = 3;
                    es_enviarcodusuario.setChecked(true);
                    es_telefone.setVisibility(View.INVISIBLE);
                    es_codusuario.setVisibility(View.VISIBLE);
                    es_codusuario.requestFocus();
                    es_telefone.setText("");
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(es_codusuario.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (es_codusuario.length() == 0) {
                        es_codusuario.requestFocus();
                    } else {
                        verificarcodigousuario();
                        if (!valido) {
                            es_codusuario.requestFocus();
                        }
                    }
                break;
                }
        }
    }

    public void verificaremail() {
        validar = Validar.ValidarEmail(es_email.length(), String.valueOf(es_email.getText()));
        if (!validar) {
            es_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.email) + " " + getApplicationContext().getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            Usuario usuario = usuarioDAO.buscaEmail(String.valueOf(es_email.getText().toString()));
            if (usuario == null) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.email) + " " + getApplicationContext().getResources().getString(R.string.nao_cadastrado) + ".", Toast.LENGTH_LONG).show();
                es_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
                valido = false;
            } else {
                es_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                valido = true;
                usuarioid[0] = usuario.getUs_id();
                usid = usuarioid[0];
            }
        }
    }

    public void verificartelefone() {
        validar = Validar.ValidarTelefone(es_telefone.length(), String.valueOf(es_telefone.getText()));
        if (!validar) {
            es_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.telefone) + " " + getApplicationContext().getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        } else {
            es_telefone.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void verificarcodigousuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        Usuario usuario = usuarioDAO.buscaEmail(String.valueOf(es_email.getText().toString()));
        validar = Validar.ValidarCodUsuario(String.valueOf(es_codusuario.getText()), usuario.getUs_cod());
        if (!validar) {
            es_codusuario.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.codusuario) + " " + getApplicationContext().getResources().getString(R.string.invalido) + ".", Toast.LENGTH_SHORT).show();
            valido = false;
        } else {
            es_codusuario.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            valido = true;
        }
    }

    public void caixadialogo() {
        AlertDialog codigoaltsenha;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getApplicationContext().getResources().getString(R.string.codigo_confirmacao));
        builder.setMessage(getApplicationContext().getResources().getString(R.string.codigo_confirme));
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_edittext, null));

        builder.setPositiveButton(getApplicationContext().getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() { // ALTERAR PARA STRING DO SISTEMA
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText codigo = (EditText) ((Dialog) dialog).findViewById(R.id.ed_dia_texto);
                boolean validar = Validar.ValidarTempoCodigo(datasolicitacao);
                if (validar) {
                    boolean validarcod = Validar.ValidarCodigoAltSenha(String.valueOf(codigo.getText()), codigogerado);
                    if (validarcod) {
                        Intent itns = new Intent(EsqueciSenhaActivity.this, NovaSenhaActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putInt("usid", usid);

                        itns.putExtras(bundle);

                        startActivityForResult(itns, 1);
                    } else {
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.nao_alterar) + " " + getApplicationContext().getResources().getString(R.string.a) + " " + getApplicationContext().getResources().getString(R.string.senha) + ".", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.codigo_espirado), Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton(getApplicationContext().getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() { // ALTERAR PARA STRING DO SISTEMA
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        codigoaltsenha = builder.create();
        codigoaltsenha.show();
    }

    public void enviarcodigoemail() {
        Codigos codigos = new Codigos();
        codigogerado = codigos.AltSenha();
        datasolicitacao = Funcao.DataAtual();
        if (codigogerado.length() > 0) {
            Toast.makeText(context, getApplicationContext().getResources().getString(R.string.codigosenha) + " " + getApplicationContext().getResources().getString(R.string.sucesso_gerado) + ".", Toast.LENGTH_LONG).show();
            //IMPLEMENTAR CÓDIGOS DE ENVIO DE E-MAIL
        } else {
            Toast.makeText(context, getApplicationContext().getResources().getString(R.string.codigosenha) + " " + getApplicationContext().getResources().getString(R.string.nao_gerado) + ", " + getApplicationContext().getResources().getString(R.string.novamente) + ".", Toast.LENGTH_LONG).show();
        }

    }

    public void enviarcodigosms() {
        Codigos codigos = new Codigos();
        codigogerado = codigos.AltSenha();
        datasolicitacao = Funcao.DataAtual();
        if (codigogerado.length() > 0) {
            Toast.makeText(context, getApplicationContext().getResources().getString(R.string.codigosenha) + " " + getApplicationContext().getResources().getString(R.string.sucesso_gerado) + ".", Toast.LENGTH_LONG).show();
            //IMPLEMENTAR CÓDIGOS DE ENVIO DE SMS
        } else {
            Toast.makeText(context, getApplicationContext().getResources().getString(R.string.codigosenha) + " " + getApplicationContext().getResources().getString(R.string.nao_gerado) + ", " + getApplicationContext().getResources().getString(R.string.novamente) + ".", Toast.LENGTH_LONG).show();
        }

    }
}
