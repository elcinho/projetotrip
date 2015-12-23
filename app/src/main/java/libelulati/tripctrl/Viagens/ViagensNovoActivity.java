package libelulati.tripctrl.Viagens;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class ViagensNovoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_novo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int usuario = InicioActivity.getId_uslogado();

        final EditText vi_nome = (EditText) findViewById(R.id.ed_vin_nome);
        final EditText vi_localizacao = (EditText) findViewById(R.id.ed_vin_localizacao);
        final EditText vi_dtinicio = (EditText) findViewById(R.id.ed_vin_inicio);
        final EditText vi_dtfim = (EditText) findViewById(R.id.ed_vin_fim);
        final EditText vi_tipotransporte = (EditText) findViewById(R.id.ed_vin_tipotransporte);
        final EditText vi_transporte = (EditText) findViewById(R.id.ed_vin_transporte);
        final EditText vi_tipohospedagem = (EditText) findViewById(R.id.ed_vin_tipohospedagem);
        final EditText vi_hospedagem = (EditText) findViewById(R.id.ed_vin_hospedagem);
        final EditText vi_valortotal = (EditText) findViewById(R.id.ed_vin_valortotal);
        final Button vi_salvar = (Button) findViewById(R.id.bt_vin_salvar);

        vi_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                Viagens viagens = new Viagens();

                viagens.setUs_id(usuario);
                viagens.setVi_nome(vi_nome.getText().toString());
                viagens.setVi_local(vi_localizacao.getText().toString());
                viagens.setVi_dtini(vi_dtinicio.getText().toString());
                viagens.setVi_dtfim(vi_dtfim.getText().toString());
                viagens.setTr_id(vi_tipotransporte.getText().toString());//mudar para o ID do SPINNER
                viagens.setVi_transporte(vi_transporte.getText().toString());
                viagens.setHo_id(vi_tipohospedagem.getText().toString());//mudar para o ID do SPINNER
                viagens.setVi_hospedagem(vi_hospedagem.getText().toString());
                viagens.setVi_valortotal(vi_valortotal.getText().toString());

                boolean sucesso = new ViagensDAO(context).criar(viagens);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getVIAGEM() + MensagensUsuario.getCriado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_criar() + MensagensUsuario.getVIAGEM(), Toast.LENGTH_LONG).show();
                }

                finish();
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

}
