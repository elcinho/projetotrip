package libelulati.tripctrl.Notificacoes;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import libelulati.tripctrl.R;

public class NotificacoesPlanejadoNoventaPorcento extends DialogFragment {


    AlertDialog alerta;

    public Dialog onCreateDialog (Bundle bundle) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.viagem);
        //builder.setMessage(R.string.notificacao_viagem_90);
        builder.setPositiveButton(R.string.opcao_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        alerta = builder.create();
        alerta.show();


        return alerta;
    }

}


