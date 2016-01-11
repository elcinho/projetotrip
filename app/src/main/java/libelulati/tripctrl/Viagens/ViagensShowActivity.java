package libelulati.tripctrl.Viagens;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class ViagensShowActivity extends AppCompatActivity {

    int usuario = InicioActivity.getId_uslogado();
    int ano, mes, dia, dataclick, vis_id, vis_menu;
    String titulo_show, titulo_edit;
    StringBuilder dataformatada;
    boolean validar, v_nome, v_dtinicio, v_dtfim, v_valor, v_transporte, v_hospedagem;
    Context context;
    MenuItem vis_salvar;
    EditText vis_nome, vis_localizacao, vis_dtinicio, vis_dtfim, vis_tipotransporte, vis_transporte, vis_tipohospedagem, vis_hospedagem, vis_valortotal;
    Spinner vis_sp_tptransporte, vis_sp_tphospedagem;
    FloatingActionButton fab_editar;
    List<String> listatipotransporte, listatipohospedagem;
    MeuSpinner meuSpinner = new MeuSpinner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = ViagensShowActivity.this;
        vis_menu = 1;
        invalidateOptionsMenu();

        fab_editar = (FloatingActionButton) findViewById(R.id.fab_vis_editar);
        vis_nome = (EditText) findViewById(R.id.ed_vis_nome);
        vis_localizacao = (EditText) findViewById(R.id.ed_vis_localizacao);
        vis_dtinicio = (EditText) findViewById(R.id.ed_vis_inicio);
        vis_dtfim = (EditText) findViewById(R.id.ed_vis_fim);
        vis_tipotransporte = (EditText) findViewById(R.id.ed_vis_tipotransporte);
        vis_transporte = (EditText) findViewById(R.id.ed_vis_transporte);
        vis_tipohospedagem = (EditText) findViewById(R.id.ed_vis_tipohospedagem);
        vis_hospedagem = (EditText) findViewById(R.id.ed_vis_hospedagem);
        vis_valortotal = (EditText) findViewById(R.id.ed_vis_valortotal);
        vis_sp_tptransporte = (Spinner) findViewById(R.id.sp_vis_tptransporte);
        vis_sp_tphospedagem = (Spinner) findViewById(R.id.sp_vis_tphospedagem);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(vis_nome.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent it_vis_show = getIntent();
        Bundle bundle = it_vis_show.getExtras();

        vis_id = bundle.getInt(StringsNomes.getID());
        vis_nome.setText(bundle.getString(StringsNomes.getViNome()));
        vis_localizacao.setText(bundle.getString(StringsNomes.getViLocal()));
        vis_dtinicio.setText(bundle.getString(StringsNomes.getViDtini()));
        vis_dtfim.setText(bundle.getString(StringsNomes.getViDtfim()));
        vis_tipotransporte.setText(bundle.getString(StringsNomes.getTrId()));
        vis_transporte.setText(bundle.getString(StringsNomes.getViTransporte()));
        vis_tipohospedagem.setText(bundle.getString(StringsNomes.getHoId()));
        vis_hospedagem.setText(bundle.getString(StringsNomes.getViHospedagem()));
        vis_valortotal.setText(bundle.getString(StringsNomes.getViValortotal()));

        titulo_show = context.getResources().getString(R.string.title_activity_viagens_show) + " " + vis_nome.getText().toString();
        titulo_edit = context.getResources().getString(R.string.title_activity_viagens_edit) + " " + vis_nome.getText().toString();

        getSupportActionBar().setTitle(titulo_show);

        listatipotransporte = new ViagensDAO(context).sp_tipostransporte();
        listatipohospedagem = new ViagensDAO(context).sp_tiposhospedagem();

        vis_dtinicio.setInputType(InputType.TYPE_NULL);
        vis_dtfim.setInputType(InputType.TYPE_NULL);
        vis_tipotransporte.setInputType(InputType.TYPE_NULL);
        vis_tipohospedagem.setInputType(InputType.TYPE_NULL);

        vis_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_nome.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarnome();
                }
            }
        });

        vis_dtinicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtinicio.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    verificardtini();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtinicio.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        vis_dtfim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                else{
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardtfim();
                }
            }
        });

        vis_valortotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_valortotal.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarvalor();
                }
            }
        });

        vis_transporte.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_transporte.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificartransporte(vis_transporte.getText().toString());
                }
            }
        });

        vis_hospedagem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_hospedagem.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                verificarhospedagem(vis_hospedagem.getText().toString());
            }
        });

        fab_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportActionBar().setTitle(titulo_edit);

                vis_nome.setEnabled(true);
                vis_localizacao.setEnabled(true);
                vis_dtinicio.setEnabled(true);
                vis_dtfim.setEnabled(true);
                vis_tipotransporte.setEnabled(true);
                vis_transporte.setEnabled(true);
                vis_tipohospedagem.setEnabled(true);
                vis_hospedagem.setEnabled(true);
                vis_valortotal.setEnabled(true);
                vis_sp_tptransporte.setVisibility(View.VISIBLE);
                vis_sp_tphospedagem.setVisibility(View.VISIBLE);

                vis_tipotransporte.setTextColor(getResources().getColor(R.color.colorBranco));
                vis_tipohospedagem.setTextColor(getResources().getColor(R.color.colorBranco));

                meuSpinner.preencherSpinner(context, listatipotransporte, vis_sp_tptransporte);
                meuSpinner.posicaoSelecionada(vis_sp_tptransporte, vis_tipotransporte.getText().toString());
                meuSpinner.selecionarItem(vis_sp_tptransporte, vis_tipotransporte);

                meuSpinner.preencherSpinner(context, listatipohospedagem, vis_sp_tphospedagem);
                meuSpinner.posicaoSelecionada(vis_sp_tphospedagem, vis_tipohospedagem.getText().toString());
                meuSpinner.selecionarItem(vis_sp_tphospedagem, vis_tipohospedagem);

                vis_nome.requestFocus();

                fab_editar.setVisibility(View.INVISIBLE);
                vis_menu = 2;
                invalidateOptionsMenu();
            }
        });
    }

    public void salvar(){

        Viagens viagens = new Viagens();

        viagens.setUs_id(usuario);
        viagens.setVi_nome(vis_nome.getText().toString());
        viagens.setVi_local(vis_localizacao.getText().toString());
        viagens.setVi_dtini(vis_dtinicio.getText().toString());
        viagens.setVi_dtfim(vis_dtfim.getText().toString());
        viagens.setTr_id(vis_tipotransporte.getText().toString());
        viagens.setVi_transporte(vis_transporte.getText().toString());
        viagens.setHo_id(vis_tipohospedagem.getText().toString());
        viagens.setVi_hospedagem(vis_hospedagem.getText().toString());
        viagens.setVi_valortotal(vis_valortotal.getText().toString());

        if(IsValido()){
            boolean sucesso = new ViagensDAO(context).atualizar(viagens, vis_id);

            if (sucesso) {
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_alterar_viagem), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_viagem), Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_salvar, menu);
        vis_salvar = menu.findItem(R.id.mn_gb_salvar);
            switch (vis_menu){
                case 1:
                    vis_salvar.setVisible(false);
                    break;
                case 2:
                    vis_salvar.setVisible(true);
                    break;
            }
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

    public void verificarnome(){
        validar = Validar.ValidarNome(String.valueOf(vis_nome.getText().toString()));
        if(!validar){
            vis_nome.setError(context.getResources().getString(R.string.validar_nome));
            v_nome = false;
        }
        else{
            v_nome = true;
        }
    }

    public void verificardtini(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date data = sdf.parse(String.valueOf(vis_dtinicio.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar) {
            vis_dtinicio.setError(context.getResources().getString(R.string.validar_dtinicio));
            v_dtinicio = false;
        }
        else{
            v_dtinicio = true;
        }
    }

    public void verificardtfim(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inicio = sdf.parse(String.valueOf(vis_dtinicio.getText().toString()));
            Date fim = sdf.parse(String.valueOf(vis_dtfim.getText().toString()));
            validar = Validar.ValidarDataFim(inicio, fim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            vis_dtfim.setError(context.getResources().getString(R.string.validar_dtfim));
            v_dtfim = false;
        }
        else{
            v_dtfim = true;
        }
    }

    public void verificarvalor(){
        double valor = 0;
        if(vis_valortotal.length()>0){
            valor = Double.parseDouble(vis_valortotal.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    vis_valortotal.setError(context.getResources().getString(R.string.validar_valor));
                    v_valor = false;
                }
                else{
                    v_valor = true;
                }
            }
        }
        else{
            vis_valortotal.setError(context.getResources().getString(R.string.validar_valor));
            v_valor = false;
        }
    }

    public void verificartransporte(String transporte){
        if(transporte.length() < 1){
            vis_transporte.setError(context.getResources().getString(R.string.validar_campo_vazio));
            v_transporte = false;
        }
        else {
            v_transporte = true;
        }
    }

    public void verificarhospedagem(String hospedagem){
        if(hospedagem.length() < 1){
            vis_hospedagem.setError(context.getResources().getString(R.string.validar_campo_vazio));
            v_hospedagem = false;
        }
        else {
            v_hospedagem = true;
        }
    }

    public boolean IsValido(){
        if(v_nome && v_dtinicio && v_dtfim && v_transporte && v_hospedagem && v_valor){
            return true;
        }
        else{
            return false;
        }
    }

    public void calendario() {
        final Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void AtualizarData() {
        dataformatada = (new StringBuilder().append(dia).append("/").append(mes).append("/").append(ano));

        switch (dataclick){
            case 1:
                vis_dtinicio.setText(dataformatada);
                break;
            case 2:
                vis_dtfim.setText(dataformatada);
                break;
            default:
                dataclick = 0;
                break;
        }
    }

    public void showDatePickerDialog_vis_dtini(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
        dataclick = 1;
    }

    public void showDatePickerDialog_vis_dtfim(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datepicker");
        dataclick = 2;
    }

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