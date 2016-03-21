package libelulati.tripctrl.Notificacoes;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import libelulati.tripctrl.Configuracoes.ConfiguracoesListActivity;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Main.MainActivity;
import libelulati.tripctrl.R;

/**
 * Created by 01543558 on 21/03/16.
 */
public class NotificarPagamentoAlarme extends Service {
    Context context;

    @Override
    public  int onStartCommand (Intent intent ,  int flags,  int startId)  {

        generarNotificacion ();
        return  Service.START_NOT_STICKY ;
    }

    @Override
    public IBinder onBind ( Intent intent)  {
        return  null ;
    }

    public  void generarNotificacion (){

        Intent intent =  new  Intent( this.getApplicationContext(), InicioActivity.class );

        NotificationCompat.Builder builder =new  NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.trip_icon);
        builder.setContentTitle(context.getResources().getString(R.string.viagem));
        builder.setContentText(context.getResources().getString(R.string.notificacao_vencimento));

        PendingIntent pendingIntent = PendingIntent.getActivity (this , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(R.drawable.trip_icon, builder.build());
    }

}
