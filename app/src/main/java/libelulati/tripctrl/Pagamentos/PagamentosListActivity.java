package libelulati.tripctrl.Pagamentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    }


}
