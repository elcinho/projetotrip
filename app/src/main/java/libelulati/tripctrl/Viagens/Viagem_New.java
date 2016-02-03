package libelulati.tripctrl.Viagens;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import libelulati.tripctrl.R;

public class Viagem_New extends DialogFragment{
    EditText ed_dvi_nome, ed_dvi_dtinic, ed_dvi_dtfim, ed_dvi_valor;
    boolean v_nome, v_dtini, v_dtfim, v_valor;
    Button positivo;
    int id_usuario;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogview = inflater.inflate(R.layout.dialog_viagem_novo, null);
        builder.setView(dialogview);
        builder.setTitle(getActivity().getResources().getString(R.string.novaviagem));

        ed_dvi_nome = (EditText)dialogview.findViewById(R.id.ed_dvi_nome);
        ed_dvi_dtinic = (EditText)dialogview.findViewById(R.id.ed_dvi_dtinic);
        ed_dvi_dtfim = (EditText)dialogview.findViewById(R.id.ed_dvi_dtfim);
        ed_dvi_valor = (EditText)dialogview.findViewById(R.id.ed_dvi_valor);

        builder.setPositiveButton(getActivity().getResources().getString(R.string.salvar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (v_nome && v_dtini && v_dtfim && v_valor) {
                    Salvar();
                } else {
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_validar_campos), Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton(getActivity().getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positivo.setTextColor(getActivity().getResources().getColor(R.color.colorGrey));
                positivo.setEnabled(false);
            }
        });

        return dialog;
    }

    public void Salvar(){

        Viagem viagem = new Viagem();

        viagem.setUs_id(id_usuario);
        viagem.setVi_nome(ed_dvi_nome.getText().toString());
        viagem.setVi_dtinic(ed_dvi_dtinic.getText().toString());
        viagem.setVi_dtfim(ed_dvi_dtfim.getText().toString());
        viagem.setVi_valor(ed_dvi_valor.getText().toString());

        boolean sucesso = new Viagens_Dao(getActivity()).criar(viagem);
        if(sucesso){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sucesso_criar_viagem), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_criar_viagem), Toast.LENGTH_LONG).show();
        }
        dismiss();
    }
}
