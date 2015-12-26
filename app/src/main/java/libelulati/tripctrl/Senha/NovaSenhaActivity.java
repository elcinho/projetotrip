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
                        Toast.makeText(context, "Senha alterada com sucesso.", Toast.LENGTH_LONG).show(); //ALTERAR PARA STRING DO SISTEMA
                        if(idus == 0){
                            startActivity(new Intent(NovaSenhaActivity.this, MainActivity.class));
                            finish();
                        }
                        else{
                            onBackPressed();
                        }
                    } else {
                        Toast.makeText(context, "A senha não foi alterada", Toast.LENGTH_LONG).show(); //ALTERAR PARA STRING DO SISTEMA
                    }
                } else {
                    Toast.makeText(context, "Campos inválidos, não é possível alterar a senha.", Toast.LENGTH_LONG).show(); //ALTERAR PARA STRING DO SISTEMA
                }
            }
        });
    }


    public void verificarsenha() {
        validar = Validar.ValidarSenha(String.valueOf(ns_senha.getText()));
        if (!validar) {
            Toast.makeText(getApplicationContext(), "Senha inválida", Toast.LENGTH_LONG).show(); //ALTERAR PARA STRING DO SISTEMA
            ns_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            valido = false;
        } else {
            valido = true;
        }
    }

    public void verificarconfirmesenha() {
        validar = Validar.ValidarConfirmeSenha(String.valueOf(ns_senha.getText()), String.valueOf(ns_confirmesenha.getText()));
        if (!validar) {
            Toast.makeText(getApplicationContext(), "As senhas não coincidem", Toast.LENGTH_LONG).show(); //ALTERAR PARA STRING DO SISTEMA
            ns_senha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            ns_confirmesenha.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            valido = false;
        } else {
            valido = true;
        }
    }

}
