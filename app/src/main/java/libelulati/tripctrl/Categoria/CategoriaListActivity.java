package libelulati.tripctrl.Categoria;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class CategoriaListActivity extends AppCompatActivity {

    Context context;
    int usuario = InicioActivity.getId_uslogado();
    EditText ed_ca_nome;
    boolean validar, v_nome;
    Button positivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_list);

        context = CategoriaListActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_ca_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaCategoria();
            }
        });
    }

    @Override
    public void onResume() {
        listarCategorias();
        super.onResume();
    }

    public void listarCategorias(){

        LinearLayout linearLayoutLista = (LinearLayout) findViewById(R.id.lv_item_categorias);
        linearLayoutLista.removeAllViews();

        List<Categoria> categorias = new CategoriaDAO(context).listar(usuario);

        if(categorias.size() > 0 ){
            for(Categoria categ : categorias){
                int id = categ.getCa_id();
                String ca_nome = categ.getCa_nome();

                TextView tx_ca_item = new TextView(this);
                tx_ca_item.setPadding(0, 10, 0, 10);
                tx_ca_item.setText(ca_nome);
                tx_ca_item.setTag(id);
                tx_ca_item.setTypeface(tx_ca_item.getTypeface(), Typeface.BOLD);
                tx_ca_item.setTextSize(20);
                tx_ca_item.setTextColor(getResources().getColor(R.color.colorAccent));

                linearLayoutLista.addView(tx_ca_item);
            }
        }
    }

    public void novaCategoria(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.dialog_categoria, null);
        builder.setView(dialogview);
        builder.setTitle(context.getResources().getString(R.string.title_activity_categoria_novo));

        ed_ca_nome = (EditText)findViewById(R.id.ed_ca_nome);

        ed_ca_nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ed_ca_nome.length() > 5){
                    verificarNome();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                verificarNome();
            }
        });

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                salvar();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((android.app.AlertDialog) dialog).getButton(android.app.AlertDialog.BUTTON_POSITIVE);
                positivo.setEnabled(false);
            }
        });
    }

    public void salvar(){

        Categoria categoria = new Categoria();
        categoria.setUs_id(usuario);
        categoria.setCa_nome(ed_ca_nome.getText().toString());

        if(v_nome){
            boolean sucesso = new CategoriaDAO(context).criar(categoria);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_categoria), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_categoria), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }

    }

    public void verificarNome(){
        validar = Validar.ValidarNome(ed_ca_nome.getText().toString());
        if(!validar){
            ed_ca_nome.setError(context.getResources().getString(R.string.validar_categoria));
            v_nome = false;
        }
        else{
            v_nome = true;
            positivo.setEnabled(true);
        }
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
