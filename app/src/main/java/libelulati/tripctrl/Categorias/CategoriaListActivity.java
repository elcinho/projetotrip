package libelulati.tripctrl.Categorias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class CategoriaListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    Context context;
    EditText ed_ca_novo, ed_di_editar;
    TextView tx_ca_nome;
    ImageView iv_ca_novo;
    boolean v_categoria;
    Validar validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_list);

        context = CategoriaListActivity.this;

        validar = new Validar(context);

        ed_ca_novo = (EditText) findViewById(R.id.ed_ca_novo);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_ca_novo.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        iv_ca_novo = (ImageView) findViewById(R.id.iv_ca_novo);
        iv_ca_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Categoria> cats = new Categorias_DAO(context).listar(id_usuario);
                v_categoria = validar.ValidarCategorias(cats, ed_ca_novo.getText().toString(), ed_ca_novo);
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
        Categoria categoria = new Categoria();

        categoria.setUs_id(id_usuario);
        categoria.setCa_nome(ed_ca_novo.getText().toString());

        if(IsValido()){
            boolean sucesso = new Categorias_DAO(context).criar(categoria);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_categoria), Toast.LENGTH_LONG).show();
                ed_ca_novo.setText("");
                ed_ca_novo.clearFocus();

                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(ed_ca_novo.getWindowToken(), 0);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                Listar();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_categoria), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos),Toast.LENGTH_LONG).show();
        }
    }

    public void Atualizar(int id){
        Categoria categoria = new Categoria();
        categoria.setUs_id(id_usuario);
        categoria.setCa_nome(ed_di_editar.getText().toString());

        if(IsValido()){
            boolean sucesso = new Categorias_DAO(context).atualizar(categoria, id);
            if(sucesso){
                Listar();
            }
            else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_categoria), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void Listar(){
        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_ca_lista);
        linearLayout_itens.removeAllViews();

        List<Categoria> categorias = new Categorias_DAO(context).listar(id_usuario);
        View viewItens = null;

        if(categorias.size() > 0){
            for(final Categoria categoria : categorias){
                final int id_ca = categoria.getCa_id();
                int id_us = categoria.getUs_id();
                final String ca_nome = categoria.getCa_nome();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_textview, null);

                tx_ca_nome = (TextView)viewItens.findViewById(R.id.tx_vw_textview);

                tx_ca_nome.setText(ca_nome);
                tx_ca_nome.requestFocus();

                viewItens.setTag(id_ca);

                if(id_us == id_usuario){
                    viewItens.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Editar(id_ca, ca_nome);
                        }
                    });

                    viewItens.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Deletar(id_ca);
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
                Categorias_DAO categorias_dao = new Categorias_DAO(context);
                boolean sucesso = categorias_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_categoria), Toast.LENGTH_LONG).show();
                    Listar();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_categoria), Toast.LENGTH_LONG).show();
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
        if(v_categoria){
            return true;
        }
        else{
            return false;
        }
    }
}
