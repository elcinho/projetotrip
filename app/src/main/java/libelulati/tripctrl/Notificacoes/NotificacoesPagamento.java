package libelulati.tripctrl.Notificacoes;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Gastos.Gastos_DAO;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Inicio.Totais_DAO;
import libelulati.tripctrl.Planejamentos.Planejamento_DAO;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagens_DAO;


public class NotificacoesPagamento extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("Script" , "-> Alarme");
        NotificarPagamento(context, new Intent(context, InicioActivity.class),context.getResources().getString(R.string.viagem), context.getResources().getString(R.string.pagamento), context.getResources().getString(R.string.notificacao_pagamentos));

    }

    public void NotificarPagamento(Context context, Intent intent,CharSequence Ticker, CharSequence titulo, CharSequence Descrição) {
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                    builder.setTicker(Ticker);
                    builder.setContentTitle(titulo);
                    builder.setContentText(Descrição);
                    builder.setSmallIcon(R.drawable.trip_icon);
                    builder.setContentIntent(p);

                    Notification notification = builder.build();
                    notification.vibrate = new long[]{150, 300, 150, 600};
                    notification.flags = Notification.FLAG_AUTO_CANCEL;
                    notificationManager.notify(R.drawable.trip_icon, notification);

                    try {
                        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone toque = RingtoneManager.getRingtone(context, som);
                        toque.play();

                    } catch (Exception e) {

                    }
                }


}








