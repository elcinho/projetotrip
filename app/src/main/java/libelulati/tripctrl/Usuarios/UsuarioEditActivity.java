package libelulati.tripctrl.Usuarios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class UsuarioEditActivity extends AppCompatActivity {
    int novo = 0;
    int vis_menu = 0;
    int id_usuario = 0;
    int us_semsenha = 0;
    EditText ed_us_nome, ed_us_email, ed_us_dtnasc, ed_us_senha;
    CheckBox cb_us_semsenha;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_edit);

        context = UsuarioEditActivity.this;

        ed_us_nome = (EditText)findViewById(R.id.ed_us_nome);
        ed_us_email = (EditText)findViewById(R.id.ed_us_email);
        ed_us_senha = (EditText)findViewById(R.id.ed_us_senha);
        ed_us_dtnasc = (EditText)findViewById(R.id.ed_us_dtnasc);

        Intent it_us_novo = getIntent();
        Bundle bundle = it_us_novo.getExtras();

        novo = bundle.getInt("novo");
        ed_us_nome.setText(bundle.getString(Nomes.getUsNome()));
        ed_us_email.setText(bundle.getString(Nomes.getUsEmail()));
        ed_us_dtnasc.setText(bundle.getString(Nomes.getUsDtnasc()));
        if(us_semsenha == 0){
            cb_us_semsenha.setChecked(false);
        }
        else{
            cb_us_semsenha.setChecked(true);
        }

        switch (novo){
            case 1:
                UsuarioNovo();
                break;
            case 2:
                UsuarioShow();
                break;
        }
    }

    public void showDatePicker(View view, EditText ed_data){
        DialogFragment dialogFragment = new DatePicker(ed_data);
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    public void Salvar(){

    }

    public void UsuarioNovo(){
        getSupportActionBar().setTitle(context.getResources().getString(R.string.title_activity_usuario_novo));
        vis_menu = 1;
        invalidateOptionsMenu();

        ed_us_dtnasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_us_dtnasc);
            }
        });

        ed_us_dtnasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ed_us_dtnasc.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

    }

    public void UsuarioShow(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_us_nome.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 2;
        invalidateOptionsMenu();

        getSupportActionBar().setTitle(context.getResources().getString(R.string.title_activity_usuario_edit));
        ed_us_senha.setVisibility(View.INVISIBLE);
    }

    public void UsuarioEditar(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_us_nome.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 3;
        invalidateOptionsMenu();

        ed_us_dtnasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_us_dtnasc);
            }
        });

        ed_us_dtnasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ed_us_dtnasc.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        ed_us_nome.requestFocus();
    }




}
