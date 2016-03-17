package libelulati.tripctrl.Notificacoes;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import libelulati.tripctrl.Gastos.Gasto;
import libelulati.tripctrl.Gastos.Gastos_DAO;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Viagens.Viagem;
import libelulati.tripctrl.Viagens.Viagens_DAO;


public class NotificacoesCinquentaPorcento extends DialogFragment {

    Dialog dialog;
    Viagem ValorViagem;
    Gasto gastos;
    String ViagemValor, ValorTotalGasto;
    double Porcentagem;


    public Dialog onCreateDialog(Bundle bundle) {
        ValorViagem = new Viagens_DAO(getActivity()).buscarID(getId());
        gastos = new Gastos_DAO(getActivity()).buscarID(getId());
        ViagemValor = ValorViagem.getVi_valor();
        ValorTotalGasto = gastos.getGa_valor();

        Porcentagem = ViagemValor.length() / ValorTotalGasto.length();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.viagem);
        builder.setMessage(R.string.Notificaçao_categoria_50);
        builder.setPositiveButton(R.string.opcao_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });

        if (Porcentagem > 1.1 && Porcentagem <= 2) {
            builder.create();
            builder.show();

        } else {

            dialog.dismiss();

        }
        return dialog;
    }

}




