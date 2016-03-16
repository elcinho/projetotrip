package libelulati.tripctrl.Notificacoes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Planejamentos.PlanejamentosListActivity;
import libelulati.tripctrl.R;

import static android.app.PendingIntent.getActivity;

public class Notificacoes extends Activity {


    private Context getActivity;

    public void NotificaçãoPlanejadoCinquentaPorcento(View view){

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, PlanejamentosListActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("R.string.viagem");
        builder.setContentText("R.string.notificacao_viagem_50");
        builder.setSmallIcon(R.drawable.trip_icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.trip));
        builder.setContentIntent(p);

        Notification notification = builder.build();
        notification.vibrate = new long[]{150, 300, 150, 600};
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(R.drawable.trip_icon, notification);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();

        }catch(Exception e){

        }


    }




}
