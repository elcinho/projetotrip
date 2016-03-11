package libelulati.tripctrl.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuarios.EsqueciSenhaActivity;
import libelulati.tripctrl.Usuarios.UsuarioEditActivity;

public class MainActivity extends AppCompatActivity {

    Button bt_main_entrar;
    TextView tx_main_esquecisenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_main_entrar = (Button) findViewById(R.id.bt_main_entrar);
        tx_main_esquecisenha = (TextView)findViewById(R.id.tx_main_esquecisenha);
        bt_main_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_main_entrar = new Intent (MainActivity.this, InicioActivity.class);
                startActivity(it_main_entrar);
            }
        });

        tx_main_esquecisenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_esqueci_senha = new Intent(MainActivity.this, EsqueciSenhaActivity.class);
                startActivity(it_esqueci_senha);
            }
        });
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.mn_main_novousuario:
                ExibirUsuarioNew();
                break;
        }
        return true;
    }

    public void ExibirUsuarioNew(){
        Intent it_novo_usuario = new Intent(MainActivity.this, UsuarioEditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("novo", 1);
        bundle.putInt(Nomes.getID(), 0);
        bundle.putInt(Nomes.getUsSemsenha(), 0);
        bundle.putString(Nomes.getUsNome(), null);
        bundle.putString(Nomes.getUsEmail(), null);
        bundle.putString(Nomes.getUsDtnasc(), null);
        bundle.putString(Nomes.getUsSenha(), null);

        it_novo_usuario.putExtras(bundle);
        startActivityForResult(it_novo_usuario,1);
    }*/
}
