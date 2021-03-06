package libelulati.tripctrl.Inicio;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


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


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import libelulati.tripctrl.Configuracoes.ConfiguracoesListActivity;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Gastos.GastosListActivity;
import libelulati.tripctrl.Notificacoes.Cnotificacoes;
import libelulati.tripctrl.Notificacoes.Notificacoes;
import libelulati.tripctrl.Notificacoes.NotificarBroadcast;
import libelulati.tripctrl.Pagamentos.PagamentosListActivity;
import libelulati.tripctrl.Planejamentos.PlanejamentosListActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Relatorios.RelatoriosListActivity;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagem_New;
import libelulati.tripctrl.Viagens.Viagens_DAO;

public class InicioActivity extends AppCompatActivity {
    static int id_usuario = 0;
    List<Viagem> viagens;
    Viagem viagem;
    Cnotificacoes cnotificacoes;
    Button bt_ini_addviagem;
    TextView tx_ini_dataviagem, tx_ini_valorviagem;
    Context context;
    String titulo;
    static int id_viagem;
    int exibir_menu ,cn_pagamento;
    View v_inicio, v_linha;
    ImageView fabIconNew;
    PieChart gr_ini_inicio;
    float val_viagem, val_planejamento, val_gasto;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    ImageView itemGasto, itemPlanejamento, itemPagamento, itemRelatorio, itemConfiguracoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        id_usuario = 1; // BUSCAR ID DO USUARIO LOGADO
        v_inicio = findViewById(R.id.rl_inicio);
        context = InicioActivity.this;
        v_linha = findViewById(R.id.vw_ini_linha01);
        gr_ini_inicio = (PieChart)findViewById(R.id.gr_ini_inicio);

        // MENU FLUTUANTE
        int floatActionButtonSize = getResources().getDimensionPixelSize(R.dimen.float_action_button_size);
        int floatActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.float_action_button_margin);
        int floatActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.float_action_button_content_size);
        int floatActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.float_action_button_content_margin);
        int subActionButtonSize = getResources().getDimensionPixelSize(R.dimen.sub_action_button_size);
        int subActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.sub_action_button_margin);
        int subActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.sub_action_button_content_size);
        int subActionButtonContentMargin = getResources().getDimensionPixelOffset(R.dimen.sub_action_button_content_margin);

        fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams newParams = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams(floatActionButtonSize, floatActionButtonSize);
        newParams.setMargins(floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin);

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams newIconParams = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.LayoutParams(floatActionButtonContentSize, floatActionButtonContentSize);
        newIconParams.setMargins(floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin);

        final com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton fab_novo = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(this)
                .setContentView(fabIconNew, newIconParams)
                .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_pink))
                .setLayoutParams(newParams)
                .build();

        FrameLayout.LayoutParams subNewParams = new FrameLayout.LayoutParams(subActionButtonSize, subActionButtonSize);
        subNewParams.setMargins(subActionButtonMargin, subActionButtonMargin, subActionButtonMargin, subActionButtonMargin);

        SubActionButton.LayoutParams newItemParams = new SubActionButton.LayoutParams(subActionButtonContentSize, subActionButtonContentSize);
        newItemParams.setMargins(subActionButtonContentMargin, subActionButtonContentMargin, subActionButtonContentMargin, subActionButtonContentMargin);

        SubActionButton.Builder itensMenu = new SubActionButton.Builder(this);
        itensMenu.setLayoutParams(subNewParams);

        itemGasto = new ImageView(this);
        itemPlanejamento = new ImageView(this);
        itemPagamento = new ImageView(this);
        itemRelatorio = new ImageView(this);
        itemConfiguracoes = new ImageView(this);

        itemGasto.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_attach_money));
        itemPlanejamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_assignment));
        itemPagamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_payment));
        itemRelatorio.setImageDrawable(getResources().getDrawable(R.drawable.ic_insert_chart_black_24dp));
        itemConfiguracoes.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_settings));

        final FloatingActionMenu menuPrincipal = new FloatingActionMenu.Builder(this)
                .addSubActionView(itensMenu.setContentView(itemConfiguracoes)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_blue))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemRelatorio)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_yellow))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemPagamento)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_violet))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemPlanejamento)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_green))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemGasto)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_orange))
                        .build())
                .setRadius(getResources().getDimensionPixelSize(R.dimen.float_menu_radius))
                .attachTo(fab_novo).build();

        menuPrincipal.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                gr_ini_inicio.setVisibility(View.INVISIBLE);
                v_inicio.setBackgroundColor(context.getResources().getColor(R.color.colorBlack));
                fabIconNew.setRotation(0);
                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, propertyValuesHolder);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                gr_ini_inicio.setVisibility(View.VISIBLE);
                v_inicio.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                fabIconNew.setRotation(getResources().getDimensionPixelSize(R.dimen.float_menu_angulo));
                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, propertyValuesHolder);
                animator.start();
            }
        });

        v_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuPrincipal.close(true);
            }
        });

        itemGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListGasto();
                menuPrincipal.close(true);
            }
        });

        itemPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListPagamento();
                menuPrincipal.close(true);
            }
        });
        itemPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListPlanejamento();
                menuPrincipal.close(true);
            }
        });

        itemRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListRelatorios();
                menuPrincipal.close(true);
            }
        });

        itemConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListConfiguracoes();
                menuPrincipal.close(true);
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


       // ExibirNotificaçãoExterna();
        ExibirNotificacao();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(R.drawable.trip_icon);
    }



    @Override
    public void onResume() {
        Iniciar();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if(exibir_menu == 1)
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

    //Notificações
  public void ExibirNotificaçãoExterna(){
      cn_pagamento = cnotificacoes.getCn_pagamentos();
      if(cn_pagamento == 1) {
          Calendar calendar = Calendar.getInstance();
          calendar.setTimeInMillis(System.currentTimeMillis());
          calendar.add(Calendar.SECOND, 3);

          Intent myIntent = new Intent(InicioActivity.this, NotificarBroadcast.class);
          PendingIntent pendingIntent = PendingIntent.getBroadcast(InicioActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

          AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
          alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
      }
  }

    public void ExibirNotificacao() {

        Notificacoes notificacoes = new Notificacoes();

        if (notificacoes != null) {
            notificacoes.NotificarGasto50();
        }

        if(notificacoes != null) {
            notificacoes.NotificarGasto90();
        }

        if(notificacoes != null) {
            notificacoes.NotificarPlanejado50();
        }

        if (notificacoes != null) {
            notificacoes.NotificarPlanejado90();
        }
    }

   //Fim Notificações

    public void Iniciar(){
        viagens = new Viagens_DAO(context).listar(id_usuario);
        if(viagens.size() > 0){
            bt_ini_addviagem.setVisibility(View.INVISIBLE);
            tx_ini_dataviagem.setVisibility(View.VISIBLE);
            tx_ini_valorviagem.setVisibility(View.VISIBLE);
            itemGasto.setEnabled(true);
            itemPlanejamento.setEnabled(true);
            itemPagamento.setEnabled(true);
            itemRelatorio.setEnabled(true);
            id_viagem = viagens.size();
            viagem = new Viagens_DAO(context).buscarID(id_viagem);
            titulo = viagem.getVi_nome();
            getSupportActionBar().setTitle(titulo);
            tx_ini_dataviagem.setText(context.getResources().getString(R.string.periodo
            ) + " " + viagem.getVi_dtinic() + " " + context.getResources().getString(R.string.a) + " " + viagem.getVi_dtfim());
            tx_ini_valorviagem.setText(context.getResources().getString(R.string.valordisponivel) + " " + context.getResources().getString(R.string.moeda) + " " + viagem.getVi_valor());
            exibir_menu = 1;
            AtualizarTotais();
        }
        else{
            bt_ini_addviagem.setVisibility(View.VISIBLE);
            itemGasto.setEnabled(false);
            itemPlanejamento.setEnabled(false);
            itemPagamento.setEnabled(false);
            itemRelatorio.setEnabled(false);
            v_linha.setVisibility(View.INVISIBLE);
            tx_ini_dataviagem.setVisibility(View.INVISIBLE);
            tx_ini_valorviagem.setVisibility(View.INVISIBLE);
            exibir_menu = 0;
        }
        RecuperarTotais();
        MontarGrafico();
    }

    public void AtualizarTotais(){
        Totais totalviagem = new Totais_DAO(context).buscarNome("viagem", id_viagem);
        if(totalviagem == null){
            Totais_DAO totais_dao = new Totais_DAO(context);
            Totais totais = new Totais();
            totais.setUs_id(id_usuario);
            totais.setVi_id(id_viagem);
            totais.setTo_nome("viagem");
            totais.setTo_total(viagem.getVi_valor());
            totais.setTo_gasto(null);
            totais.setTo_planejamento(null);
            totais_dao.criar(totais);
        }
        else{
            Totais_DAO totais_dao = new Totais_DAO(context);
            totalviagem.setTo_total(viagem.getVi_valor());
            totais_dao.atualizar(totalviagem, "viagem");
        }
    }

    public void RecuperarTotais(){
        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais totalviagem = totais_dao.buscarNome("viagem", id_viagem);
        Totais totalgasto = totais_dao.buscarNome("gasto", id_viagem);
        Totais totalplanejamento = totais_dao.buscarNome("planejamento", id_viagem);

        if(totalviagem == null){
            val_viagem = 0;
        }
        else{
            val_viagem = Float.parseFloat(totalviagem.getTo_total());
        }

        if(totalgasto == null){
            val_gasto = 0;
        }
        else{
            val_gasto = Float.parseFloat(totalgasto.getTo_total());
        }

        if(totalplanejamento == null){
            val_planejamento = 0;
        }
        else{
            val_planejamento = Float.parseFloat(totalplanejamento.getTo_total());
        }
    }

    public void ChamarListGasto(){
        Intent it_gastos = new Intent(context, GastosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_gastos.putExtras(bundle);

        startActivityForResult(it_gastos, 1);
        finish();
    }

    public void ChamarListPagamento(){
        Intent it_pagamento = new Intent(context, PagamentosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_pagamento.putExtras(bundle);

        startActivityForResult(it_pagamento, 1);
        finish();
    }

    public void ChamarListPlanejamento(){
        Intent it_planejamento = new Intent(context, PlanejamentosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_planejamento.putExtras(bundle);

        startActivityForResult(it_planejamento, 1);
        finish();
    }

    public void ChamarListConfiguracoes(){
        Intent it_configuracoes = new Intent(context, ConfiguracoesListActivity.class);
        startActivity(it_configuracoes);
        finish();
    }

    public void ChamarListRelatorios(){
        Intent it_relatorios = new Intent(context, RelatoriosListActivity.class);
        startActivity(it_relatorios);
        finish();
    }

    //Gráfico
    public void MontarGrafico(){
        float pc_planejamento, pc_gastos;
        pc_gastos = (val_gasto * 100)/val_planejamento;
        pc_planejamento = 100 - pc_gastos;

        ArrayList<Entry> entries = new ArrayList<>();
        if(val_viagem == 0 && val_gasto == 0 && val_planejamento == 0){
            gr_ini_inicio.setVisibility(View.INVISIBLE);
        }
        else{
            if(val_gasto == 0 && val_planejamento == 0){
                entries.add(new Entry(val_viagem, 0));
            }
            else {
                if(val_gasto == 0){
                    entries.add(new Entry(val_planejamento, 0));
                }
                else {
                    if(val_planejamento == 0){
                        entries.add(new Entry(val_gasto, 0));
                    }
                    else{
                        entries.add(new Entry(val_gasto, 0));
                        entries.add(new Entry(val_planejamento - val_gasto, 1));
                    }
                }
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<String>();
        if(val_viagem == 0 && val_gasto == 0 && val_planejamento == 0){
            gr_ini_inicio.setVisibility(View.INVISIBLE);
        }
        else{
            if(val_gasto == 0 && val_planejamento == 0){
                labels.add(context.getResources().getString(R.string.viagem));
            }
            else {
                if(val_gasto == 0){
                    labels.add(context.getResources().getString(R.string.planejamento));
                }
                else {
                    if(val_planejamento == 0){
                        labels.add(context.getResources().getString(R.string.gasto));
                    }
                    else{
                        labels.add(context.getResources().getString(R.string.gasto) + " - " + format(pc_gastos) + "%");
                        labels.add(context.getResources().getString(R.string.planejado) + " - " + format(pc_planejamento) + "%");
                    }
                }
            }
        }

        PieData data = new PieData(labels, dataSet);
        gr_ini_inicio.setData(data);
        gr_ini_inicio.setBackgroundColor(Color.TRANSPARENT);

        ArrayList<Integer> cores = new ArrayList<Integer>();
        if(cores.size() == 0){
            cores.add(context.getResources().getColor(R.color.colorRed));
            cores.add(context.getResources().getColor(R.color.colorGreen));
        }

        dataSet.setColors(cores);
    }

    public static int getId_usuario() {
        return id_usuario;
    }

    public static int getId_viagem() {
        return id_viagem;
    }

    public static String format(float x){
        return String.format("%.2f", x);
    }

}
