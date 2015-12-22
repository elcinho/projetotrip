package libelulati.tripctrl.Viagens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class ViagensShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FloatingActionButton fab_editar = (FloatingActionButton) findViewById(R.id.fab_vis_editar);
        final EditText vis_nome = (EditText) findViewById(R.id.ed_vis_nome);
        final EditText vis_localizacao = (EditText) findViewById(R.id.ed_vis_localizacao);
        final EditText vis_dtini = (EditText) findViewById(R.id.ed_vis_dtinicio);
        final EditText vis_dtfim = (EditText) findViewById(R.id.ed_vis_dtfim);
        final EditText vis_tipotransp = (EditText) findViewById(R.id.ed_vis_tipotransporte);
        final EditText vis_transporte = (EditText) findViewById(R.id.ed_vis_transporte);
        final EditText vis_tipohosp = (EditText) findViewById(R.id.ed_vis_tipohospedagem);
        final EditText vis_hospedagem = (EditText) findViewById(R.id.ed_vis_hospedagem);
        final EditText vis_valortot = (EditText) findViewById(R.id.ed_vis_valortotal);
        final Button vis_salvar = (Button) findViewById(R.id.bt_vis_salvar);

        Intent it_vis_show = getIntent();
        Bundle bundle = it_vis_show.getExtras();

        final int id = bundle.getInt(StringsNomes.getID());
        vis_nome.setText(bundle.getString(StringsNomes.getViNome()));
        vis_localizacao.setText(bundle.getString(StringsNomes.getViLocal()));
        vis_dtini.setText(bundle.getString(StringsNomes.getViDtini()));
        vis_dtfim.setText(bundle.getString(StringsNomes.getViDtfim()));
        vis_tipotransp.setText(bundle.getString(StringsNomes.getTrId()));
        vis_transporte.setText(bundle.getString(StringsNomes.getViTransporte()));
        vis_tipohosp.setText(bundle.getString(StringsNomes.getHoId()));
        vis_hospedagem.setText(bundle.getString(StringsNomes.getViHospedagem()));
        vis_valortot.setText(bundle.getString(StringsNomes.getViValortotal()));

        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vis_nome.setEnabled(true);
                vis_localizacao.setEnabled(true);
                vis_dtini.setEnabled(true);
                vis_dtfim.setEnabled(true);
                vis_tipotransp.setEnabled(true);
                vis_transporte.setEnabled(true);
                vis_tipohosp.setEnabled(true);
                vis_hospedagem.setEnabled(true);
                vis_valortot.setEnabled(true);

                vis_nome.requestFocus();

                vis_salvar.setVisibility(View.VISIBLE);
                fab_editar.setVisibility(View.INVISIBLE);
            }
        });

        vis_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int usuario_id = InicioActivity.getId_uslogado();

                final Context context = v.getContext();
                Viagens viagens = new Viagens();

                viagens.setUs_id(usuario_id);
                viagens.setVi_nome(vis_nome.getText().toString());
                viagens.setVi_local(vis_localizacao.getText().toString());
                viagens.setVi_dtini(vis_dtini.getText().toString());
                viagens.setVi_dtfim(vis_dtfim.getText().toString());
                viagens.setTr_id(vis_tipotransp.getText().toString());
                viagens.setVi_transporte(vis_transporte.getText().toString());
                viagens.setHo_id(vis_tipohosp.getText().toString());
                viagens.setVi_hospedagem(vis_hospedagem.getText().toString());
                viagens.setVi_valortotal(vis_valortot.getText().toString());

                boolean sucesso = new ViagensDAO(context).atualizar(viagens, id);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getVIAGEM() + " " + viagens.getVi_nome() + " " + MensagensUsuario.getEditado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_editar() + MensagensUsuario.getVIAGEM() + " " + viagens.getVi_nome(), Toast.LENGTH_LONG).show();
                }

                finish();
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
