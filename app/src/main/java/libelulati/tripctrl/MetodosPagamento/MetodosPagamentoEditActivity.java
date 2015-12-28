package libelulati.tripctrl.MetodosPagamento;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;


public class MetodosPagamentoEditActivity extends AppCompatActivity {

    private Spinner spinnerUsuario , spinnerTipoPagamento , spinnerViagem;
    private List<String> listaUsuario, listaTipoPagamento, listaViagem;
    MetodosPagamentoDAO data;
    private EditText ed_mp_usuario, ed_mp_viagem, ed_mp_tipoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_edit);

             getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        this.ed_mp_usuario = (EditText) findViewById(R.id.ed_mp_usuario);
        spinnerUsuario = (Spinner) findViewById(R.id.sp_mp_usuario);
        listaUsuario = new ArrayList<String>();
        data = new MetodosPagamentoDAO(this);
        this.ed_mp_usuario.setText("");
        listaUsuario = data.SpinerUsuario();

        ArrayAdapter<String> adpterUsuario = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaUsuario);
        adpterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adpterUsuario);

        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                ed_mp_usuario.setText(arg0.getItemAtPosition(arg2).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        this.ed_mp_viagem = (EditText) findViewById(R.id.ed_mp_viagem);
        spinnerUsuario = (Spinner) findViewById(R.id.sp_mp_viagem);
        listaUsuario = new ArrayList<String>();
        data = new MetodosPagamentoDAO(this);
        this.ed_mp_viagem.setText("");
        listaViagem = data.SpinerViagem();

        ArrayAdapter<String> adpterViagem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaUsuario);
        adpterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adpterViagem);

        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                Toast.makeText(arg0.getContext(), "selecionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
                ed_mp_usuario.setText(arg0.getItemAtPosition(arg2).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });


        this.ed_mp_tipoPagamento = (EditText) findViewById(R.id.ed_mp_tipopagamento);
        spinnerTipoPagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
        listaTipoPagamento = new ArrayList<String>();
        data = new MetodosPagamentoDAO(this);
        this.ed_mp_usuario.setText("");
        listaTipoPagamento = data.SpinerTipoPagamento();

        ArrayAdapter<String> adpterTipoPagamento = new ArrayAdapter<String>(this, android.R.layout.activity_list_item, listaTipoPagamento);
        adpterTipoPagamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPagamento.setAdapter(adpterTipoPagamento);
        spinnerTipoPagamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                Toast.makeText(arg0.getContext(), "selecionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
                ed_mp_tipoPagamento.setText(arg0.getItemAtPosition(arg2).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });





        final int usuario = InicioActivity.getId_uslogado();
    final Spinner sp_idViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
    final Spinner sp_tipopagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
    final EditText mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
    final EditText mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
    final EditText mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);
    final Button mp_bt_salvar = (Button) findViewById(R.id.bt_mp_salvar);


        mp_bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final Context context = v.getContext();
            MetodosPagamento mp = new MetodosPagamento();

            mp.setUs_id(usuario);
            mp.setDv_id(Integer.parseInt(sp_idViagem.getAdapter().toString()));
            mp.setTp_id(Integer.parseInt(sp_tipopagamento.getAdapter().toString()));
            mp.setMe_detalhes(mp_detalhe.getText().toString());
            mp.setMe_valor(Integer.parseInt(mp_valor.getText().toString()));
            mp.setMe_vencimento(mp_vencimento.getText().toString());

                boolean sucesso = new MetodosPagamentoDAO(context).criar(mp);

            if (sucesso) {
                Toast.makeText(context, context.getResources().getString(R.string.metodopagameto) + " " + context.getResources().getString(R.string.sucesso_criado) + ".", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar) + " " + context.getResources().getString(R.string.metodopagameto)+ ".", Toast.LENGTH_LONG).show();
            }

            finish();
        }

    });
}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                if(getActionBar() == null){
                    onBackPressed();
                }
                else{
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
