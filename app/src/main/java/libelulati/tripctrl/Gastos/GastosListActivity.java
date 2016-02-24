package libelulati.tripctrl.Gastos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class GastosListActivity extends AppCompatActivity {

    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    Context context;
    int id_gastos;
    FloatingActionButton fab_ga_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_list);

        context = GastosListActivity.this;

        final Intent it_ga_gastos = getIntent();
        Bundle bundle = it_ga_gastos.getExtras();
        id_viagem = bundle.getInt(Nomes.getViId());

        fab_ga_new = (FloatingActionButton)findViewById(R.id.fab_ga_new);
        fab_ga_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it_new_gasto = new Intent(context, GastoEditActivity.class);
                Bundle bdnovo = new Bundle();
                bdnovo.putInt("novo", 1);
                bdnovo.putInt(Nomes.getID(), id_gastos);
                bdnovo.putInt(Nomes.getViId(), id_viagem);
                bdnovo.putInt(Nomes.getUsId(), id_usuario);
                bdnovo.putString(Nomes.getCaId(), null);
                bdnovo.putString(Nomes.getPaId(), null);
                bdnovo.putString(Nomes.getGaDescricao(), null);
                bdnovo.putString(Nomes.getGaData(), null);
                bdnovo.putString(Nomes.getGaValor(), null);

                it_new_gasto.putExtras(bdnovo);
                startActivityForResult(it_new_gasto, 1);
            }
        });
    }

    @Override
    public void onResume() {
        Listar();
        super.onResume();
    }

    public void Listar(){

        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_ga_lista);
        linearLayout_itens.removeAllViews();

        List<Gasto> gastos = new Gastos_DAO(context).listar(id_viagem);
        View viewItens = null;
        TextView tx_ga_descricao, tx_ga_categoria, tx_ga_valor, tx_ga_data;

        if(gastos.size() > 0){
            for(final Gasto gasto : gastos){
                id_gastos = gasto.getGa_id();
                String ga_descricao = gasto.getGa_descricao();
                String ga_valor = gasto.getGa_valor();
                final String ga_categoria = gasto.getCa_id();
                String ga_data = gasto.getGa_data();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_gastos, null);

                tx_ga_descricao = (TextView)viewItens.findViewById(R.id.tx_ga_descricao);
                tx_ga_categoria = (TextView)viewItens.findViewById(R.id.tx_ga_categoria);
                tx_ga_valor = (TextView)viewItens.findViewById(R.id.tx_ga_valor);
                tx_ga_data = (TextView)viewItens.findViewById(R.id.tx_ga_data);

                tx_ga_descricao.setText(ga_descricao);
                tx_ga_categoria.setText(ga_categoria);
                tx_ga_valor.setText(ga_valor);
                tx_ga_data.setText(ga_data);

                viewItens.setTag(id_gastos);

                viewItens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it_ga_show = new Intent(context, GastoEditActivity.class);
                        Bundle bdshow = new Bundle();

                        bdshow.putInt("novo", 2);
                        bdshow.putInt(Nomes.getID(), id_gastos);
                        bdshow.putInt(Nomes.getViId(), id_viagem);
                        bdshow.putInt(Nomes.getUsId(), id_usuario);
                        bdshow.putString(Nomes.getCaId(), gasto.getCa_id());
                        bdshow.putString(Nomes.getPaId(), gasto.getPa_id());
                        bdshow.putString(Nomes.getGaDescricao(), gasto.getGa_descricao());
                        bdshow.putString(Nomes.getGaData(), gasto.getGa_data());
                        bdshow.putString(Nomes.getGaValor(), gasto.getGa_valor());

                        it_ga_show.putExtras(bdshow);
                        startActivityForResult(it_ga_show, 1);
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

















