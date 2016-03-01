package libelulati.tripctrl.Configuracoes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Notificacoes.NotificacoesConfiguracaoActivity;
import libelulati.tripctrl.R;

public class ConfiguracoesListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_list);

        context = ConfiguracoesListActivity.this;

        LinearLayout linearLayout_itens = (LinearLayout) findViewById(R.id.li_co_lista);
        linearLayout_itens.removeAllViews();

        List<String> co_itens = new ArrayList<>();
        if(co_itens.size() == 0){
            co_itens.add(context.getResources().getString(R.string.co_notificacoes));
            co_itens.add(context.getResources().getString(R.string.co_perfil_usuario));
            co_itens.add(context.getResources().getString(R.string.co_categorias));
            co_itens.add(context.getResources().getString(R.string.co_tipos_pagamento));
            co_itens.add(context.getResources().getString(R.string.co_sobre));
            co_itens.add(context.getResources().getString(R.string.co_avalie));
            co_itens.add(context.getResources().getString(R.string.co_termo_uso));
            co_itens.add(context.getResources().getString(R.string.co_sair));
        }

        View viewItens = null;
        TextView tx_co_itens;

        for (int i = 0; i < co_itens.size(); i++){
            int item_id = i;
            String item = co_itens.get(i);

            LayoutInflater inflater = getLayoutInflater();
            viewItens = inflater.inflate(R.layout.view_list_configuracoes, null);

            tx_co_itens = (TextView)viewItens.findViewById(R.id.tx_co_itens);
            tx_co_itens.setText(item);

            viewItens.setTag(item_id);

            linearLayout_itens.addView(viewItens);
        }
    }

    public void Visualizar(int id){

        switch (id){
            case 0:
                Intent it_notificacoes = new Intent(context, NotificacoesConfiguracaoActivity.class);
                startActivity(it_notificacoes);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }
}
