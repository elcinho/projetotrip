package libelulati.tripctrl.Inicio;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagem_New;
import libelulati.tripctrl.Viagens.Viagens_Dao;

public class InicioActivity extends AppCompatActivity {
    static int id_usuario = 0;
    List<Viagem> viagens;
    Viagem viagem;
    Button bt_ini_addviagem;
    TextView tx_ini_dataviagem, tx_ini_valorviagem;
    Context context;
    String titulo;
    int posicao;
    FloatingActionButton fab_ini_add, fab_ini_close, fab_ini_gastos, fab_ini_planejamento, fab_ini_pagamento, fab_ini_configuracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        id_usuario = 1; // BUSCAR ID DO USUARIO LOGADO

        context = InicioActivity.this;

        bt_ini_addviagem = (Button)findViewById(R.id.bt_ini_addviagem);
        tx_ini_dataviagem = (TextView)findViewById(R.id.tx_ini_dataviagem);
        tx_ini_valorviagem = (TextView)findViewById(R.id.tx_ini_valorviagem);
        fab_ini_add = (FloatingActionButton) findViewById(R.id.fab_ini_add);
        fab_ini_close = (FloatingActionButton) findViewById(R.id.fab_ini_close);
        fab_ini_gastos = (FloatingActionButton) findViewById(R.id.fab_ini_gastos);
        fab_ini_planejamento = (FloatingActionButton) findViewById(R.id.fab_ini_planejamento);
        fab_ini_pagamento = (FloatingActionButton) findViewById(R.id.fab_ini_pagamento);
        fab_ini_configuracoes = (FloatingActionButton) findViewById(R.id.fab_ini_configuracoes);

        fab_ini_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fab_ini_add.setVisibility(View.INVISIBLE);
                fab_ini_close.setVisibility(View.VISIBLE);
                fab_ini_gastos.setVisibility(View.VISIBLE);
                fab_ini_planejamento.setVisibility(View.VISIBLE);
                fab_ini_planejamento.setRippleColor(context.getResources().getColor(R.color.colorPurple));
                fab_ini_pagamento.setVisibility(View.VISIBLE);
                fab_ini_pagamento.setRippleColor(context.getResources().getColor(R.color.colorYellow));
                fab_ini_configuracoes.setVisibility(View.VISIBLE);
                fab_ini_configuracoes.setRippleColor(context.getResources().getColor(R.color.colorBlue));
            }
        });

        fab_ini_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_ini_add.setVisibility(View.VISIBLE);
                fab_ini_close.setVisibility(View.INVISIBLE);
                fab_ini_gastos.setVisibility(View.INVISIBLE);
                fab_ini_planejamento.setVisibility(View.INVISIBLE);
                fab_ini_pagamento.setVisibility(View.INVISIBLE);
                fab_ini_configuracoes.setVisibility(View.INVISIBLE);
            }
        });

        bt_ini_addviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibirViagemNew();
            }
        });

    }

    @Override
    public void onResume() {
        Iniciar();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.mn_ini_novoviagem:
                ExibirViagemNew();
                break;
        }
            return true;
    }

    public void ExibirViagemNew(){
        DialogFragment viagemnew = new Viagem_New();
        viagemnew.show(getSupportFragmentManager(), "viagemnew");
    }

    public void Iniciar(){
        viagens = new Viagens_Dao(context).listar(id_usuario);
        if(viagens.size() > 0){
            bt_ini_addviagem.setVisibility(View.INVISIBLE);
            tx_ini_dataviagem.setVisibility(View.VISIBLE);
            tx_ini_valorviagem.setVisibility(View.VISIBLE);
            posicao = viagens.size();
            viagem = new Viagens_Dao(context).buscarID(posicao);
            titulo = viagem.getVi_nome();
            getSupportActionBar().setTitle(titulo);
            tx_ini_dataviagem.setText(context.getResources().getString(R.string.periodo
            ) + " " + viagem.getVi_dtinic() + " " + context.getResources().getString(R.string.a) + " " + viagem.getVi_dtfim());
            tx_ini_valorviagem.setText(context.getResources().getString(R.string.valordisponivel)+ " " + context.getResources().getString(R.string.moeda) + " " + viagem.getVi_valor());
        }
        else{
            bt_ini_addviagem.setVisibility(View.VISIBLE);
            tx_ini_dataviagem.setVisibility(View.INVISIBLE);
            tx_ini_valorviagem.setVisibility(View.INVISIBLE);
        }
    }

    public static int getId_usuario() {
        return id_usuario;
    }

}
