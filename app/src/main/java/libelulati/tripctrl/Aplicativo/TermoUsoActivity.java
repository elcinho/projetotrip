package libelulati.tripctrl.Aplicativo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuarios.Usuario;
import libelulati.tripctrl.Usuarios.Usuario_DAO;

public class TermoUsoActivity extends AppCompatActivity {
    int id_usuario = 1;
    int us_uso;
    CheckBox cb_tu_termo;
    TextView tx_tu_termo;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_uso);

        context = TermoUsoActivity.this;
        cb_tu_termo = (CheckBox)findViewById(R.id.cb_tu_check);
        tx_tu_termo = (TextView)findViewById(R.id.tx_tu_termo);

        tx_tu_termo.setMovementMethod(new ScrollingMovementMethod());

        Usuario usuario = new Usuario_DAO(context).buscaId(id_usuario);
        if(usuario != null){
            us_uso = usuario.getUs_uso();
        }
        if(us_uso == 0){
            cb_tu_termo.setChecked(false);
        }
        else{
            cb_tu_termo.setChecked(true);
        }

        cb_tu_termo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    us_uso = 1;
                    Atualizar();
                }
                else{
                    us_uso = 0;
                    Atualizar();
                }
            }
        });

    }

    public void Atualizar(){
        Usuario usuario = new Usuario_DAO(context).buscaId(id_usuario);
        usuario.setUs_uso(us_uso);

        boolean sucesso = new Usuario_DAO(context).alterarTermo(usuario, id_usuario);
        if(!sucesso){
            Toast.makeText(context, context.getResources().getString(R.string.erro_atualizar_termouso), Toast.LENGTH_LONG).show();
        }
    }
}
