package libelulati.tripctrl.Listas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.R;

public class ItemListaListActivity extends AppCompatActivity {

    String titulo;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lista_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = ItemListaListActivity.this;

        Intent lista = getIntent();
        Bundle bundle = lista.getExtras();

        titulo = bundle.getString(StringsNomes.getLiNome());
        getSupportActionBar().setTitle(titulo);
    }

    @Override
    public void onResume() {
        listarItens();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_itemlista, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.mn_il_novo:
                novoitem();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public void listarItens(){

    }

    public void novoitem(){

    }

}
