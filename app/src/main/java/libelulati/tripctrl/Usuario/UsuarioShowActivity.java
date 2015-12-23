package libelulati.tripctrl.Usuario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class UsuarioShowActivity extends AppCompatActivity {

    FloatingActionButton fab_editar;
    TextView uss_nome, uss_dtnasc, uss_email, uss_telefone, uss_localizacao, uss_cod;
    Context contexto;
    int usuaruioid = InicioActivity.getId_uslogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_us_editar);

        uss_nome = (TextView) findViewById(R.id.tx_uss_nome);
        uss_dtnasc = (TextView) findViewById(R.id.tx_uss_dtnasc);
        uss_email = (TextView) findViewById(R.id.tx_uss_email);
        uss_telefone = (TextView) findViewById(R.id.tx_uss_telefone);
        uss_localizacao = (TextView) findViewById(R.id.tx_uss_localizacao);
        uss_cod = (TextView) findViewById(R.id.tx_uss_codusuario);


        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contexto = view.getContext();

                UsuarioDAO usuarioDAO = new UsuarioDAO(contexto);
                Usuario usuario = usuarioDAO.buscaId(usuaruioid);

                Intent it_usedit = new Intent(UsuarioShowActivity.this, UsuarioEditActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString(StringsNomes.getUsNome(), usuario.getUs_nome());
                bundle.putString(StringsNomes.getUsDtnasc(), usuario.getUs_dtnasc());
                bundle.putString(StringsNomes.getUsCodarea(), usuario.getUs_codarea());
                bundle.putString(StringsNomes.getUsTelefone(), usuario.getUs_telefone());
                bundle.putString(StringsNomes.getUsLongitude(), usuario.getUs_longitude());

                it_usedit.putExtras(bundle);

                startActivityForResult(it_usedit, 1);
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

    @Override
    public void onResume(){
        AtualizarDados();
        super.onResume();
    }

    public void AtualizarDados(){
        contexto = getApplicationContext();

        UsuarioDAO usuarioDAO = new UsuarioDAO(contexto);
        Usuario usuario = usuarioDAO.buscaId(usuaruioid);

        if(usuario == null){
            Intent it_uss_edusuario = getIntent();
            Bundle bundle = it_uss_edusuario.getExtras();

            uss_nome.setText(bundle.getString(StringsNomes.getUsNome()));
            uss_dtnasc.setText(bundle.getString(StringsNomes.getUsDtnasc()));
            uss_email.setText(bundle.getString(StringsNomes.getUsEmail()));
            uss_telefone.setText(bundle.getString(StringsNomes.getUsTelefone()));
            uss_localizacao.setText(bundle.getString(StringsNomes.getUsLongitude()));
            uss_cod.setText(bundle.getString(StringsNomes.getUsCod()));
        }
        else{
            uss_nome.setText(usuario.getUs_nome());
            uss_email.setText(usuario.getUs_email());
            uss_dtnasc.setText(usuario.getUs_dtnasc());
            uss_telefone.setText(usuario.getUs_codarea() + " " + usuario.getUs_telefone());
            uss_localizacao.setText(usuario.getUs_longitude());
        }
    }

}
