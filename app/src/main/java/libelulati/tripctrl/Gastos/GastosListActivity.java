package libelulati.tripctrl.Gastos;

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
import libelulati.tripctrl.Inicio.Totais;
import libelulati.tripctrl.Inicio.Totais_DAO;
import libelulati.tripctrl.R;

public class GastosListActivity extends AppCompatActivity {

    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    Context context;
    int id_gastos;
    FloatingActionButton fab_ga_new;
    double ga_total = 0, dc_valor = 0;
    TextView tx_ga_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_list);

        context = GastosListActivity.this;

        final Intent it_ga_gastos = getIntent();
        Bundle bundle = it_ga_gastos.getExtras();
        id_viagem = bundle.getInt(Nomes.getViId());

        tx_ga_total = (TextView)findViewById(R.id.tx_ga_total);
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

    public double Listar(){

        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_ga_lista);
        linearLayout_itens.removeAllViews();

        List<Gasto> gastos = new Gastos_DAO(context).listar(id_viagem);
        View viewItens = null;
        TextView tx_ga_descricao, tx_ga_categoria, tx_ga_valor, tx_ga_data;

        ga_total = 0;

        if(gastos.size() > 0){
            for(final Gasto gasto : gastos){
                final int id_ga = gasto.getGa_id();
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

                viewItens.setTag(id_ga);

                viewItens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Visualizar(id_ga);
                    }
                });

                linearLayout_itens.addView(viewItens);

                dc_valor = Double.parseDouble(gasto.getGa_valor());
                ga_total += dc_valor;

                tx_ga_total.setText(context.getResources().getString(R.string.total) + " " + context.getResources().getString(R.string.moeda) + " " + format(ga_total));
                AtualizarTotais();
            }
        }
        else{
            TextView nenhumregistro = new TextView(context);
            nenhumregistro.setPadding(8,8,8,8);
            nenhumregistro.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayout_itens.addView(nenhumregistro);
        }
        return 0;
    }

    public void AtualizarTotais(){
        Totais totalgasto = new Totais_DAO(context).buscarNome("gasto");
        if(totalgasto == null){
            Totais_DAO totais_dao = new Totais_DAO(context);
            Totais totais = new Totais();
            totais.setUs_id(id_usuario);
            totais.setVi_id(id_viagem);
            totais.setTo_nome("gasto");
            totais.setTo_total(String.valueOf(ga_total));
            totais.setTo_gasto(null);
            totais.setTo_planejamento(null);
            totais_dao.criar(totais);
        }
        else{
            Totais_DAO totais_dao = new Totais_DAO(context);
            totalgasto.setTo_total(String.valueOf(ga_total));
            totais_dao.atualizar(totalgasto, "gasto");
        }
    }


    public void Visualizar(int id){
        Gastos_DAO gastos_dao = new Gastos_DAO(context);
        Gasto gasto_id = gastos_dao.buscarID(id);

        Intent it_ga_show = new Intent(context, GastoEditActivity.class);
        Bundle bdshow = new Bundle();

        bdshow.putInt("novo", 2);
        bdshow.putInt(Nomes.getID(), gasto_id.getGa_id());
        bdshow.putInt(Nomes.getViId(), gasto_id.getVi_id());
        bdshow.putInt(Nomes.getUsId(), gasto_id.getUs_id());
        bdshow.putString(Nomes.getCaId(), gasto_id.getCa_id());
        bdshow.putString(Nomes.getPaId(), gasto_id.getPa_id());
        bdshow.putString(Nomes.getGaDescricao(), gasto_id.getGa_descricao());
        bdshow.putString(Nomes.getGaData(), gasto_id.getGa_data());
        bdshow.putString(Nomes.getGaValor(), gasto_id.getGa_valor());

        it_ga_show.putExtras(bdshow);
        startActivityForResult(it_ga_show, 1);
    }

    public double getGa_valor_total() {
        return ga_total;
    }



    public static String format(double x){
        return String.format("%.2f", x);
    }
}

















