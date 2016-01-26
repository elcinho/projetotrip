package libelulati.tripctrl.Listas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class ListasListActivity extends AppCompatActivity {

    Context context;
    int usuario = InicioActivity.getId_uslogado();
    EditText nome_lista;
    boolean validar, v_nome;
    Button positivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = ListasListActivity.this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_li_novo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaLista();
            }
        });
    }

    @Override
    public void onResume() {
        listarListas();
        super.onResume();
    }

    public void novaLista(){
        AlertDialog novalista;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.dialog_lista, null);
        builder.setView(dialogview);
        builder.setTitle(context.getResources().getString(R.string.lista));
        builder.setMessage(" ");

        nome_lista = (EditText)dialogview.findViewById(R.id.ed_dia_lista);

        nome_lista.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nome_lista.length() > 5) {
                    verificarNome();
                }
                else{
                    nome_lista.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nome_lista.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (nome_lista.length() > 5) {
                    verificarNome();
                }
                else{
                    nome_lista.setError(null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                salvar();
                listarListas();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        novalista = builder.create();
        novalista.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positivo.setEnabled(false);
            }
        });
        novalista.show();

    }

    public void listarListas(){

        LinearLayout linearLayoutLista = (LinearLayout) findViewById(R.id.lv_item_listas);
        linearLayoutLista.removeAllViews();

        List<Lista> lista = new ListasDAO(context).listar(usuario);

        if(lista.size() > 0 ){
            for(Lista list : lista){
                final int id = list.getLi_id();
                String li_nome = list.getLi_nome();

                TextView tx_li_item = new TextView(this);
                tx_li_item.setPadding(0, 10, 0, 10);
                tx_li_item.setText(li_nome);
                tx_li_item.setTag(id);
                tx_li_item.setTypeface(tx_li_item.getTypeface(), Typeface.BOLD);
                tx_li_item.setTextSize(20);
                tx_li_item.setTextColor(getResources().getColor(R.color.colorAccent));

                tx_li_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicar(id);
                    }
                });

                linearLayoutLista.addView(tx_li_item);
            }
        }
        else{
            TextView tx_novo = new TextView(this);
            tx_novo.setPadding(8, 8, 8, 8);
            tx_novo.setText(context.getResources().getString(R.string.encontrado_registro));

            linearLayoutLista.addView(tx_novo);
        }
    }

    public void salvar(){

        Lista lista = new Lista();
        lista.setUs_id(usuario);
        lista.setLi_nome(nome_lista.getText().toString());

        if(v_nome){
            boolean sucesso = new ListasDAO(context).criar(lista);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_lista), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_lista), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void verificarNome(){
        validar = Validar.ValidarNome(nome_lista.getText().toString());
        if(!validar){
            nome_lista.setError(context.getResources().getString(R.string.validar_categoria));
            v_nome = false;
        }
        else{
            v_nome = true;
            nome_lista.setError(null);
            positivo.setEnabled(true);
        }
    }

    /* Menu */
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
    /* Fim do menu */

    public void clicar(int id){

        ListasDAO listasDAO = new ListasDAO(context);
        Lista lista = listasDAO.buscarListaID(id);

        Intent listashow = new Intent(ListasListActivity.this, ItemListaListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(StringsNomes.getID(), id);
        bundle.putString(StringsNomes.getLiNome(), lista.getLi_nome());

        listashow.putExtras(bundle);
        startActivityForResult(listashow, 1);
    }
}
