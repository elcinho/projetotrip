package libelulati.tripctrl.Notificacoes;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import libelulati.tripctrl.Gastos.Gasto;
import libelulati.tripctrl.Gastos.Gastos_DAO;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagens_DAO;

import static android.app.PendingIntent.getActivity;


public class NotificacoesCinquentaPorcento extends Activity {

    public void NotificaçãoPlanejadoCinquentaPorcento(View view) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    PendingIntent p = getActivity(this, 0, new Intent(this, InicioActivity.class), 0);
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
}




