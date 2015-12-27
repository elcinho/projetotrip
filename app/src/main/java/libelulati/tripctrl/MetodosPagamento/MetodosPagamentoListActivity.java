package libelulati.tripctrl.MetodosPagamento;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
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
    String ME_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_me_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_mp_novo = new Intent(MetodosPagamentoListActivity.this, MetodosPagamentoEditActivity.class);
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

    public void listarMetodosPagamento() {

        LinearLayout linearLayoutLista = (LinearLayout) findViewById(R.id.lv_item_metodosPagamento);
        linearLayoutLista.removeAllViews();

        List<MetodosPagamento> metodosPagamentos = new MetodosPagamentoDAO(this).listar();

        if (metodosPagamentos.size() > 0) {
            for (MetodosPagamento mp : metodosPagamentos) {

                int me_id = mp.getMe_id() ;
                int tp_id = mp.getTp_id();
                String me_detalhe = mp.getMe_detalhes();
                float me_valor = mp.getMe_valor();

                String visualizar = tp_id + " - " + me_detalhe + " / " + me_valor;

                TextView tx_mp_item = new TextView(this);
                tx_mp_item.setPadding(0, 10, 0, 10);
                tx_mp_item.setText(visualizar);
                tx_mp_item.setTag(Integer.toString(me_id));


                tx_mp_item.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        context = v.getContext();
                        ME_id = v.getTag().toString();

                        final CharSequence[] opcoes = {context.getResources().getString(R.string.opcao_visualizar), context.getResources().getString(R.string.opcao_deletar)};

                        new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.opcao_titulo)).setItems(opcoes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        visualizar(Integer.parseInt(ME_id));
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        deletar(Integer.parseInt(ME_id));
                                        dialog.dismiss();
                                        break;
                                }
                            }

                        }).show();
                        return false;
                    }
                });

                linearLayoutLista.addView(tx_mp_item);
            }
        } else {

            TextView criarnovo = new TextView(this);
            criarnovo.setPadding(8, 8, 8, 8);
            criarnovo.setText("Não foi encontrado nenhum registro.");

            linearLayoutLista.addView(criarnovo);
        }
    }

    public void visualizar(int id) {

        MetodosPagamentoDAO mpDAO = new MetodosPagamentoDAO(context);
        MetodosPagamento mp = mpDAO.buscarID(id);

        Intent mpshow = new Intent(context,MetodosPagamentoShowActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(StringsNomes.getID(), id);
        bundle.putInt(StringsNomes.getMeId(), mp.getMe_id());
        bundle.putInt(StringsNomes.getUsId(), mp.getUs_id());
        bundle.putInt(StringsNomes.getTpId(), mp.getTp_id());
        bundle.putString(StringsNomes.getMeDetalhe(), mp.getMe_detalhes());
        bundle.putInt(StringsNomes.getMeValor(), (int) mp.getMe_valor());
        bundle.putString(StringsNomes.getMeVencimento(), mp.getMe_vencimento());


        mpshow.putExtras(bundle);
        startActivityForResult(mpshow, 1);
    }

    public void deletar(int id) {

        final int del_id = id;

        AlertDialog confirme;
        MetodosPagamentoDAO mpDAO = new MetodosPagamentoDAO(context);
        MetodosPagamento mp = mpDAO.buscarID(id);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.opcao_confirmar));
        builder.setMessage(context.getResources().getString(R.string.opcao_registro) + mp.getMe_id() + context.getResources().getString(R.string.opcao_excluir));

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                MetodosPagamentoDAO mpDAO = new MetodosPagamentoDAO(context);
                MetodosPagamento mp = mpDAO.buscarID(del_id);
                boolean sucesso = mpDAO.deletar(del_id);

                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.metodopagameto) + " " + mp.getMe_id() + " " + context.getResources().getString(R.string.sucesso_deletado), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_deletar) + context.getResources().getString(R.string.metodopagameto) + " " + mp.getMe_id(), Toast.LENGTH_LONG).show();
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
