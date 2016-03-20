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

import libelulati.tripctrl.Aplicativo.SobreActivity;
import libelulati.tripctrl.Aplicativo.TermoUsoActivity;
import libelulati.tripctrl.Categorias.CategoriaListActivity;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Notificacoes.NotificacoesConfiguracaoActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.TipoPagamento.TiposPagamentoListActivity;
import libelulati.tripctrl.Usuarios.Usuario;
import libelulati.tripctrl.Usuarios.UsuarioEditActivity;
import libelulati.tripctrl.Usuarios.Usuario_DAO;

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
            co_itens.add(context.getResources().getString(R.string.co_categorias));
            co_itens.add(context.getResources().getString(R.string.co_tipos_pagamento));
            co_itens.add(context.getResources().getString(R.string.co_sobre));
            co_itens.add(context.getResources().getString(R.string.co_termo_uso));
            co_itens.add(context.getResources().getString(R.string.co_sair));
        }

        View viewItens = null;
        TextView tx_co_itens;

        for (int i = 0; i < co_itens.size(); i++){
            final int item_id = i + 1;
            String item = co_itens.get(i);

            LayoutInflater inflater = getLayoutInflater();
            viewItens = inflater.inflate(R.layout.view_list_configuracoes, null);

            tx_co_itens = (TextView)viewItens.findViewById(R.id.tx_co_itens);
            tx_co_itens.setText(item);

            viewItens.setTag(item_id);

            viewItens.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Visualizar(item_id);
                }
            });

            linearLayout_itens.addView(viewItens);
        }
    }

    public void Visualizar(int id){

        switch (id){
            case 1:
                Intent it_notificacoes = new Intent(context, NotificacoesConfiguracaoActivity.class);
                startActivity(it_notificacoes);
                break;
            case 2:
                Intent it_categorias = new Intent(context, CategoriaListActivity.class);
                startActivity(it_categorias);
                break;
            case 3:
                Intent it_tipopagamento = new Intent(context, TiposPagamentoListActivity.class);
                startActivity(it_tipopagamento);
                break;
            case 4:
                Intent it_sobre = new Intent(context, SobreActivity.class);
                startActivity(it_sobre);
                break;
            case 5:
                Intent it_termo = new Intent(context, TermoUsoActivity.class);
                startActivity(it_termo);
                break;
            case 6:
                Intent it_sair = new Intent(Intent.ACTION_MAIN);
                it_sair.addCategory(Intent.CATEGORY_HOME);
                it_sair.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it_sair);
                break;
        }
    }

    public void Usuarios(int id){
        Usuario_DAO usuario_dao = new Usuario_DAO(context);
        Usuario usuario_id = usuario_dao.buscaId(id);

        Intent it_us_show = new Intent(context, UsuarioEditActivity.class);
        Bundle usshow = new Bundle();

        usshow.putInt("novo", 2);
        usshow.putInt(Nomes.getID(), usuario_id.getUs_id());
        usshow.putInt(Nomes.getUsSemsenha(), usuario_id.getUs_semsenha());
        usshow.putString(Nomes.getUsNome(), usuario_id.getUs_nome());
        usshow.putString(Nomes.getUsEmail(), usuario_id.getUs_email());
        usshow.putString(Nomes.getUsDtnasc(), usuario_id.getUs_dtnasc());

        it_us_show.putExtras(usshow);
        startActivityForResult(it_us_show, 1);
    }
}