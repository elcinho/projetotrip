package libelulati.tripctrl.Senha;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.MainActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;
import libelulati.tripctrl.Usuario.UsuarioEditActivity;

public class NovaSenhaActivity extends AppCompatActivity {

    EditText ns_senha, ns_confirmesenha;
    Button ns_salvar;
    boolean validar, valido;
    int idus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_senha);

        ns_senha = (EditText) findViewById(R.id.ed_ns_senha);
        ns_confirmesenha = (EditText) findViewById(R.id.ed_ns_confirmesenha);
        ns_salvar = (Button) findViewById(R.id.bt_ns_salvar);

        Intent itns = getIntent();
        Bundle bundle = itns.getExtras();

        idus = bundle.getInt("usid");

        ns_senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    ns_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    verificarsenha();
                }
            }
        });

        ns_confirmesenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    ns_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    verificarconfirmesenha();
                }

            }
        });

        ns_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                verificarsenha();
                verificarconfirmesenha();
                if (valido) {
                    Usuario usuario = new Usuario();

                    usuario.setUs_senha(ns_senha.getText().toString());

                    boolean sucesso = new UsuarioDAO(context).alterarsenha(usuario, idus);
                    if (sucesso) {
                        Toast.makeText(context, context.getResources().getString(R.string.senha) + " " + context.getResources().getString(R.string.sucesso_alterada) + ".", Toast.LENGTH_LONG).show();
                        if(idus == 0){
                            startActivity(new Intent(NovaSenhaActivity.this, MainActivity.class));
                            finish();
                        }
                        else{
                            onBackPressed();
                        }
                    } else {
                        Toast.makeText(context, context.getResources().getString(R.string.senha) + " " + context.getResources().getString(R.string.nao_alterada) + ".", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.campos_invalidos) + ". " + context.getResources().getString(R.string.senha) + " " + context.getResources().getString(R.string.nao_alterada) + ".", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void verificarsenha() {
        validar = Validar.ValidarSenha(String.valueOf(ns_senha.getText()));
        if (!validar) {
            Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.senha) + " " + this.getResources().getString(R.string.invalida) + ".", Toast.LENGTH_LONG).show();
            ns_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            valido = false;
        } else {
            valido = true;
        }
    }

    public void verificarconfirmesenha() {
        validar = Validar.ValidarConfirmeSenha(String.valueOf(ns_senha.getText()), String.valueOf(ns_confirmesenha.getText()));
        if (!validar) {
            Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.senhas_diferentes) + ".", Toast.LENGTH_LONG).show();
            ns_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            ns_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            valido = false;
        } else {
            valido = true;
        }
    }

}
