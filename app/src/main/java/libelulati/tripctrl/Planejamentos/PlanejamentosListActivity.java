package libelulati.tripctrl.Planejamentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PlanejamentosListActivity extends AppCompatActivity {

    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    Context context;
    FloatingActionButton fab_pl_new;
    double pl_total = 0, dc_valor = 0;
    TextView tx_pl_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamentos_list);

        context = PlanejamentosListActivity.this;

        final Intent it_pl_planejamnto = getIntent();
        Bundle bundle = it_pl_planejamnto.getExtras();
        id_viagem = bundle.getInt(Nomes.getViId());

        tx_pl_total = (TextView)findViewById(R.id.tx_pl_total);
        fab_pl_new = (FloatingActionButton)findViewById(R.id.fab_pl_new);
        fab_pl_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibirPlanejamentoNew();
            }
        });
    }

    @Override
    public void onResume() {
        Listar();
        super.onResume();
    }

    public void Listar(){
        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_pl_lista);
        linearLayout_itens.removeAllViews();

        List<Planejamento> planejamentos = new Planejamento_DAO(context).listar(id_viagem);
        View viewitens = null;
        TextView tx_pl_categoria, tx_pl_valor;

        pl_total = 0;

        if(planejamentos.size() > 0){
            for(final Planejamento planejamento : planejamentos){
                int tagID = planejamento.getPl_id();
                final String pl_categoria = planejamento.getCa_id();
                final String pl_valor = planejamento.getPl_valor();

                LayoutInflater inflater = getLayoutInflater();
                viewitens = inflater.inflate(R.layout.view_list_planejamentos, null);

                tx_pl_categoria = (TextView)viewitens.findViewById(R.id.tx_pl_categoria);
                tx_pl_valor = (TextView)viewitens.findViewById(R.id.tx_pl_valor);

                tx_pl_categoria.setText(pl_categoria);
                tx_pl_valor.setText(pl_valor);

                viewitens.setTag(tagID);
                viewitens.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String  id = v.getTag().toString();
                        final CharSequence[] opcoes = {context.getResources().getString(R.string.opcao_visualizar), context.getResources().getString(R.string.opcao_deletar)};

                        new AlertDialog.Builder(context).setItems(opcoes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        ExibirPlanejamentoEdit(pl_categoria, pl_valor, Integer.parseInt(id));
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        Deletar(Integer.parseInt(id));
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        }).show();
                    }
                });

                linearLayout_itens.addView(viewitens);

                dc_valor = Double.parseDouble(planejamento.getPl_valor());
                pl_total += dc_valor;

                tx_pl_total.setText(context.getResources().getString(R.string.moeda) + " " + format(pl_total));
            }
        }
        else{
            TextView nenhumregistro = new TextView(context);
            nenhumregistro.setPadding(8,8,8,8);
            nenhumregistro.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayout_itens.addView(nenhumregistro);
        }
    }

    public void ExibirPlanejamentoNew(){
        DialogFragment planejamentonew = new Planejamento_new(id_viagem);
        planejamentonew.show(getSupportFragmentManager(), "planejamentonew");
    }

    public void ExibirPlanejamentoEdit(String categoria, String valor, int id_pl){
        DialogFragment planejamentoedit = new Planejamento_edit(categoria, valor, id_pl, id_viagem);
        planejamentoedit.show(getSupportFragmentManager(), "planejamentoedit");
    }

    public void Deletar(int id){
        final int del_id = id;

        AlertDialog confirme;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.opcao_confirmar));
        builder.setMessage(context.getResources().getString(R.string.excluir_registro));

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Planejamento_DAO planejamento_dao = new Planejamento_DAO(context);
                boolean sucesso = planejamento_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_gasto), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_gasto), Toast.LENGTH_LONG).show();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        confirme = builder.create();
        confirme.show();
    }

    public static String format(double x){
        return String.format("%.2f", x);
    }

    public boolean IsValido(){
        return true;
    }

}
