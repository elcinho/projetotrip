package libelulati.tripctrl.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
public class MainActivity extends AppCompatActivity {

    Button bt_main_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_main_entrar = (Button) findViewById(R.id.bt_main_entrar);
        bt_main_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_main_entrar = new Intent (MainActivity.this, InicioActivity.class);
                startActivity(it_main_entrar);
            }
        });
    }
}
