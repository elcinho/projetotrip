package libelulati.tripctrl.TipoPagamento;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class TiposPagamentoListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    Context context;
    EditText ed_tp_novo, ed_di_editar;
    TextView tx_tp_nome;
    ImageView iv_tp_novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_pagamento_list);

        context = TiposPagamentoListActivity.this;

        ed_tp_novo = (EditText) findViewById(R.id.ed_tp_novo);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_tp_novo.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        iv_tp_novo = (ImageView) findViewById(R.id.iv_tp_novo);
        iv_tp_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar();
            }
        });
    }

    @Override
    public void onResume() {
        Listar();
        super.onResume();
    }

    public void Salvar(){
        TipoPagamento tipoPagamento = new TipoPagamento();

        tipoPagamento.setUs_id(id_usuario);
        tipoPagamento.setTp_nome(ed_tp_novo.getText().toString());
        if(IsValido()){
            boolean sucesso = new TipoPagamento_DAO(context).criar(tipoPagamento);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_tipopagamento), Toast.LENGTH_LONG).show();
                ed_tp_novo.setText("");
                ed_tp_novo.clearFocus();

                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed_tp_novo.getWindowToken(), 0);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                Listar();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_tipopagamento), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos),Toast.LENGTH_LONG).show();
        }
    }

    public void Atualizar (int id){
        TipoPagamento tipoPagamento = new TipoPagamento();
        tipoPagamento.setUs_id(id_usuario);
        tipoPagamento.setTp_nome(ed_di_editar.getText().toString());

        if(IsValido()){
            boolean sucesso = new TipoPagamento_DAO(context).atualizar(tipoPagamento, id);
            if(sucesso){
                Listar();
            }
            else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_tipopagamento), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void Listar(){
        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_tp_lista);
        linearLayout_itens.removeAllViews();

        List<TipoPagamento> tipoPagamentos = new TipoPagamento_DAO(context).listar(id_usuario);
        View viewItens = null;

        if(tipoPagamentos.size() > 0){
            for(final TipoPagamento tipoPagamento : tipoPagamentos){
                final int id_tp = tipoPagamento.getTp_id();
                int id_us = tipoPagamento.getUs_id();
                final String tp_nome = tipoPagamento.getTp_nome();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_textview, null);

                tx_tp_nome = (TextView)viewItens.findViewById(R.id.tx_vw_textview);

                tx_tp_nome.setText(tp_nome);
                tx_tp_nome.requestFocus();

                viewItens.setTag(id_tp);

                if(id_us == id_usuario){
                    viewItens.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Editar(id_tp, tp_nome);
                        }
                    });

                    viewItens.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Deletar(id_tp);
                            return false;
                        }
                    });
                }

                linearLayout_itens.addView(viewItens);
            }
        }
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
                TipoPagamento_DAO tipoPagamento_dao = new TipoPagamento_DAO(context);
                boolean sucesso = tipoPagamento_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_tipopagamento), Toast.LENGTH_LONG).show();
                    Listar();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_tipopagamento), Toast.LENGTH_LONG).show();
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

    public void Editar(final int id, String nome) {
        AlertDialog editardialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.editar) + " " + nome);
        LayoutInflater inflater = getLayoutInflater();
        View layoutview = inflater.inflate(R.layout.dialog_editar_edittext, null);
        builder.setView(layoutview);
        ed_di_editar = (EditText)layoutview.findViewById(R.id.ed_di_editar);
        ed_di_editar.setText(nome);

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Atualizar(id);
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        editardialog = builder.create();
        editardialog.show();
    }

    public boolean IsValido(){
        return true;
    }
}
