package libelulati.tripctrl.Usuarios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import libelulati.tripctrl.Funcoes.Codigos;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.R;

public class EsqueciSenhaActivity extends AppCompatActivity {
    final int[] usuarioid = {0};
    RadioButton es_enviarsms, es_enviaremail;
    EditText es_telefone, es_email;
    int envio = 0, usid = 0;
    Context context;
    String codigogerado, datasolicitacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        es_enviarsms = (RadioButton) findViewById(R.id.rb_es_enviarsms);
        es_enviaremail = (RadioButton) findViewById(R.id.rb_es_enviaremail);

        es_telefone = (EditText) findViewById(R.id.ed_es_telefone);
        es_email = (EditText) findViewById(R.id.ed_es_email);

        es_email.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.enviar_menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mn_gb_enviar:
                Enviar();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public void Enviar(){
        context = EsqueciSenhaActivity.this;

        es_email.clearFocus();
        es_telefone.clearFocus();

        switch (envio) {
            case 1:
                    enviarcodigoemail();
                    dialogoCodigo();
                break;
            case 2:
                    enviarcodigosms();
                    dialogoCodigo();
                break;
            case 3:
                    ExibirAlterarSenha();
               break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean check = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_es_enviaremail:
                if (check) {
                    envio = 1;
                    es_enviaremail.setChecked(true);
                    es_telefone.setVisibility(View.INVISIBLE);
                    es_telefone.setText("");
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(es_email.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (es_email.length() < 0) {
                        es_email.requestFocus();
                    }
                }
                break;
            case R.id.rb_es_enviarsms:
                if (check) {
                    envio = 2;
                    es_enviarsms.setChecked(true);
                    es_telefone.setVisibility(View.VISIBLE);
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(es_telefone.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    if (es_telefone.length() == 0) {
                        es_telefone.requestFocus();
                    }
                }
                break;
        }
    }

    public void ExibirAlterarSenha(){
        DialogFragment alterarsenha = new AlterarSenha(usid);
        alterarsenha.show(getSupportFragmentManager(), "alterarsenha");
    }

    public void dialogoCodigo() {
        AlertDialog codigoaltsenha;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getApplicationContext().getResources().getString(R.string.codigoconfirmacao));
        builder.setMessage(getApplicationContext().getResources().getString(R.string.codigoconfirme));
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_editar_edittext, null));

        builder.setPositiveButton(getApplicationContext().getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText codigo = (EditText) ((Dialog) dialog).findViewById(R.id.ed_di_editar);
                boolean validar = Validar.ValidarTempoCodigo(datasolicitacao);
                if (validar) {
                    boolean validarcod = Validar.ValidarCodigoAltSenha(String.valueOf(codigo.getText()), codigogerado);
                    if (validarcod) {
                        ExibirAlterarSenha();
                    } else {
                        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.validar_codigoSenha), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.expirado_codigo), Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton(getApplicationContext().getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        codigoaltsenha = builder.create();
        codigoaltsenha.show();
    }

    public void enviarcodigoemail() {
        Codigos codigos = new Codigos();
        codigogerado = codigos.AltSenha();

        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        datasolicitacao = formatador.format(data);
        if(IsValido()){
            if (codigogerado.length() > 0) {

                //IMPLEMENTAR CÓDIGOS DE ENVIO DE E-MAIL

                Toast.makeText(context, getApplicationContext().getResources().getString(R.string.sucesso_enviar_codigoSenha) + ": " + codigogerado, Toast.LENGTH_LONG).show(); //TIRAR VISUALIZAÇÃO DO CODIGO

            } else {
                Toast.makeText(context, getApplicationContext().getResources().getString(R.string.erro_gerar_codigo), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void enviarcodigosms() {
        Codigos codigos = new Codigos();
        codigogerado = codigos.AltSenha();
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        datasolicitacao = formatador.format(data);
        if(IsValido()){
            if (codigogerado.length() > 0) {

                //IMPLEMENTAR CÓDIGOS DE ENVIO DE SMS

                Toast.makeText(context, getApplicationContext().getResources().getString(R.string.sucesso_enviar_codigoSenha) + ": " + codigogerado, Toast.LENGTH_LONG).show(); //TIRAR VISUALIZAÇÃO DO CODIGO

            } else {
                Toast.makeText(context, getApplicationContext().getResources().getString(R.string.erro_gerar_codigo), Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean IsValido(){
        return true;
    }

}
