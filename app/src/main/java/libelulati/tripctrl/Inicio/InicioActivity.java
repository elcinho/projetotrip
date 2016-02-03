package libelulati.tripctrl.Inicio;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem_New;

public class InicioActivity extends AppCompatActivity {
    int id_usuario;

    Button bt_ini_addviagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        id_usuario = 1; // BUSCAR ID DO USUARIO LOGADO

        bt_ini_addviagem = (Button)findViewById(R.id.bt_ini_addviagem);
        bt_ini_addviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibirViagemNew();
            }
        });
    }

    public void ExibirViagemNew(){
        DialogFragment viagemnew = new Viagem_New();
        viagemnew.show(getSupportFragmentManager(), "viagemnew");
    }

    public int getId_usuario() {
        return id_usuario;
    }

}
