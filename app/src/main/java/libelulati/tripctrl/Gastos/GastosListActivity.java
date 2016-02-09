package libelulati.tripctrl.Gastos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
    String id_gastos;
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
                bdnovo.putInt("isnew", 1);

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

        if(gastos.size() > 0){
            for(Gasto gasto : gastos){
                int ga_id = gasto.getGa_id();
                String ga_descricao = gasto.getGa_descricao();
                String ga_valor = gasto.getGa_valor();
                String ga_categoria = gasto.getCa_id();
                String ga_data = gasto.getGa_data();

                RelativeLayout relativeLayout_itens = (RelativeLayout)findViewById(R.id.re_ga_itens);
                relativeLayout_itens.setTag(ga_id);

                TextView tx_ga_descricao = (TextView)findViewById(R.id.tx_ga_descricao);
                TextView tx_ga_categoria = (TextView)findViewById(R.id.tx_ga_categoria);
                TextView tx_ga_valor = (TextView)findViewById(R.id.tx_ga_valor);
                TextView tx_ga_data = (TextView)findViewById(R.id.tx_ga_data);

                tx_ga_descricao.setText(ga_descricao);
                tx_ga_valor.setText(ga_valor);
                tx_ga_categoria.setText(ga_categoria);
                tx_ga_data.setText(ga_data);


                linearLayout_itens.addView(relativeLayout_itens);
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

















