package libelulati.tripctrl.Planejamentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Pagamentos.PagamentosEditActivity;
import libelulati.tripctrl.R;

public class PlanejamentosListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem, id_planejamento;
    Context context;
    FloatingActionButton fab_pl_new;
    double pl_total = 0, dc_total = 0;
    TextView tx_pl_total;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamentos_list);

        context = PlanejamentosListActivity.this;

        final Intent it_pl_planejamento = getIntent();
        Bundle bundle = it_pl_planejamento.getExtras();
        id_viagem = bundle.getInt(Nomes.getViId());

        tx_pl_total = (TextView)findViewById(R.id.tx_pl_total);
        fab_pl_new = (FloatingActionButton)findViewById(R.id.fab_pl_new);
        fab_pl_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_new_planejamento = new Intent(context, PlanejamentoEditActivity.class);
                Bundle bdnovo = new Bundle();
                bdnovo.putInt("novo", 1);
                bdnovo.putInt(Nomes.getID(), id_planejamento);
                bdnovo.putInt(Nomes.getViId(), id_viagem);
                bdnovo.putInt(Nomes.getUsId(), id_usuario);
                bdnovo.putString(Nomes.getCaId(), null);
                bdnovo.putString(Nomes.getPlValorcat(), null);

                it_new_planejamento.putExtras(bdnovo);
                startActivityForResult(it_new_planejamento, 1);
            }
        });
    }

    @Override
    public void onResume(){
        Listar();
        super.onResume();
    }

    public void Listar(){
        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_pl_lista);
        linearLayout_itens.removeAllViews();

        List<Planejamento> planejamentos = new Planejamento_DAO(context).listar(id_viagem);
        View viewItens = null;
        TextView tx_pl_categoria, tx_pl_valor;

        pl_total = 0;

        if(planejamentos.size() > 0){
            for(final Planejamento planejamento : planejamentos){
                final int id_pl = planejamento.getPl_id();
                String pl_categoria = planejamento.getCa_id();
                String pl_valor = planejamento.getPl_valor();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_planejamentos, null);

                tx_pl_categoria = (TextView)viewItens.findViewById(R.id.tx_pl_categoria);
                tx_pl_valor = (TextView)viewItens.findViewById(R.id.tx_pl_valor);

                tx_pl_categoria.setText(pl_categoria);
                tx_pl_valor.setText(pl_valor);

                viewItens.setTag(id_pl);

                viewItens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it_pl_show = new Intent(context, PagamentosEditActivity.class);
                        Bundle bdshow = new Bundle();

                        bdshow.putInt("novo", 2);
                        bdshow.putInt(Nomes.getID(), id_pl);
                        bdshow.putInt(Nomes.getUsId(), id_usuario);
                        bdshow.putInt(Nomes.getViId(), id_viagem);
                        bdshow.putString(Nomes.getCaId(), planejamento.getCa_id());
                        bdshow.putString(Nomes.getPlValorcat(), planejamento.getPl_valor());

                        it_pl_show.putExtras(bdshow);
                        startActivityForResult(it_pl_show, 1);
                    }
                });

                linearLayout_itens.addView(viewItens);

                dc_total = Double.parseDouble(planejamento.getPl_valor());
                pl_total += dc_total;

                tx_pl_total.setText(context.getResources().getString(R.string.total) + " " + context.getResources().getString(R.string.moeda) + " " + format(pl_total));
            }
        }
        else{
            TextView nenhumregistro = new TextView(context);
            nenhumregistro.setPadding(8,8,8,8);
            nenhumregistro.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayout_itens.addView(nenhumregistro);
        }
    }

    public static String format(double x){
        return String.format("%.2f", x);
    }
}
