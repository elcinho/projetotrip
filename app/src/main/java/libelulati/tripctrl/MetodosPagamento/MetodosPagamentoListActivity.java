package libelulati.tripctrl.MetodosPagamento;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
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

public class MetodosPagamentoListActivity extends AppCompatActivity {

    Context context;
    String id_metodopagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodospagamento_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = MetodosPagamentoListActivity.this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mp_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_mp_novo = new Intent(context, MetodosPagamentoNovoActivity.class);
                startActivity(it_mp_novo);
            }
        });
    }

    @Override
    public void onResume() {
        listarMetodosPagamento();
        super.onResume();
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

    public void listarMetodosPagamento(){
        LinearLayout linearLayoutLista = (LinearLayout) findViewById(R.id.lv_item_metodopagamento);
        linearLayoutLista.removeAllViews();

        List<MetodosPagamento> metodosPagamentos = new MetodosPagamentoDAO(this).listar();

        if (metodosPagamentos.size() > 0) {
            for (MetodosPagamento pagamento : metodosPagamentos) {
                int id = pagamento.getMp_id();
                String mp_detalhe = pagamento.getMp_detalhe();
                String mp_dtvenc = pagamento.getMp_dtvenc();
                String mp_valor = pagamento.getMp_valor();

                String dados = mp_dtvenc + " - " + context.getResources().getString(R.string.moeda) + " " + mp_valor;

                TextView tx_mp_item = new TextView(this);
                tx_mp_item.setPadding(0, 10, 0, 10);
                tx_mp_item.setText(mp_detalhe);
                tx_mp_item.setTag(Integer.toString(id));
                tx_mp_item.setTypeface(tx_mp_item.getTypeface(), Typeface.BOLD);
                tx_mp_item.setTextSize(20);
                tx_mp_item.setTextColor(getResources().getColor(R.color.colorAccent));

                TextView tx_mp_dados = new TextView(this);
                tx_mp_dados.setPadding(0,0,0,0);
                tx_mp_dados.setText(dados);

                tx_mp_item.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        context = v.getContext();
                        id_metodopagamento = v.getTag().toString();

                        final CharSequence[] opcoes = {context.getResources().getString(R.string.opcao_visualizar),context.getResources().getString(R.string.opcao_deletar) };

                        new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.opcao_titulo)).setItems(opcoes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        visualizar(Integer.parseInt(id_metodopagamento));
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        deletar(Integer.parseInt(id_metodopagamento));
                                        dialog.dismiss();
                                        break;
                                }
                            }

                        }).show();
                        return false;
                    }
                });

                linearLayoutLista.addView(tx_mp_item);
                linearLayoutLista.addView(tx_mp_dados);
            }
        } else {

            TextView criarnovo = new TextView(this);
            criarnovo.setPadding(8, 8, 8, 8);
            criarnovo.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayoutLista.addView(criarnovo);
        }
    }

    public void visualizar(int id) {

        MetodosPagamentoDAO metodosPagamentoDAO = new MetodosPagamentoDAO(context);
        MetodosPagamento metodosPagamento = metodosPagamentoDAO.buscarID(id);

        Intent mpshow = new Intent(context, MetodosPagamentoShowActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(StringsNomes.getID(), id);
        bundle.putString(StringsNomes.getMeDetalhe(), metodosPagamento.getMp_detalhe());
        bundle.putString(StringsNomes.getViId(), metodosPagamento.getVi_id());
        bundle.putString(StringsNomes.getTpId(), metodosPagamento.getTp_id());
        bundle.putString(StringsNomes.getMeValor(), metodosPagamento.getMp_valor());
        bundle.putString(StringsNomes.getMeVencimento(), metodosPagamento.getMp_dtvenc());

        mpshow.putExtras(bundle);
        startActivityForResult(mpshow, 1);
    }

    public void deletar(int id) {

        final int del_id = id;

        AlertDialog confirme;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.opcao_confirmar));
        builder.setMessage(context.getResources().getString(R.string.excluir_registro));

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                MetodosPagamentoDAO metodospagamentoDAO = new MetodosPagamentoDAO(context);
                boolean sucesso = metodospagamentoDAO.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_metodopagamento), Toast.LENGTH_LONG).show();
                    listarMetodosPagamento();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_metodopagamento), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
            }
        });

        confirme = builder.create();
        confirme.show();
    }
}
