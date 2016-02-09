package libelulati.tripctrl.Gastos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import libelulati.tripctrl.R;

public class GastoEditActivity extends AppCompatActivity {
    int novo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasto_edit);

        Intent it_ga_novo = getIntent();
        Bundle bundle = it_ga_novo.getExtras();

        novo = bundle.getInt("isnew");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if(novo == 1){
            getMenuInflater().inflate(R.menu.salvar_menu, menu);
            return (true);
        }
        else{
            getMenuInflater().inflate(R.menu.edit_delet_menu, menu);
            return (true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mn_gb_salvar:
                break;
            case R.id.mn_gb_editar:
                break;
            case R.id.mn_gb_deletar:
                break;
        }
        return true;
    }


}
