package libelulati.tripctrl.Pagamentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PagamentosEditActivity extends AppCompatActivity {

    int novo = 0;
    int vis_menu = 0;
    int id_usuario = InicioActivity.getId_usuario();
    EditText ed_pae_descricao, ed_pae_tipopagamento, ed_pae_valor, ed_pae_vencimento;
    Spinner sp_pae_tipopagamento;
    Context context;
    int id_viagem, id_pagamento;
    List<String> listatipopagamentos;
    MeuSpinner meuSpinner = new MeuSpinner();
    String nulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos_edit);

        context = PagamentosEditActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed_pae_descricao = (EditText)findViewById(R.id.ed_pae_descricao);
        ed_pae_tipopagamento = (EditText)findViewById(R.id.ed_pae_tipopagamento);
        ed_pae_valor = (EditText)findViewById(R.id.ed_pae_valor);
        ed_pae_vencimento = (EditText)findViewById(R.id.ed_pae_dtvenc);
        sp_pae_tipopagamento = (Spinner)findViewById(R.id.sp_pae_tipopagamento);

        Intent it_pa_novo = getIntent();
        Bundle bundle = it_pa_novo.getExtras();

        novo = bundle.getInt("novo");
        id_pagamento = bundle.getInt(Nomes.getID());
        id_viagem = bundle.getInt(Nomes.getViId());
        ed_pae_descricao.setText(bundle.getString(Nomes.getPaDescricao()));
        ed_pae_tipopagamento.setText(bundle.getString(Nomes.getTpId()));
        ed_pae_valor.setText(bundle.getString(Nomes.getPaValor()));
        ed_pae_vencimento.setText(bundle.getString(Nomes.getPaVencimento()));

        nulo = context.getResources().getString(R.string.encontrado_registro);

        listatipopagamentos = new Pagamentos_DAO(context).sp_tipopagamentos();

        switch (novo){
            case 1:
                PagamentoNovo();
                break;
            case 2:
                PagamentoShow();
                break;
        }
    }

    public void PagamentoNovo(){

    }

    public void PagamentoShow(){

    }

    public void PagamentoEditar(){

    }

    public void showDatePicker(View view, EditText ed_data){
        DialogFragment dialogFragment = new DatePicker(ed_data);
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public void Salvar(){
        Pagamento pagamento = new Pagamento();

        pagamento.setUs_id(id_usuario);
        pagamento.setVi_id(id_viagem);
        pagamento.setTp_id(ed_pae_tipopagamento.getText().toString());
        pagamento.setPa_descricao(ed_pae_descricao.getText().toString());
        pagamento.setPa_valor(ed_pae_valor.getText().toString());
        pagamento.setPa_dtvenc(ed_pae_vencimento.getText().toString());

        if(IsValido()){
            boolean sucesso = new Pagamentos_DAO(context).criar(pagamento);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_pagamento), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_pagamento), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public boolean IsValido(){
        return true;
    }

}
