package libelulati.tripctrl.Pagamentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PagamentosListActivity extends AppCompatActivity {

    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    Context context;
    int id_pagamento;
    FloatingActionButton fab_pa_new;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos_list);

        context = PagamentosListActivity.this;

        final Intent it_pa_pagamentos = getIntent();
        Bundle bundle = it_pa_pagamentos.getExtras();
        id_viagem = bundle.getInt(Nomes.getViId());

        fab_pa_new = (FloatingActionButton)findViewById(R.id.fab_pa_new);
        fab_pa_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_new_pagamento = new Intent(context, PagamentosEditActivity.class);
                Bundle bdnovo = new Bundle();
                bdnovo.putInt("novo", 1);
                bdnovo.putInt(Nomes.getID(), id_pagamento);
                bdnovo.putInt(Nomes.getViId(), id_viagem);
                bdnovo.putInt(Nomes.getUsId(), id_usuario);
                bdnovo.putString(Nomes.getTpId(), null);
                bdnovo.putString(Nomes.getPaDescricao(), null);
                bdnovo.putString(Nomes.getPaValor(), null);
                bdnovo.putString(Nomes.getPaVencimento(), null);

                it_new_pagamento.putExtras(bdnovo);
                startActivityForResult(it_new_pagamento, 1);
            }
        });
    }

    @Override
    public void onResume() {
        Listar();
        super.onResume();
    }

    public void Listar(){

        LinearLayout linearLayout_itens = (LinearLayout) findViewById(R.id.li_pa_lista);
        linearLayout_itens.removeAllViews();

        List<Pagamento> pagamentos = new Pagamentos_DAO(context).listar(id_viagem);
        View viewItens = null;
        TextView tx_pa_descricao, tx_pa_tipopagamento, tx_pa_valor, tx_pa_vencimento;

        if(pagamentos.size() > 0){
            for (final Pagamento pagamento : pagamentos){
                id_pagamento = pagamento.getPa_id();
                String pa_descricao = pagamento.getPa_descricao();
                String pa_tipopagamento = pagamento.getTp_id();
                String pa_valor = pagamento.getPa_valor();
                String pa_vencimento = pagamento.getPa_dtvenc();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_pagamentos, null);

                tx_pa_descricao = (TextView)viewItens.findViewById(R.id.tx_pa_descricao);
                tx_pa_tipopagamento = (TextView)viewItens.findViewById(R.id.tx_pa_tipopagamento);
                tx_pa_valor = (TextView)viewItens.findViewById(R.id.tx_pa_valor);
                tx_pa_vencimento = (TextView)viewItens.findViewById(R.id.tx_pa_data);

                tx_pa_descricao.setText(pa_descricao);
                tx_pa_tipopagamento.setText(pa_tipopagamento);
                tx_pa_valor.setText(pa_valor);
                tx_pa_vencimento.setText(pa_vencimento);

                viewItens.setTag(id_pagamento);

                viewItens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it_pa_show = new Intent(context, PagamentosEditActivity.class);
                        Bundle bdshow = new Bundle();

                        bdshow.putInt("novo", 2);
                        bdshow.putInt(Nomes.getID(), id_pagamento);
                        bdshow.putInt(Nomes.getUsId(), id_usuario);
                        bdshow.putInt(Nomes.getViId(), id_viagem);
                        bdshow.putString(Nomes.getTpId(), pagamento.getTp_id());
                        bdshow.putString(Nomes.getPaDescricao(), pagamento.getPa_descricao());
                        bdshow.putString(Nomes.getPaValor(), pagamento.getPa_valor());
                        bdshow.putString(Nomes.getPaVencimento(), pagamento.getPa_dtvenc());

                        it_pa_show.putExtras(bdshow);
                        startActivityForResult(it_pa_show, 1);
                    }
                });

                linearLayout_itens.addView(viewItens);

            }
        }
        else{
            TextView nenhumregistro = new TextView(context);
            nenhumregistro.setPadding(8,8,8,8);
            nenhumregistro.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayout_itens.addView(nenhumregistro);
        }
    }
}
