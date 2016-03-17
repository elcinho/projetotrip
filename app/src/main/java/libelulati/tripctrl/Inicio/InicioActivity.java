package libelulati.tripctrl.Inicio;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;


import libelulati.tripctrl.Categorias.Categoria;
import libelulati.tripctrl.Configuracoes.ConfiguracoesListActivity;
import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Gastos.GastosListActivity;
import libelulati.tripctrl.Notificacoes.Cnotificacoes;
import libelulati.tripctrl.Notificacoes.NotificacoesConfiguracaoActivity;
import libelulati.tripctrl.Pagamentos.PagamentosListActivity;
import libelulati.tripctrl.Planejamentos.Planejamento;
import libelulati.tripctrl.Planejamentos.Planejamento_DAO;
import libelulati.tripctrl.Planejamentos.PlanejamentosListActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagem_New;
import libelulati.tripctrl.Viagens.Viagens_DAO;

public class InicioActivity extends AppCompatActivity {
    static int id_usuario = 0;
    List<Viagem> viagens;
    List<Planejamento> planejamentos;
    //List<Categoria> categorias;
    Viagem viagem;
    Planejamento planejamento;
    Button bt_ini_addviagem;
    TextView tx_ini_dataviagem, tx_ini_valorviagem;
    Context context;
    String titulo;
    int id_viagem, exibir_menu;
    View v_inicio;
    ImageView fabIconNew;
    //boolean validar_notificacoes;
    //Cnotificacoes cnotificacoes;
    //NotificacoesConfiguracaoActivity notificacoesConfiguracaoActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        id_usuario = 1; // BUSCAR ID DO USUARIO LOGADO
        v_inicio = findViewById(R.id.rl_inicio);
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


        //Configuraçao Notificaçao


        fabIconNew = new ImageView(this);
        fabIconNew.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));

        FloatingActionButton.LayoutParams newParams = new FloatingActionButton.LayoutParams(floatActionButtonSize, floatActionButtonSize);
        newParams.setMargins(floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin, floatActionButtonMargin);

        FloatingActionButton.LayoutParams newIconParams = new FloatingActionButton.LayoutParams(floatActionButtonContentSize, floatActionButtonContentSize);
        newIconParams.setMargins(floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin, floatActionButtonContentMargin);

        final FloatingActionButton fab_novo = new FloatingActionButton.Builder(this)
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

        ImageView itemGasto = new ImageView(this);
        ImageView itemPlanejamento = new ImageView(this);
        ImageView itemPagamento = new ImageView(this);
        ImageView itemConfiguracoes = new ImageView(this);

        itemGasto.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_attach_money));
        itemPlanejamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_assignment));
        itemPagamento.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_payment));
        itemConfiguracoes.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_settings));

        final FloatingActionMenu menuPrincipal = new FloatingActionMenu.Builder(this)
                .addSubActionView(itensMenu.setContentView(itemConfiguracoes)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_blue))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemPagamento)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_orange))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemPlanejamento)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_green))
                        .build())
                .addSubActionView(itensMenu.setContentView(itemGasto)
                        .setBackgroundDrawable(getResources().getDrawable(R.drawable.circle_violet))
                        .build())
                .setRadius(getResources().getDimensionPixelSize(R.dimen.float_menu_radius))
                .attachTo(fab_novo).build();

        menuPrincipal.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                v_inicio.setBackgroundColor(context.getResources().getColor(R.color.colorBlack));
                fabIconNew.setRotation(0);
                PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, propertyValuesHolder);
                animator.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
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

        itemConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChamarListConfiguracoes();
                menuPrincipal.close(true);
            }
        });

        // FIM MENU FLUTUANTE

        bt_ini_addviagem = (Button) findViewById(R.id.bt_ini_addviagem);
        tx_ini_dataviagem = (TextView) findViewById(R.id.tx_ini_dataviagem);
        tx_ini_valorviagem = (TextView) findViewById(R.id.tx_ini_valorviagem);
        bt_ini_addviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExibirViagemNew();
            }
        });

        NotificarCategoria();


    }

    @Override
    public void onResume() {
        Iniciar();
        super.onResume();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if (exibir_menu == 1)
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




    public void NotificarCategoria() {
        viagens =  new Viagens_DAO(context).listar(id_usuario);
        viagem = new Viagens_DAO(context).buscarID(id_usuario);
        int Valor_vi = Integer.parseInt(viagem.getVi_valor());
        planejamento = new Planejamento_DAO(context).buscarID(id_usuario);
        int Valor_planejado = Integer.parseInt(planejamento.getPl_valor());
        double Porcentagem = 0;
        Porcentagem = Valor_vi / Valor_planejado;
       // planejamentos = new Planejamento_DAO(context).listar(id_usuario);


        //if(viagens.size() > 0) {
            //if (planejamentos.size() > 0) {
                if (Porcentagem > 1.1 && Porcentagem <= 2) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, InicioActivity.class), 0);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setTicker("Viagem");
                    builder.setContentText("Você atingiu 50% do planejado da sua viajem!");
                    builder.setSmallIcon(R.drawable.trip_icon);
                    builder.setContentIntent(p);
                    NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                    String[] msg = new String[]{"Você Atingiu 50% do total", "planejado da sua viagem!"};
                    for (int i = 0; i < msg.length; i++) {
                        style.addLine(msg[i]);
                    }
                    builder.setStyle(style);

                    Notification notification = builder.build();
                    notification.vibrate = new long[]{150, 300, 150, 600};
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(R.drawable.trip_icon, notification);

                    try {
                        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone toque = RingtoneManager.getRingtone(this, som);
                        toque.play();

                    } catch (Exception e) {

                    }
                }

                if (Porcentagem > 0 && Porcentagem <= 1.1) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, InicioActivity.class), 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setTicker(context.getResources().getString(R.string.viagem));
                    builder.setContentText(context.getResources().getString(R.string.notificacao_gasto));
                    builder.setSmallIcon(R.drawable.trip_icon);
                    builder.setContentIntent(p);
                    NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                    String[] msg = new String[]{"Você Atingiu 90% do total", "planejado da sua viagem!"};
                    for (int i = 0; i < msg.length; i++) {
                        style.addLine(msg[i]);
                    }
                    builder.setStyle(style);

                    Notification notification = builder.build();
                    notification.vibrate = new long[]{150, 300, 150, 600};
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(R.drawable.trip_icon, notification);

                    try {
                        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone toque = RingtoneManager.getRingtone(this, som);
                        toque.play();

                    } catch (Exception e) {

                    }
                }

           // }

       // }
    }


    public void NotificarPlanejado(){

    }

    public void ExibirViagemNew() {
        DialogFragment viagemnew = new Viagem_New();
        viagemnew.show(getSupportFragmentManager(), "viagemnew");
    }

    public void Iniciar() {
        viagens = new Viagens_DAO(context).listar(id_usuario);
        if (viagens.size() > 0) {
            bt_ini_addviagem.setVisibility(View.INVISIBLE);
            tx_ini_dataviagem.setVisibility(View.VISIBLE);
            tx_ini_valorviagem.setVisibility(View.VISIBLE);
            id_viagem = viagens.size();
            viagem = new Viagens_DAO(context).buscarID(id_viagem);
            titulo = viagem.getVi_nome();
            getSupportActionBar().setTitle(titulo);
            tx_ini_dataviagem.setText(context.getResources().getString(R.string.periodo
            ) + " " + viagem.getVi_dtinic() + " " + context.getResources().getString(R.string.a) + " " + viagem.getVi_dtfim());
            tx_ini_valorviagem.setText(context.getResources().getString(R.string.valordisponivel) + " " + context.getResources().getString(R.string.moeda) + " " + viagem.getVi_valor());
            exibir_menu = 1;
        } else {
            bt_ini_addviagem.setVisibility(View.VISIBLE);
            tx_ini_dataviagem.setVisibility(View.INVISIBLE);
            tx_ini_valorviagem.setVisibility(View.INVISIBLE);
            exibir_menu = 0;
        }
    }

    public void ChamarListGasto() {
        Intent it_gastos = new Intent(context, GastosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_gastos.putExtras(bundle);

        startActivityForResult(it_gastos, 1);
    }

    public void ChamarListPagamento() {
        Intent it_pagamento = new Intent(context, PagamentosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_pagamento.putExtras(bundle);

        startActivityForResult(it_pagamento, 1);
    }

    public void ChamarListPlanejamento() {
        Intent it_planejamento = new Intent(context, PlanejamentosListActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt(Nomes.getViId(), id_viagem);
        it_planejamento.putExtras(bundle);

        startActivityForResult(it_planejamento, 1);
    }

    public void ChamarListConfiguracoes() {
        Intent it_configuracoes = new Intent(context, ConfiguracoesListActivity.class);
        startActivity(it_configuracoes);
    }


    public static int getId_usuario() {
        return id_usuario;
    }
}

