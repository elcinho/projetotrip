package libelulati.tripctrl.Usuarios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

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
        cb_us_semsenha = (CheckBox)findViewById(R.id.cb_us_semsenha);

        Intent it_us_novo = getIntent();
        Bundle bundle = it_us_novo.getExtras();

        novo = bundle.getInt("novo");
        id_usuario = bundle.getInt(Nomes.getID());
        ed_us_nome.setText(bundle.getString(Nomes.getUsNome()));
        ed_us_email.setText(bundle.getString(Nomes.getUsEmail()));
        ed_us_dtnasc.setText(bundle.getString(Nomes.getUsDtnasc()));
        us_semsenha = bundle.getInt(Nomes.getUsSemsenha());
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
        final Usuario usuario = new Usuario();

        usuario.setUs_nome(ed_us_nome.getText().toString());
        usuario.setUs_email(ed_us_email.getText().toString());
        usuario.setUs_dtnasc(ed_us_dtnasc.getText().toString());
        usuario.setUs_senha(ed_us_senha.getText().toString());

        cb_us_semsenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    usuario.setUs_semsenha(1);
                } else {
                    usuario.setUs_semsenha(0);
                }
            }
        });

        if(IsValido()){
            boolean sucesso = new Usuario_DAO(context).criar(usuario);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_usuario), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_usuario), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos),Toast.LENGTH_LONG).show();
        }
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
                if (hasFocus) {
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

    public void Atualizar(){
        final Usuario usuario = new Usuario();

        usuario.setUs_nome(ed_us_nome.getText().toString());
        usuario.setUs_email(ed_us_email.getText().toString());
        usuario.setUs_dtnasc(ed_us_dtnasc.getText().toString());

        cb_us_semsenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    usuario.setUs_semsenha(1);
                } else {
                    usuario.setUs_semsenha(0);
                }
            }
        });

        if(IsValido()){
            boolean sucesso = new Usuario_DAO(context).atualizar(usuario, id_usuario);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_alterar_usuario), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_usuario), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public boolean IsValido(){
        return true;
    }

    public void AlterarSenha(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        switch (vis_menu) {
            case 1:
                getMenuInflater().inflate(R.menu.salvar_menu, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.editar_menu, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.usuario_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                if(getActionBar() == null){
                    onBackPressed();
                }
                else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                break;
            case R.id.mn_gb_salvar:
                Salvar();
                break;
            case R.id.mn_editar:
                UsuarioEditar();
                break;
            case R.id.mn_us_salvar:
                Atualizar();
                break;
            case R.id.mn_us_alterarsenha:
                AlterarSenha();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }






}
