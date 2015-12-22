package libelulati.tripctrl.Viagens;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;
import libelulati.tripctrl.Strings.StringsSistema;


public class ViagensListActivity extends AppCompatActivity {

    Context context;
    String id_viagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_vi_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_vi_novo = new Intent(ViagensListActivity.this, ViagensNovoActivity.class);
                startActivity(it_vi_novo);
            }
        });
    }

    @Override
    public void onResume() {
        listarViagens();
        super.onResume();
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

    public void listarViagens() {

        LinearLayout linearLayoutLista = (LinearLayout) findViewById(R.id.lv_item_viagens);
        linearLayoutLista.removeAllViews();

        List<Viagens> viagens = new ViagensDAO(this).listar();

        if (viagens.size() > 0) {
            for (Viagens viag : viagens) {
                int id = viag.getVi_id();
                String vi_nome = viag.getVi_nome();
                String vi_dtini = viag.getVi_dtini();
                String vi_dtfim = viag.getVi_dtfim();

                String visualizar = vi_nome + " - " + vi_dtini + " / " + vi_dtfim;

                TextView tx_vi_item = new TextView(this);
                tx_vi_item.setPadding(0, 10, 0, 10);
                tx_vi_item.setText(visualizar);
                tx_vi_item.setTag(Integer.toString(id));

                tx_vi_item.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        context = v.getContext();
                        id_viagem = v.getTag().toString();

                        final CharSequence[] opcoes = {StringsSistema.getOPCAOVISUALIZAR(), StringsSistema.getOPCAODELETAR()};

                        new AlertDialog.Builder(context).setTitle(StringsSistema.getOPCAOTITULO()).setItems(opcoes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        visualizar(Integer.parseInt(id_viagem));
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        deletar(Integer.parseInt(id_viagem));
                                        dialog.dismiss();
                                        break;
                                }
                            }

                        }).show();
                        return false;
                    }
                });

                linearLayoutLista.addView(tx_vi_item);
            }
        } else {

            TextView criarnovo = new TextView(this);
            criarnovo.setPadding(8, 8, 8, 8);
            criarnovo.setText("Não foi encontrado nenhum registro.");

            linearLayoutLista.addView(criarnovo);
        }
    }

    public void visualizar(int id) {

        ViagensDAO viagensDAO = new ViagensDAO(context);
        Viagens viagens = viagensDAO.buscarID(id);

        Intent vishow = new Intent(context, ViagensShowActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(StringsNomes.getID(), id);
        bundle.putString(StringsNomes.getViNome(), viagens.getVi_nome());
        bundle.putString(StringsNomes.getViLocal(), viagens.getVi_local());
        bundle.putString(StringsNomes.getViDtini(), viagens.getVi_dtini());
        bundle.putString(StringsNomes.getViDtfim(), viagens.getVi_dtfim());
        bundle.putString(StringsNomes.getTrId(), viagens.getTr_id());
        bundle.putString(StringsNomes.getViTransporte(), viagens.getVi_transporte());
        bundle.putString(StringsNomes.getHoId(), viagens.getHo_id());
        bundle.putString(StringsNomes.getViHospedagem(), viagens.getVi_hospedagem());
        bundle.putString(StringsNomes.getViValortotal(), viagens.getVi_valortotal());

        vishow.putExtras(bundle);
        startActivityForResult(vishow, 1);
    }

    public void deletar(int id) {

        final int del_id = id;

        AlertDialog confirme;
        ViagensDAO viagensDAO = new ViagensDAO(context);
        Viagens viagens = viagensDAO.buscarID(id);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(StringsSistema.getCONFIRMAR());
        builder.setMessage(StringsSistema.getREGISTRO() + viagens.getVi_nome() + StringsSistema.getEXCLUSAO());

        builder.setPositiveButton(StringsSistema.getOK(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                ViagensDAO viagensDAO = new ViagensDAO(context);
                Viagens viagens = viagensDAO.buscarID(del_id);
                boolean sucesso = viagensDAO.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getVIAGEM() + " " + viagens.getVi_nome() + " " + MensagensUsuario.getDeletado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_deletar() + MensagensUsuario.getVIAGEM() + " " + viagens.getVi_nome(), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(StringsSistema.getCANCELAR(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
            }
        });

        confirme = builder.create();
        confirme.show();
    }
}
