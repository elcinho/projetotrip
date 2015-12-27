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

public class ViagensShowActivity extends AppCompatActivity {

    EditText vis_nome, vis_localizacao, vis_dtinicio, vis_dtfim, vis_tipotransp, vis_transporte, vis_tipohosp, vis_hospedagem, vis_valortotal;
    FloatingActionButton fab_editar;
    MenuItem vis_salvar;
    Context context;
    int vis_id, usuario_id, vis_menu, ano, dia, mes, dataclick;
    StringBuilder dataformatada;
    boolean validar, valido;
    String titulo_show, titulo_edit;

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
        vis_dtinicio = (EditText) findViewById(R.id.ed_vis_dtinicio);
        vis_dtfim = (EditText) findViewById(R.id.ed_vis_dtfim);
        vis_tipotransp = (EditText) findViewById(R.id.ed_vis_tipotransporte);
        vis_transporte = (EditText) findViewById(R.id.ed_vis_transporte);
        vis_tipohosp = (EditText) findViewById(R.id.ed_vis_tipohospedagem);
        vis_hospedagem = (EditText) findViewById(R.id.ed_vis_hospedagem);
        vis_valortotal = (EditText) findViewById(R.id.ed_vis_valortotal);

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
        vis_tipotransp.setText(bundle.getString(StringsNomes.getTrId()));
        vis_transporte.setText(bundle.getString(StringsNomes.getViTransporte()));
        vis_tipohosp.setText(bundle.getString(StringsNomes.getHoId()));
        vis_hospedagem.setText(bundle.getString(StringsNomes.getViHospedagem()));
        vis_valortotal.setText(bundle.getString(StringsNomes.getViValortotal()));

        titulo_show = context.getResources().getString(R.string.title_activity_viagens_show) + " " + vis_nome.getText().toString();
        titulo_edit = context.getResources().getString(R.string.title_activity_viagens_edit) + " " + vis_nome.getText().toString();

        getSupportActionBar().setTitle(titulo_show);

        vis_dtinicio.setInputType(InputType.TYPE_NULL);
        vis_dtfim.setInputType(InputType.TYPE_NULL);

        vis_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    vis_nome.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
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
                    valido = false;
                    vis_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    verificardtini();
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtinicio.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        vis_dtfim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    valido = false;
                    vis_dtfim.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificardtfim();
                }
            }
        });

        vis_valortotal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    valido = false;
                    vis_valortotal.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(vis_valortotal.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    verificarvalor();
                }
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
                vis_tipotransp.setEnabled(true);
                vis_transporte.setEnabled(true);
                vis_tipohosp.setEnabled(true);
                vis_hospedagem.setEnabled(true);
                vis_valortotal.setEnabled(true);

                vis_nome.requestFocus();

                fab_editar.setVisibility(View.INVISIBLE);
                vis_menu = 2;
                invalidateOptionsMenu();
            }
        });
    }

    public void salvar(){
        usuario_id = InicioActivity.getId_uslogado();
        Viagens viagens = new Viagens();

        viagens.setUs_id(usuario_id);
        viagens.setVi_nome(vis_nome.getText().toString());
        viagens.setVi_local(vis_localizacao.getText().toString());
        viagens.setVi_dtini(vis_dtinicio.getText().toString());
        viagens.setVi_dtfim(vis_dtfim.getText().toString());
        viagens.setTr_id(vis_tipotransp.getText().toString());
        viagens.setVi_transporte(vis_transporte.getText().toString());
        viagens.setHo_id(vis_tipohosp.getText().toString());
        viagens.setVi_hospedagem(vis_hospedagem.getText().toString());
        viagens.setVi_valortotal(vis_valortotal.getText().toString());

        boolean sucesso = new ViagensDAO(context).atualizar(viagens, vis_id);

        if (sucesso) {
            Toast.makeText(context, context.getResources().getString(R.string.viagem) + " " + viagens.getVi_nome() + " " + context.getResources().getString(R.string.sucesso_editado) + ".", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.erro_editar) + " " + context.getResources().getString(R.string.viagem) + " " + viagens.getVi_nome(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_global, menu);
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
            vis_nome.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, context.getResources().getString(R.string.nome) + " " + context.getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        }
        else{
            vis_nome.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
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
        if(!validar){
            vis_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, context.getResources().getString(R.string.datainicio) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        }
        else{
            vis_dtinicio.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
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
            vis_dtfim.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.alert_icon, 0);
            Toast.makeText(context, context.getResources().getString(R.string.datafim) + ".", Toast.LENGTH_LONG).show();
            valido = false;
        }
        else{
            vis_dtfim.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            valido = true;
        }
    }

    public void verificarvalor(){
        double valor = 0;
        if(vis_valortotal.length() > 1){
            valor = Double.parseDouble(vis_valortotal.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    vis_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
                    Toast.makeText(context, context.getResources().getString(R.string.valortotal) + " " + context.getResources().getString(R.string.invalido) + ".", Toast.LENGTH_LONG).show();
                    valido = false;
                }
                else{
                    vis_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    valido = true;
                }
            }
        }
        else{
            vis_valortotal.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.alert_icon,0);
            Toast.makeText(context, context.getResources().getString(R.string.valortotal) + " " + context.getResources().getString(R.string.nao_vazio) + ".", Toast.LENGTH_LONG).show();
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
