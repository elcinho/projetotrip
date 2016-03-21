package libelulati.tripctrl.Notificacoes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;




public class NotificarBroadcast extends BroadcastReceiver{


    @Override
    public  void onReceive ( Context context,  Intent intent)  {
        Intent startIntent =  new  Intent ( context , NotificarPagamentoAlarme.class);
        context.startService(startIntent);
    }
}








