package libelulati.tripctrl.Planejamentos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class PlanejamentoEditActivity extends AppCompatActivity {
    int novo = 0;
    int vis_menu = 0;
    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem, id_planejamento;
    EditText ed_ple_categoria, ed_ple_valor;
    Spinner sp_ple_categoria;
    Context context;
    List<String> listarcategorias;
    MeuSpinner meuSpinner = new MeuSpinner();
    boolean v_categoria, v_valor;
    Validar validar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento_edit);

        context = PlanejamentoEditActivity.this;
        validar = new Validar(context);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed_ple_categoria = (EditText) findViewById(R.id.ed_ple_categoria);
        ed_ple_valor = (EditText) findViewById(R.id.ed_ple_valor);
        sp_ple_categoria = (Spinner)findViewById(R.id.sp_ple_categoria);

        Intent it_pl_novo = getIntent();
        Bundle bundle = it_pl_novo.getExtras();

        novo = bundle.getInt("novo");
        id_planejamento = bundle.getInt(Nomes.getID());
        id_viagem = bundle.getInt(Nomes.getViId());
        ed_ple_categoria.setText(bundle.getString(Nomes.getCaId()));
        ed_ple_valor.setText(bundle.getString(Nomes.getPlValorcat()));

        listarcategorias = new Planejamento_DAO(context).sp_categorias(id_usuario);

        switch (novo){
            case 1:
                PlanejamentoNovo();
                break;
            case 2:
                PlanejamentoShow();
                break;
        }
    }

    public void Salvar(){
        Planejamento planejamento = new Planejamento();
        planejamento.setUs_id(id_usuario);
        planejamento.setVi_id(id_viagem);
        planejamento.setCa_id(ed_ple_categoria.getText().toString());
        planejamento.setPl_valor(ed_ple_valor.getText().toString());
        if(IsValido()){
            boolean sucesso = new Planejamento_DAO(context).criar(planejamento);
            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_criar_planejamento), Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, context.getResources().getString(R.string.erro_criar_planejamento), Toast.LENGTH_LONG).show();
            }
            finish();
        }
        else{
            Toast.makeText(context,context.getResources().getString(R.string.erro_validar_campos),Toast.LENGTH_LONG).show();
        }
    }

    public void PlanejamentoNovo(){
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_planejamentos_new));

        vis_menu = 1;
        invalidateOptionsMenu();

        meuSpinner.preencherSpinner(context, listarcategorias, sp_ple_categoria);
        meuSpinner.selecionarItem(sp_ple_categoria, ed_ple_categoria);
        ed_ple_categoria.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_ple_categoria.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        final List<Planejamento> planejamentos = new Planejamento_DAO(context).listar(id_viagem);
        ed_ple_categoria.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                }
            }
        });

        ed_ple_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    v_valor = validar.ValidarValor(ed_ple_valor.getText().toString(), ed_ple_valor);
                }
            }
        });
    }

    public void PlanejamentoShow(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_ple_categoria.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 2;
        invalidateOptionsMenu();

        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_planejamento_edit) + " " + ed_ple_categoria.getText().toString());

        ed_ple_categoria.setVisibility(View.VISIBLE);
        sp_ple_categoria.setVisibility(View.INVISIBLE);

        ed_ple_categoria.setEnabled(false);
        ed_ple_valor.setEnabled(false);

        ed_ple_categoria.setTextColor(context.getResources().getColor(R.color.colorBlack));
        ed_ple_valor.setTextColor(context.getResources().getColor(R.color.colorBlack));
    }

    public void PlanejamentoEditar(){
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(ed_ple_categoria.getWindowToken(), 0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        vis_menu = 3;
        invalidateOptionsMenu();

        sp_ple_categoria.setVisibility(View.VISIBLE);

        ed_ple_categoria.setEnabled(true);
        ed_ple_valor.setEnabled(true);

        ed_ple_categoria.setTextColor(context.getResources().getColor(R.color.colorWhite));

        meuSpinner.preencherSpinner(context, listarcategorias, sp_ple_categoria);
        meuSpinner.posicaoSelecionada(sp_ple_categoria, ed_ple_categoria.getText().toString());
        meuSpinner.selecionarItem(sp_ple_categoria, ed_ple_categoria);
    }

    public void Atualizar(){
        Planejamento planejamento = new Planejamento();
        planejamento.setUs_id(id_usuario);
        planejamento.setVi_id(id_viagem);
        planejamento.setCa_id(ed_ple_categoria.getText().toString());
        planejamento.setPl_valor(ed_ple_valor.getText().toString());

        if(IsValido()){
            boolean sucesso = new Planejamento_DAO(context).atualizar(planejamento, id_planejamento);

            if(sucesso){
                Toast.makeText(context, context.getResources().getString(R.string.sucesso_alterar_planejamento), Toast.LENGTH_LONG).show();
                finish();
            }
            else {
                Toast.makeText(context, context.getResources().getString(R.string.erro_alterar_planejamento), Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else{
            Toast.makeText(context, context.getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
        }
    }

    public void Deletar(int id){
        final int del_id = id;

        AlertDialog confirme;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.opcao_confirmar));
        builder.setMessage(context.getResources().getString(R.string.excluir_registro));

        builder.setPositiveButton(context.getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Planejamento_DAO planejamento_dao = new Planejamento_DAO(context);
                boolean sucesso = planejamento_dao.deletar(del_id);
                if (sucesso) {
                    Toast.makeText(context, context.getResources().getString(R.string.sucesso_excluir_gasto), Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.erro_excluir_gasto), Toast.LENGTH_LONG).show();
                    finish();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(context.getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        confirme = builder.create();
        confirme.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        switch (vis_menu) {
            case 1:
                getMenuInflater().inflate(R.menu.salvar_menu, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.edit_delet_menu, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.atualizar_menu, menu);
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
            case R.id.mn_gb_editar:
                PlanejamentoEditar();
                break;
            case R.id.mn_gb_deletar:
                Deletar(id_planejamento);
                break;
            case R.id.mn_gb_atualizar:
                Atualizar();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public boolean IsValido(){
        final List<Planejamento> planejamentos = new Planejamento_DAO(context).listar(id_viagem);
        v_categoria = validar.ValidarCategoria(planejamentos, ed_ple_categoria.getText().toString(), ed_ple_categoria);
        v_valor = validar.ValidarValor(ed_ple_valor.getText().toString(), ed_ple_valor);

        if(v_categoria && v_valor){
            return true;
        }
        else{
            return false;
        }
    }
}
