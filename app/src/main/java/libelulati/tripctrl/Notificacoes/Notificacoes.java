package libelulati.tripctrl.Notificacoes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Inicio.Totais;
import libelulati.tripctrl.Inicio.Totais_DAO;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;


public class Notificacoes extends Activity {
    Context context;
    Cnotificacoes cnotificacoes;

    float Valor_planejado = 0;
    float Valor_gasto = 0;
    float Porcentagem = 0;
    float Valor_viagem = 0;
    int cn_gasto , cn_planejamento,cn_viagem;
    int id_viagem = InicioActivity.getId_viagem();
    SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");


    public void NotificarPlanejado50() {
        int id = (int) System.currentTimeMillis();
        String Planejamento = "planejamento";
        String Gasto = "gasto";

        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais TotalPlanejado = totais_dao.buscarNome(Planejamento, id_viagem);
        Totais TotalGasto = totais_dao.buscarNome(Gasto, id_viagem);

        if(TotalPlanejado != null) {
            Valor_planejado = Float.parseFloat(TotalPlanejado.getTo_planejamento());
        }
        if(TotalGasto != null) {
            Valor_gasto = Float.parseFloat(TotalGasto.getTo_gasto());
        }


            Porcentagem = (Valor_planejado / Valor_gasto);


       // if(cn_gasto == 1) {
            if (Porcentagem > 1.1 && Porcentagem < 2) {
                Intent notificarIntent = new Intent(getApplicationContext(), InicioActivity.class);
                notificarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, id, notificarIntent, PendingIntent.FLAG_UPDATE_CURRENT );

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setContentIntent(pendingIntent);

                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                String[] msg = new String[]{context.getResources().getString(R.string.notificacao_viagem_50_planejado1), context.getResources().getString(R.string.notificacao_viagem_50_planejado2)};
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
        //}
    }

    public void NotificarPlanejado90() {
        //cn_planejamento = cnotificacoes.getCn_planejamentos();
        int id = (int) System.currentTimeMillis();
        String Planejamento = "planejamento";
        String Gasto = "gasto";


        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais TotalPlanejado = totais_dao.buscarNome(Planejamento,id_viagem);
        Totais TotalGasto = totais_dao.buscarNome(Gasto, id_viagem);


        if(TotalPlanejado != null) {
            Valor_planejado = Float.parseFloat(TotalPlanejado.getTo_planejamento());
        }

        if(TotalGasto != null) {
            Valor_gasto = Float.parseFloat(TotalGasto.getTo_gasto());
        }


        Porcentagem = (Valor_planejado / Valor_gasto);
        //if(cn_planejamento == 1) {
            if (Porcentagem > 0 && Porcentagem <= 1.1) {
                Intent notificarIntent = new Intent(getApplicationContext(), InicioActivity.class);
                notificarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, id, notificarIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setContentIntent(pendingIntent);

                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                String[] msg = new String[]{context.getResources().getString(R.string.notificacao_viagem_90_planejado1), context.getResources().getString(R.string.notificacao_viagem_90_planejado2)};
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
          //  }
        }

    }

    public void NotificarGasto50() {
       // cn_gasto = cnotificacoes.getCn_gastos();
        int id =  ( int )  System . currentTimeMillis ();
        String Gasto = "gasto";
        String Viagem = "viagem";


        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais TotalGasto = totais_dao.buscarNome(Gasto, id_viagem);
        Totais TotalVigem = totais_dao.buscarNome(Viagem,id_viagem);

        if(TotalGasto != null) {
            Valor_gasto = Float.parseFloat(TotalGasto.getTo_gasto());
        }

        if (TotalVigem != null) {
            Valor_viagem = Float.parseFloat(TotalVigem.getTo_total());
        }


        Porcentagem = (Valor_viagem / Valor_gasto);

      //  if(cn_gasto == 1) {
            if (Porcentagem > 1.1 && Porcentagem < 2) {
                Intent notificarIntent = new Intent(getApplicationContext(), InicioActivity.class);
                notificarIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, id, notificarIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setContentIntent(pendingIntent);

                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                String[] msg = new String[]{context.getResources().getString(R.string.notificacao_viagem_50_total1), context.getResources().getString(R.string.notificacao_viagem_50_total2)};
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
      //  }
    }

    public void NotificarGasto90() {
        //cn_gasto = cnotificacoes.getCn_gastos();
        int id = (int) System.currentTimeMillis();
        String Gasto = "gasto";
        String Viagem = "viagem";

        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais TotalGasto = totais_dao.buscarNome(Gasto, id_viagem);
        Totais TotalVigem = totais_dao.buscarNome(Viagem, id_viagem);

        if(TotalGasto != null) {
            Valor_gasto = Float.parseFloat(TotalGasto.getTo_gasto());
        }

        if(TotalVigem != null) {
            Valor_viagem = Float.parseFloat(TotalVigem.getTo_total());
        }

        Porcentagem = (Valor_viagem / Valor_gasto);
        //if(cn_gasto ==1){
            if (Porcentagem > 0 && Porcentagem < 1.1) {
                Intent notificatIntent = new Intent(getApplicationContext(), InicioActivity.class);
                notificatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //
              PendingIntent pendingIntent = PendingIntent.getActivity(this, id , notificatIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setContentIntent(pendingIntent);

                NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                String[] msg = new String[]{context.getResources().getString(R.string.notificacao_viagem_90_total1), context.getResources().getString(R.string.notificacao_viagem_90_total2)};
                for (int i = 0; i < msg.length; i++) {
                    style.addLine(msg[i]);
                }
                builder.setStyle(style);
//pull
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
          //  }
        }
    }

    private void NotificarViagem(){
        cn_viagem = cnotificacoes.getCn_viagens();
        //Data_viagem = (Date) formata.parse(Nomes.getViDtini());
        NotificacoesConfiguracaoActivity notificacoesConfiguracaoActivity = new NotificacoesConfiguracaoActivity();


        if(cn_viagem ==1) {
        //    if (!(Data_viagem == (Data_viagem - 1))) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, InicioActivity.class), 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setContentText(context.getResources().getString(R.string.notificacao_inicio_viagem));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setContentIntent(p);

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
        //}
    }

}

