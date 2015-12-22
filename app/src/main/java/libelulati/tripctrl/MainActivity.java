package libelulati.tripctrl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Senha.EsqueciSenhaActivity;
import libelulati.tripctrl.Strings.MensagensUsuario;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;
import libelulati.tripctrl.Usuario.UsuarioNovoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView main_novousuario = (TextView)findViewById(R.id.tx_main_novousuario);
        final EditText main_email = (EditText)findViewById(R.id.ed_main_email);
        final EditText main_senha = (EditText)findViewById(R.id.ed_main_senha);
        TextView main_esquecisenha = (TextView)findViewById(R.id.tx_main_esquecisenha);
        Button main_entrar = (Button)findViewById(R.id.bt_main_entrar);

        int contarusuarios = new UsuarioDAO(this).contar();

        if (contarusuarios < 2) {
            main_novousuario.setEnabled(true);
        } else {
            main_novousuario.setVisibility(View.INVISIBLE);
        }

        main_novousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_main_novousuario = new Intent(MainActivity.this, UsuarioNovoActivity.class);
                startActivity(it_main_novousuario);
            }
        });

        main_esquecisenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_main_esquecisenha = new Intent(MainActivity.this, EsqueciSenhaActivity.class);
                startActivity(it_main_esquecisenha);
            }
        });

        main_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = v.getContext();
                final int idus;

                UsuarioDAO usuarioDAO = new UsuarioDAO(context);
                Usuario usuario = usuarioDAO.buscaEmail(main_email.getText().toString());

                if(usuario == null){
                    Toast.makeText(context, MensagensUsuario.getUSUARIO() + MensagensUsuario.getNao_encontrado(), Toast.LENGTH_LONG).show();                 }
                else {
                    idus = usuario.getUs_id();
                    boolean validar = Validar.ValidarUsuarioSenha(main_email.getText().toString(),main_senha.getText().toString(),usuario.getUs_email(),usuario.getUs_senha());
                    if(validar){
                        Intent it_main_entrar = new Intent(MainActivity.this, InicioActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putInt(StringsNomes.getID(), idus);

                        it_main_entrar.putExtras(bundle);

                        startActivityForResult(it_main_entrar, 1);
                        finish();
                    }
                    else{
                        Toast.makeText(context, MensagensUsuario.getUSUARIO() + MensagensUsuario.getNao_encontrado(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
