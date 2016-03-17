package libelulati.tripctrl.Usuarios;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import libelulati.tripctrl.R;

public class AlterarSenha extends DialogFragment{
    EditText ns_senha;
    Button positivo;
    int id_usuario;

    public AlterarSenha(int id_usuario){
        this.id_usuario = id_usuario;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogview = inflater.inflate(R.layout.dialog_alterarsenha, null);
        builder.setView(dialogview);
        builder.setTitle(getActivity().getResources().getString(R.string.informenovasenha));

        ns_senha = (EditText)dialogview.findViewById(R.id.ed_ns_senha);

        builder.setPositiveButton(getActivity().getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SalvarSenha();
            }
        });

        builder.setNegativeButton(getActivity().getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_alterar_senha), Toast.LENGTH_LONG).show();
                dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positivo.setEnabled(false);
            }
        });

        return dialog;
    }

    public void SalvarSenha(){
        Usuario_DAO usuarioDAO = new Usuario_DAO(getActivity());
        Usuario usuario = usuarioDAO.buscaId(id_usuario);

        usuario.setUs_senha(ns_senha.getText().toString());

        boolean sucesso = usuarioDAO.alterarsenha(usuario, id_usuario);
        if(sucesso){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sucesso_alterar_senha), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_alterar_senha), Toast.LENGTH_LONG).show();
        }
    }

}
