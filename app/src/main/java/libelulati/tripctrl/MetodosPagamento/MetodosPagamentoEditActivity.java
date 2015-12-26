package libelulati.tripctrl.MetodosPagamento;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Strings.MensagensUsuario;
import libelulati.tripctrl.Viagens.ViagensDAO;


public class MetodosPagamentoEditActivity extends AppCompatActivity {

    private Spinner spinnerTipoPagamento, spinnerViagem;
    private List<String>  listaTipoPagamento, listaViagem;
    MetodosPagamentoDAO data;
    private EditText ed_mp_viagem, ed_mp_tipoPagamento;
    int usuario = InicioActivity.getId_uslogado();
    boolean validar, valido;
    Context context;
    EditText mp_detalhe, mp_valor, mp_vencimento;
    private int id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_pagamento_edit);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





            spinnerViagem = (Spinner) findViewById(R.id.sp_mp_viagem);
            spinnerTipoPagamento = (Spinner) findViewById(R.id.sp_mp_tipopagamento);
            mp_detalhe = (EditText) findViewById(R.id.ed_mp_detalhe);
            mp_valor = (EditText) findViewById(R.id.ed_mp_Valor);
            mp_vencimento = (EditText) findViewById(R.id.ed_mp_vencimento);

            spinnerViagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        valido = false;
                        ed_mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    } else {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(spinnerViagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        verificaViagem();
                    }
                }
            });


            mp_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        valido = false;
                        mp_valor.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    } else {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mp_valor.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        verificarvalor();
                    }
                }
            });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menu_global, menu);
            return (true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id){
                case R.id.mn_gb_salvar:
                    salvar();
                case android.R.id.home:
                    if(getActionBar() == null){
                        onBackPressed();
                    }
                    else{
                        NavUtils.navigateUpFromSameTask(this);
                    }
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        public void salvar(){
            MetodosPagamento metodosPagamento = new MetodosPagamento();

            metodosPagamento.setUs_id(usuario);
            metodosPagamento.setDv_id((int) spinnerViagem.getAdapter().getItemId(id));
            metodosPagamento.setTp_id((int) spinnerTipoPagamento.getAdapter().getItemId(id));
            metodosPagamento.setMe_detalhes(mp_detalhe.getText().toString());
            metodosPagamento.setMe_valor(Integer.parseInt(mp_valor.getText().toString()));
            metodosPagamento.setMe_vencimento(mp_vencimento.getText().toString());

            if(valido){
                boolean sucesso = new MetodosPagamentoDAO(context).criar(metodosPagamento);

                if (sucesso) {
                    Toast.makeText(context, MensagensUsuario.getVIAGEM() + MensagensUsuario.getCriado_sucesso(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, MensagensUsuario.getErro_criar() + MensagensUsuario.getMETODOPAGAMENTO(), Toast.LENGTH_LONG).show();
                }

                finish();
            }
            else{
                Toast.makeText(context, "Há campos inválidos. O registro não será gravado;", Toast.LENGTH_SHORT).show();
            }


        }

        public void verificaViagem(){
            validar = Validar.ValidarNome(String.valueOf(spinnerViagem.getAdapter().getItem(id)));
            if(!validar){
                ed_mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                Toast.makeText(context, "Nome da viagem inválido", Toast.LENGTH_SHORT).show();
                valido = false;
            }
            else{
                ed_mp_viagem.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                valido = true;
            }
        }


        public void verificarvalor(){
            double valor = 0;
            valor = Double.parseDouble(mp_valor.getText().toString());
            if(valor == 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                    Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();
                    valido = false;
                }
                else{
                    mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    valido = true;
                }
            }
            else{
                mp_valor.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                Toast.makeText(context, "Valor inválido", Toast.LENGTH_SHORT).show();
            }
        }

}
