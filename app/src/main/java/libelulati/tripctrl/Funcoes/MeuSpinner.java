package libelulati.tripctrl.Funcoes;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import libelulati.tripctrl.R;

public class MeuSpinner {

    public void preencherSpinner(Context context, List lista, Spinner spinner){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.spinner_custom, lista);
        adapter.setDropDownViewResource(R.layout.spinner_drop_custom);
        spinner.setAdapter(adapter);
    }

    public void posicaoSelecionada(Spinner spinner, String item){
        for(int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(item)) {
                spinner.setSelection(i);
            }
        }
    }

    public void selecionarItem(Spinner spinner, final EditText editText){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
