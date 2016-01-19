package libelulati.tripctrl.Usuario;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Senha.AlterarSenha;

/*
 * Classe responsável pelo funcionamento da activity ver/editar usuário (show)
 *
 */

public class UsuarioShowActivity extends AppCompatActivity {

    //declaração dos objetos e variáveis
    FloatingActionButton fab_editar;
    EditText uss_nome, uss_dtnasc, uss_email, uss_telefone, uss_localizacao, uss_cod, uss_codarea;
    Context contexto;
    int usuaruioid = InicioActivity.getId_uslogado();
    int vis_menu, ano, dia, mes;
    StringBuilder dataformatada;
    MenuItem vis_salvar, vis_alterar;
    String titulo_show, titulo_edit;
    boolean v_nome, v_dtnasc, v_telefone, v_codarea, validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_show);

        //ativa o funcionamento do botão voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // esconde o menu "salvar / alterar senha" caso esteja na tela de visualização
        vis_menu = 1;
        invalidateOptionsMenu();

        contexto = UsuarioShowActivity.this;

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_us_editar);
        uss_nome = (EditText) findViewById(R.id.ed_uss_nome);
        uss_dtnasc = (EditText) findViewById(R.id.ed_uss_dtnasc);
        uss_email = (EditText) findViewById(R.id.ed_uss_email);
        uss_telefone = (EditText) findViewById(R.id.ed_uss_telefone);
        uss_localizacao = (EditText) findViewById(R.id.ed_uss_localizacao);
        uss_cod = (EditText) findViewById(R.id.ed_uss_codusuario);
        uss_codarea = (EditText) findViewById(R.id.ed_uss_codarea);

        uss_dtnasc.setInputType(InputType.TYPE_NULL);

        // desativa o teclado ao carregar a tela de visualização
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(uss_nome.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //determina o título para visualização e edição
        titulo_show = contexto.getResources().getString(R.string.title_activity_usuario_show) + " " + uss_nome.getText().toString();
        titulo_edit = contexto.getResources().getString(R.string.title_activity_usuario_edit) + " " + uss_nome.getText().toString();

        // seta o título visualização ao carregar a tela
        getSupportActionBar().setTitle(titulo_show);

        // verifica o campo nome ao perder o foco
        uss_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(uss_nome.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarNome();
                }
            }
        });

        // verifica o campo data de nascimento ao perder o foco
        uss_dtnasc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(uss_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarDtNasc();
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(uss_dtnasc.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        // verifica o campo codigo de area ao perder o foco
        uss_codarea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(uss_codarea.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarCodArea();
                }
            }
        });

        // verifica o campo telefone ao perder o foco
        uss_telefone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(uss_telefone.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarTelefone();
                }
            }
        });

        // determina a ação do click no botão editar
        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //seta o título editar
                getSupportActionBar().setTitle(titulo_edit);

                // habilita a edição dos campos
                uss_nome.setEnabled(true);
                uss_dtnasc.setEnabled(true);
                uss_localizacao.setEnabled(true);
                uss_codarea.setEnabled(true);
                uss_telefone.setEnabled(true);

                uss_email.setTextColor(contexto.getResources().getColor(R.color.cinza));
                uss_cod.setTextColor(contexto.getResources().getColor(R.color.cinza));

                //oculta o botão editar
                fab_editar.setVisibility(View.INVISIBLE);

                // exibe o menu 'salvar / alterar senha'
                vis_menu = 2;
                invalidateOptionsMenu();

                uss_nome.requestFocus();
            }
        });
    }

    /* Menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        vis_salvar = menu.findItem(R.id.mn_us_salvar);
        vis_alterar = menu.findItem(R.id.mn_us_alterarsenha);
        switch (vis_menu){
            case 1:
                vis_salvar.setVisible(false);
                vis_alterar.setVisible(false);
                break;
            case 2:
                vis_salvar.setVisible(true);
                vis_alterar.setVisible(true);
                break;
        }
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mn_us_alterarsenha:
                exibirAlterarSenha();
                break;
            case R.id.mn_us_salvar:
                salvar();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return (true);
    }
    /* Menu - Fim */

    @Override
    public void onResume(){
        AtualizarDados();
        super.onResume();
    }

    // Método para exibir o diálogo 'alterar senha'
    public void exibirAlterarSenha(){
        android.app.DialogFragment alterarsenha = new AlterarSenha(usuaruioid);
        alterarsenha.show(getFragmentManager(), "alterarsenha");
    }

    // Método para alterar os dados de usuário
    public void AtualizarDados(){
        contexto = getApplicationContext();

        UsuarioDAO usuarioDAO = new UsuarioDAO(contexto);
        Usuario usuario = usuarioDAO.buscaId(usuaruioid);

        if(usuario == null){
            Intent it_uss_edusuario = getIntent();
            Bundle bundle = it_uss_edusuario.getExtras();

            uss_nome.setText(bundle.getString(StringsNomes.getUsNome()));
            uss_dtnasc.setText(bundle.getString(StringsNomes.getUsDtnasc()));
            uss_email.setText(bundle.getString(StringsNomes.getUsEmail()));
            uss_codarea.setText(bundle.getString(StringsNomes.getUsCodarea()));
            uss_telefone.setText(bundle.getString(StringsNomes.getUsTelefone()));
            uss_localizacao.setText(bundle.getString(StringsNomes.getUsLongitude()));
            uss_cod.setText(bundle.getString(StringsNomes.getUsCod()));
        }
        else{
            uss_nome.setText(usuario.getUs_nome());
            uss_email.setText(usuario.getUs_email());
            uss_dtnasc.setText(usuario.getUs_dtnasc());
            uss_codarea.setText(usuario.getUs_codarea());
            uss_telefone.setText(usuario.getUs_telefone());
            uss_localizacao.setText(usuario.getUs_longitude());
            uss_cod.setText(usuario.getUs_cod());
        }
    }

    // Método para salvar as alterações do usuário
    public void salvar(){
        if(IsValido()){
            Usuario usuario = new Usuario();

            usuario.setUs_id(usuaruioid);
            usuario.setUs_nome(uss_nome.getText().toString());
            usuario.setUs_dtnasc(uss_dtnasc.getText().toString());
            usuario.setUs_email(uss_email.getText().toString());
            usuario.setUs_codarea(uss_codarea.getText().toString());
            usuario.setUs_telefone(uss_telefone.getText().toString());
            usuario.setUs_latitude(uss_localizacao.getText().toString());
            usuario.setUs_longitude(uss_localizacao.getText().toString());
            usuario.setUs_cod(uss_cod.getText().toString());

            boolean sucesso = new UsuarioDAO(contexto).atualizar(usuario, usuaruioid);
            if(sucesso){
                Toast.makeText(contexto, contexto.getResources().getString(R.string.sucesso_alterar_usuario), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(contexto, contexto.getResources().getString(R.string.erro_alterar_usuario), Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(contexto, contexto.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
            uss_nome.requestFocus();
        }
    }

    // Método para verificar se todos os campos sao válidos
    public boolean IsValido(){
        if(v_nome && v_dtnasc && v_codarea && v_telefone){
            return true;
        }
        else{
            return false;
        }
    }

    // Método para verificar Nome
    public void verificarNome(){
        validar = Validar.ValidarNome(uss_nome.getText().toString());
        if(!validar){
            uss_nome.setError(contexto.getResources().getString(R.string.validar_nome));
            v_nome = false;
        }
        else{
            v_nome = true;
        }
    }

    // Método para verificar data de nascimento
    public void verificarDtNasc(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(uss_dtnasc.getText().toString()));
            validar = Validar.ValidarDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            uss_dtnasc.setError(contexto.getResources().getString(R.string.validar_dtnasc));
            v_dtnasc = false;
        }
        else{
            v_dtnasc = true;
        }
    }

    // Método para verificar telefone
    public void verificarTelefone(){
        validar = Validar.ValidarTelefone(uss_telefone.length(), uss_telefone.getText().toString());
        if(!validar){
            uss_telefone.setError(contexto.getResources().getString(R.string.validar_telefone));
            v_telefone = false;
        }
        else{
            v_telefone = true;
        }
    }

    // Método para verificar codigo área
    public void verificarCodArea(){
        validar = Validar.ValidarCodArea(uss_codarea.getText().toString());
        if(!validar){
            uss_codarea.setError(contexto.getResources().getString(R.string.validar_codarea));
            v_codarea = false;
        }
        else{
            v_codarea = true;
        }
    }

    // Método para criar o datapicker/calendário com a data atual
    public void calendario() {
        final Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    // Método para atualizar o datapicker/calendário para a data informada
    public void AtualizarData() {
        dataformatada = (new StringBuilder().append(dia).append("/").append(mes).append("/").append(ano));
        uss_dtnasc.setText(dataformatada);
    }

    // Método para exibir o datapicker/calendário
    public void showDatePickerDialog_uss_dtnasc(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
    }

    // Subclasse para instanciar o calendário
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            calendario();
            return new DatePickerDialog(getActivity(), this, ano, mes, dia);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ano = year;
            mes = month + 1;
            dia = day;

            AtualizarData();
        }
    }
}
