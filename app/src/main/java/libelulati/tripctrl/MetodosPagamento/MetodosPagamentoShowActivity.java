package libelulati.tripctrl.MetodosPagamento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;

public class MetodosPagamentoShowActivity extends AppCompatActivity {

    private MenuItem item;
    private Spinner spinnerUsuario , spinnerTipoPagamento , spinnerViagem;
    private List<String> listaUsuario, listaTipoPagamento, listaViagem;
    MetodosPagamentoDAO data;
    private EditText ed_mp_usuario, ed_mp_viagem, ed_mp_tipoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_show);



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

                Toast.makeText(arg0.getContext(), "selecionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
                ed_mp_usuario.setText(arg0.getItemAtPosition(arg2).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        this.ed_mp_viagem = (EditText) findViewById(R.id.ed_mp_viagem);
        spinnerViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
        listaViagem = new ArrayList<String>();
        data = new MetodosPagamentoDAO(this);
        this.ed_mp_viagem.setText("");
        listaViagem = data.SpinerViagem();

        ArrayAdapter<String> adpterViagem = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaViagem);
        adpterUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerViagem.setAdapter(adpterViagem);

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




        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_me_editar);
        final Spinner  sp_idViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
        final Spinner  sp_tipopagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
        final EditText mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
        final EditText mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
        final EditText mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);
        final Button mp_bt_salvar = (Button) findViewById(R.id.bt_mp_salvar);

        Intent it_mp_show = getIntent();
        Bundle bundle = it_mp_show.getExtras();

        final int id = bundle.getInt(StringsNomes.getID());
        sp_idViagem.setAdapter((SpinnerAdapter) bundle.get(StringsNomes.getViId()));
        sp_tipopagamento.setAdapter((SpinnerAdapter) bundle.get(StringsNomes.getTpId()));
        mp_detalhe.setText(bundle.getString(StringsNomes.getMeDetalhe()));
        mp_valor.setText(bundle.getInt(StringsNomes.getMeValor()));
        mp_vencimento.setText(bundle.getString(StringsNomes.getMeVencimento()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sp_idViagem.setEnabled(true);
                sp_tipopagamento.setEnabled(true);
                mp_detalhe.setEnabled(true);
                mp_valor.setEnabled(true);
                mp_vencimento.setEnabled(true);

                sp_idViagem.requestFocus();

                mp_bt_salvar.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
            }
        });

        mp_bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int usuario_id = InicioActivity.getId_uslogado();

                final Context context = v.getContext();
                MetodosPagamento metodosPagamento = new MetodosPagamento();

                metodosPagamento.setUs_id(usuario_id);
                metodosPagamento.setDv_id((int) sp_idViagem.getAdapter().getItemId(id));
                metodosPagamento.setTp_id((int) sp_tipopagamento.getAdapter().getItemId(id));
                metodosPagamento.setMe_detalhes(mp_detalhe.getText().toString());
                metodosPagamento.setMe_valor(mp_valor.getText().charAt(id));
                metodosPagamento.setMe_vencimento(mp_vencimento.getText().toString());

                boolean sucesso = new MetodosPagamentoDAO(context).atualizar(metodosPagamento, id);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getMETODOPAGAMENTO() + " " + metodosPagamento.getMe_id() + " " + MensagensUsuario.getEditado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_editar() + MensagensUsuario.getVIAGEM() + " " + metodosPagamento.getMe_id(), Toast.LENGTH_LONG).show();
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
