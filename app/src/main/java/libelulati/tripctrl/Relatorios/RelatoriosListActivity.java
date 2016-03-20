package libelulati.tripctrl.Relatorios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.R;
import libelulati.tripctrl.RelatorioActivity;

public class RelatoriosListActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios_list);

        context = RelatoriosListActivity.this;

        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_re_lista);
        linearLayout_itens.removeAllViews();

        List<String> re_itens = new ArrayList<>();
        if(re_itens.size() == 0){
            re_itens.add(context.getResources().getString(R.string.re_gastoxplanejado));
            re_itens.add(context.getResources().getString(R.string.re_gastoxplanejado_categoria));
            re_itens.add(context.getResources().getString(R.string.re_planejado_categoria));
            re_itens.add(context.getResources().getString(R.string.re_gasto_categoria));
        }

        View viewItens = null;
        TextView tx_re_itens;

        for(int i = 0; i < re_itens.size(); i++){
            final int item_id = i + 1;
            String item = re_itens.get(i);

            LayoutInflater inflater = getLayoutInflater();
            viewItens = inflater.inflate(R.layout.view_list_relatorios, null);

            tx_re_itens = (TextView)viewItens.findViewById(R.id.tx_re_itens);
            tx_re_itens.setText(item);
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
                Intent it_rel01 = new Intent(context, RelatorioActivity.class);
                Bundle bd_rel01 = new Bundle();
                bd_rel01.putInt("relatorio", 1);
                it_rel01.putExtras(bd_rel01);
                startActivityForResult(it_rel01, 1);
                break;
            case 2:
                Intent it_rel02 = new Intent(context, RelatorioActivity.class);
                Bundle bd_rel02 = new Bundle();
                bd_rel02.putInt("relatorio", 2);
                it_rel02.putExtras(bd_rel02);
                startActivityForResult(it_rel02, 1);
                break;
            case 3:
                Intent it_rel03 = new Intent(context, RelatorioActivity.class);
                Bundle bd_rel03 = new Bundle();
                bd_rel03.putInt("relatorio", 3);
                it_rel03.putExtras(bd_rel03);
                startActivityForResult(it_rel03, 1);
                break;
            case 4:
                Intent it_rel04 = new Intent(context, RelatorioActivity.class);
                Bundle bd_rel04 = new Bundle();
                bd_rel04.putInt("relatorio", 4);
                it_rel04.putExtras(bd_rel04);
                startActivityForResult(it_rel04, 1);
                break;
        }
    }
}
