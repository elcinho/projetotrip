package libelulati.tripctrl.Categorias;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class CategoriaListActivity extends AppCompatActivity {
    int id_usuario = InicioActivity.getId_usuario();
    int id_categoria;
    Context context;
    EditText ed_ca_novo;
    ImageView ib_ca_novo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_list);

        context = CategoriaListActivity.this;

        ed_ca_novo = (EditText) findViewById(R.id.ed_ca_novo);
        ib_ca_novo = (ImageView) findViewById(R.id.iv_ca_novo);

        ib_ca_novo.setOnClickListener(new View.OnClickListener() {
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
        Categoria categoria = new Categoria();

        categoria.setUs_id(id_usuario);
        categoria.setCa_nome(ed_ca_novo.getText().toString());

        if(IsValido()){
            boolean sucesso = new Categorias_DAO(context).criar(categoria);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_categoria), Toast.LENGTH_LONG).show();
                ed_ca_novo.setText("");
                ed_ca_novo.clearFocus();
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

    public void Atualizar(){

    }

    public void Listar(){
        LinearLayout linearLayout_itens = (LinearLayout)findViewById(R.id.li_ca_lista);
        linearLayout_itens.removeAllViews();

        List<Categoria> categorias = new Categorias_DAO(context).listar(id_usuario);
        View viewItens = null;
        TextView tx_ca_nome;

        if(categorias.size() > 0){
            for(final Categoria categoria : categorias){
                final int id_ca = categoria.getCa_id();
                int id_us = categoria.getUs_id();
                String ca_nome = categoria.getCa_nome();

                LayoutInflater inflater = getLayoutInflater();
                viewItens = inflater.inflate(R.layout.view_list_textview, null);

                tx_ca_nome = (TextView)viewItens.findViewById(R.id.tx_vw_textview);
                tx_ca_nome.setText(ca_nome);

                viewItens.setTag(id_ca);

                if(id_us == id_usuario){
                    viewItens.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Editar();
                        }
                    });

                    viewItens.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Deletar();
                            return false;
                        }
                    });
                }

                linearLayout_itens.addView(viewItens);
            }
        }
    }

    public void Deletar(){
        Toast.makeText(context, "teste long Click", Toast.LENGTH_LONG).show();
    }

    public void Editar(){
        Toast.makeText(context, "teste short Click", Toast.LENGTH_LONG).show();
    }

    public boolean IsValido(){
        return true;
    }
}
