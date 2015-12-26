package libelulati.tripctrl.Inicio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Categoria.CategoriaListActivity;
import libelulati.tripctrl.Gastos.GastosListActivity;
import libelulati.tripctrl.MetodosPagamento.MetodosPagamentoListActivity;
import libelulati.tripctrl.Planejamentos.PlanejamentosListActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Relatorios.RelatoriosActivity;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;
import libelulati.tripctrl.Usuario.UsuarioShowActivity;
import libelulati.tripctrl.Viagens.ViagensListActivity;

public class InicioActivity extends AppCompatActivity {

    private static int id_uslogado = 1;  //mudar para receber o id do usuario logado.

    public static int getId_uslogado() {
        return id_uslogado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent it_inicio = getIntent();
        Bundle bundle = it_inicio.getExtras();

        id_uslogado = bundle.getInt(StringsNomes.getID());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.mn_ini_categoria:
                Intent it_ini_categoria = new Intent(InicioActivity.this, CategoriaListActivity.class);
                startActivity(it_ini_categoria);
                break;
            case R.id.mn_ini_metodopagamento:
                Intent it_ini_metodopagamento = new Intent(InicioActivity.this, MetodosPagamentoListActivity.class);
                startActivity(it_ini_metodopagamento);
                break;
            case R.id.mn_ini_planejamento:
                Intent it_ini_planejamento = new Intent(InicioActivity.this, PlanejamentosListActivity.class);
                startActivity(it_ini_planejamento);
                break;
            case R.id.mn_ini_viagens:
                Intent it_ini_viagens = new Intent(InicioActivity.this, ViagensListActivity.class);
                startActivity(it_ini_viagens);
                break;
            case R.id.mn_ini_gastos:
                Intent it_ini_gastos = new Intent(InicioActivity.this, GastosListActivity.class);
                startActivity(it_ini_gastos);
                break;
            case R.id.mn_ini_atualizar:
                break;
            case R.id.mn_ini_buscar:
                break;
            case R.id.mn_ini_configuracao:
                break;
            case R.id.mn_ini_editarusuario:

                Context contexto = getApplicationContext();

                UsuarioDAO usuarioDAO = new UsuarioDAO(contexto);
                Usuario usuario = usuarioDAO.buscaId(getId_uslogado());

                Intent it_ini_edusuario = new Intent(InicioActivity.this, UsuarioShowActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString(StringsNomes.getUsNome(), usuario.getUs_nome());
                bundle.putString(StringsNomes.getUsDtnasc(), usuario.getUs_dtnasc());
                bundle.putString(StringsNomes.getUsEmail(), usuario.getUs_email());
                bundle.putString(StringsNomes.getUsTelefone(), usuario.getUs_codarea() + " " + usuario.getUs_telefone());
                bundle.putString(StringsNomes.getUsLongitude(), usuario.getUs_longitude());
                bundle.putString(StringsNomes.getUsCod(), usuario.getUs_cod());

                it_ini_edusuario.putExtras(bundle);

                startActivityForResult(it_ini_edusuario, 1);
                break;
            case R.id.mn_ini_relatorios:
                Intent it_ini_relatorio = new Intent(InicioActivity.this, RelatoriosActivity.class);
                startActivity(it_ini_relatorio);
                break;
        }
        return true;
    }

}
