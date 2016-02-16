package libelulati.tripctrl.Inicio;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Gastos.GastosListActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagem_New;
import libelulati.tripctrl.Viagens.Viagens_DAO;

public class InicioActivity extends AppCompatActivity {
    static int id_usuario = 0;
    List<Viagem> viagens;
    Viagem viagem;
    Button bt_ini_addviagem;
    TextView tx_ini_dataviagem, tx_ini_valorviagem;
    Context context;
    String titulo;
    int id_viagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        id_usuario = 1; // BUSCAR ID DO USUARIO LOGADO

        context = InicioActivity.this;

        // MENU FLUTUANTE
        int floatActionButtonSize = getResources().getDimensionPixelSize(R.dimen.float_action_button_size);
        int floatActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.float_action_button_margin);
        int floatActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.float_action_button_content_size);
        int floatActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.float_action_button_content_margin);
        int subActionButtonSize = getResources().getDimensionPixelSize(R.dimen.sub_action_button_size);
        int subActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.sub_action_button_margin);
        int subActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.sub_action_button_content_size);
        int subActionButtonContentMargin = getResources().getDimensionPixelOffset(R.dimen.sub_action_button_content_margin);


        final ImageView fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));
        fabIconNew.setColorFilter(getResources().getColor(R.color.colorWhite));

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams newParams = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams(floatActionButtonSize, floatActionButtonSize);
        newParams.setMargins(floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin);

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams newIconParams = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams(floatActionButtonContentSize, floatActionButtonContentSize);
        newIconParams.setMargins(floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin);

        final com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton fab_novo = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(fabIconNew, newIconParams)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.bk_floatactionbutton))
                .setLayoutParams(newParams)
                .build();

        FrameLayout.LayoutParams subNewParams = new FrameLayout.LayoutParams(subActionButtonSize, subActionButtonSize);
        subNewParams.setMargins(subActionButtonMargin, subActionButtonMargin, subActionButtonMargin, subActionButtonMargin);

        SubActionButton.LayoutParams newItemParams = new SubActionButton.LayoutParams(subActionButtonContentSize, subActionButtonContentSize);
        newItemParams.setMargins(subActionButtonContentMargin, subActionButtonContentMargin, subActionButtonContentMargin, subActionButtonContentMargin);

        SubActionButton.Builder itensMenu = new SubActionButton.Builder(this);
        itensMenu.setLayoutParams(subNewParams);

        ImageView itemGasto = new ImageView(this);
        ImageView itemPlanejamento = new ImageView(this);
        ImageView itemPagamento = new ImageView(this);
        ImageView itemConfiguracoes = new ImageView(this);

        itemGasto.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_attach_money));
        itemGasto.setColorFilter(getResources().getColor(R.color.colorPrimary));
        itemPlanejamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_assignment));
        itemPlanejamento.setColorFilter(getResources().getColor(R.color.colorPrimary));
        itemPagamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_payment));
        itemPagamento.setColorFilter(getResources().getColor(R.color.colorPrimary));
        itemConfiguracoes.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_settings));
        itemConfiguracoes.setColorFilter(getResources().getColor(R.color.colorPrimary));

        final FloatingActionMenu menuPrincipal = new FloatingActionMenu.Builder(this)
                .addSubActionView(itensMenu.setContentView(itemConfiguracoes).build())
                .addSubActionView(itensMenu.setContentView(itemPagamento).build())
                .addSubActionView(itensMenu.setContentView(itemPlanejamento).build())
                .addSubActionView(itensMenu.setContentView(itemGasto).build())
                .setRadius(getResources().getDimensionPixelSize(R.dimen.float_menu_radius))
                .attachTo(fab_novo).build();

        menuPrincipal.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                fabIconNew.setRotation(0);
                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, propertyValuesHolder);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                fabIconNew.setRotation(getResources().getDimensionPixelSize(R.dimen.float_menu_angulo));
                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, propertyValuesHolder);
                animator.start();
            }
        });

        itemGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListGasto();
            }
        });

        // FIM MENU FLUTUANTE

        bt_ini_addviagem = (Button) findViewById(R.id.bt_ini_addviagem);
        tx_ini_dataviagem = (TextView)findViewById(R.id.tx_ini_dataviagem);
        tx_ini_valorviagem = (TextView)findViewById(R.id.tx_ini_valorviagem);
        bt_ini_addviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibirViagemNew();
            }
        });

    }

    @Override
    public void onResume() {
        Iniciar();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.inicio_menu, menu);
        return (true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.mn_ini_novoviagem:
                ExibirViagemNew();
                break;
        }
            return true;
    }

    public void ExibirViagemNew(){
        DialogFragment viagemnew = new Viagem_New();
        viagemnew.show(getSupportFragmentManager(), "viagemnew");
    }

    public void Iniciar(){
        viagens = new Viagens_DAO(context).listar(id_usuario);
        if(viagens.size() > 0){
            bt_ini_addviagem.setVisibility(View.INVISIBLE);
            tx_ini_dataviagem.setVisibility(View.VISIBLE);
            tx_ini_valorviagem.setVisibility(View.VISIBLE);
            id_viagem = viagens.size();
            viagem = new Viagens_DAO(context).buscarID(id_viagem);
            titulo = viagem.getVi_nome();
            getSupportActionBar().setTitle(titulo);
            tx_ini_dataviagem.setText(context.getResources().getString(R.string.periodo
            ) + " " + viagem.getVi_dtinic() + " " + context.getResources().getString(R.string.a) + " " + viagem.getVi_dtfim());
            tx_ini_valorviagem.setText(context.getResources().getString(R.string.valordisponivel)+ " " + context.getResources().getString(R.string.moeda) + " " + viagem.getVi_valor());
        }
        else{
            bt_ini_addviagem.setVisibility(View.VISIBLE);
            tx_ini_dataviagem.setVisibility(View.INVISIBLE);
            tx_ini_valorviagem.setVisibility(View.INVISIBLE);
        }
    }

    public void ChamarListGasto(){
        Intent it_gastos = new Intent(context, GastosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_gastos.putExtras(bundle);

        startActivityForResult(it_gastos, 1);
    }

    public static int getId_usuario() {
        return id_usuario;
    }

}
