package libelulati.tripctrl.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import libelulati.tripctrl.Aplicativo.TermoUsoActivity;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuarios.EsqueciSenhaActivity;
import libelulati.tripctrl.Usuarios.Usuario;
import libelulati.tripctrl.Usuarios.UsuarioEditActivity;
import libelulati.tripctrl.Usuarios.Usuario_DAO;

public class MainActivity extends AppCompatActivity {

    Button bt_main_entrar;
    int id_usuario = 1;
    int us_uso = 0;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        bt_main_entrar = (Button) findViewById(R.id.bt_main_entrar);
        bt_main_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscarUsuario();
                if (us_uso == 0) {
                    Intent it_main_termouso = new Intent(context, TermoUsoActivity.class);
                    startActivity(it_main_termouso);
                } else {
                    Intent it_main_entrar = new Intent(MainActivity.this, InicioActivity.class);
                    startActivity(it_main_entrar);
                }
            }
        });
    }

    public void BuscarUsuario(){
        Usuario usuario = new Usuario_DAO(context).buscaId(id_usuario);
        if(usuario != null){
            us_uso = usuario.getUs_uso();
        }
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
