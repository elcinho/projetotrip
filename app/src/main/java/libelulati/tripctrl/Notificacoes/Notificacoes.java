package libelulati.tripctrl.Notificacoes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.util.List;

import libelulati.tripctrl.Dados.Nomes;
import libelulati.tripctrl.Gastos.Gasto;
import libelulati.tripctrl.Gastos.Gastos_DAO;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Inicio.Totais;
import libelulati.tripctrl.Inicio.Totais_DAO;
import libelulati.tripctrl.Planejamentos.Planejamento;
import libelulati.tripctrl.Planejamentos.Planejamento_DAO;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagens_DAO;


public class Notificacoes extends Activity {
    static int id_usuario = 0; //Integer.parseInt(Nomes.getID());
    private Context context;
    List<Viagem> viagens;
    List<Planejamento> planejamentos;
    List<Gasto> gastos;
    List<Totais>totais;
    Viagem viagem;
    Planejamento planejamento;
    Gasto gasto;
    Totais total;
    int id_viagem;
    double porcentagem, valor_vi, valor_gasto, Valor_planejado;






    public void NotificarPlanejamentoCinquentaPorcento() {
        id_usuario = 1;
        viagens = new Viagens_DAO(context).listar(id_usuario);
        planejamentos = new Planejamento_DAO(context).listar(id_usuario);
        gastos = new Gastos_DAO(context).listar(id_usuario);
        total = new Totais_DAO(context).buscarNome(Nomes.getToNome());
        valor_vi = Double.parseDouble(viagem.getVi_valor());
        valor_gasto = Double.parseDouble(gasto.getGa_valor());
        Valor_planejado = Double.parseDouble(planejamento.getPl_valor());




        if (viagens.size() > 0) {
            if (planejamentos.size() > 0) {
                if (gastos.size() > 0) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, InicioActivity.class), 0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                    builder.setTicker(context.getResources().getString(R.string.viagem));
                    builder.setContentText(context.getResources().getString(R.string.notificacao_planejamento));
                    builder.setSmallIcon(R.drawable.trip_icon);
                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_temporaria));
                    builder.setContentIntent(p);

                    NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
                    String[] msg = new String[]{context.getResources().getString(R.string.notificacao_planejamento), context.getResources().getString(R.string.viagem)};
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
        }
    }

    public void NotificarPlanejamentoNoventaPorcento() {
        //  id_usuario = 1;
        // viagens = new Viagens_DAO(context).listar(id_usuario);
        /// planejamentos = new Planejamento_DAO(context).listar(id_usuario);
        // total = new Totais_DAO(context).buscarNome(Nomes.getToTotal());

        if (viagens.size() > 0) {
            if (planejamentos.size() > 0) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, InicioActivity.class), 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
                builder.setTicker(context.getResources().getString(R.string.viagem));
                builder.setContentText(context.getResources().getString(R.string.notificacao_planejamento));
                builder.setSmallIcon(R.drawable.trip_icon);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_temporaria));
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


    }



}
