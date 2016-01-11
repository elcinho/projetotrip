package libelulati.tripctrl;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import libelulati.tripctrl.Login.Login;
import libelulati.tripctrl.Senha.EsqueciSenhaActivity;
import libelulati.tripctrl.Usuario.UsuarioNovoActivity;

public class MainActivity extends AppCompatActivity {
    Button entrar, cadastrar;
    TextView esqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrar = (Button)findViewById(R.id.bt_main_entrar);
        cadastrar = (Button)findViewById(R.id.bt_main_cadastrar);
        esqueciSenha = (TextView)findViewById(R.id.tx_main_esquecisenha);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirLogin();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoUsuario();
            }
        });

        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esqueciSenha();
            }
        });
    }

    public void exibirLogin(){
        DialogFragment login = new Login();
        login.show(getFragmentManager(), "login");
    }

    public void novoUsuario(){
        Intent novo = new Intent(MainActivity.this, UsuarioNovoActivity.class);
        startActivity(novo);
    }

    public void esqueciSenha(){
        Intent senha = new Intent(MainActivity.this, EsqueciSenhaActivity.class);
        startActivity(senha);
    }

}
