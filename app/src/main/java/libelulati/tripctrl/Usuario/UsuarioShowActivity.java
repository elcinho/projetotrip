package libelulati.tripctrl.Usuario;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_us_editar);

        final TextView uss_nome = (TextView) findViewById(R.id.tx_uss_nome);
        final TextView uss_dtnasc = (TextView) findViewById(R.id.tx_uss_dtnasc);
        final TextView uss_email = (TextView) findViewById(R.id.tx_uss_email);
        final TextView uss_telefone = (TextView) findViewById(R.id.tx_uss_telefone);
        final TextView uss_localizacao = (TextView) findViewById(R.id.tx_uss_localizacao);
        final TextView uss_cod = (TextView) findViewById(R.id.tx_uss_codusuario);

        Intent it_uss_edusuario = getIntent();
        Bundle bundle = it_uss_edusuario.getExtras();

        uss_nome.setText(bundle.getString(StringsNomes.getUsNome()));
        uss_dtnasc.setText(bundle.getString(StringsNomes.getUsDtnasc()));
        uss_email.setText(bundle.getString(StringsNomes.getUsEmail()));
        uss_telefone.setText(bundle.getString(StringsNomes.getUsTelefone()));
        uss_localizacao.setText(bundle.getString(StringsNomes.getUsLongitude()));
        uss_cod.setText(bundle.getString(StringsNomes.getUsCod()));

        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int usuaruioid = InicioActivity.getId_uslogado();
                Context contexto = view.getContext();

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
        if (id == R.id.mn_ac_voltar) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
