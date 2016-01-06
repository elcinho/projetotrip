package libelulati.tripctrl.Senha;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;

import static android.app.AlertDialog.*;

public class AlterarSenha extends DialogFragment{

    EditText ns_senha, ns_confirmesenha;
    Button positivo;
    boolean validar = false, v_senha = false, v_consenha = false;
    int id_usuario;

    public AlterarSenha(int id_usuario){
        this.id_usuario = id_usuario;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_alterarsenha, null);
        builder.setView(dialogView);
        builder.setTitle(getActivity().getResources().getString(R.string.informenovasenha));

        ns_senha = (EditText)dialogView.findViewById(R.id.ed_ns_senha);
        ns_confirmesenha = (EditText)dialogView.findViewById(R.id.ed_ns_confirmesenha);

        ns_senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verificarSenha(ns_senha.getText().toString());
                    if(v_senha){
                        positivo.setEnabled(true);
                    }
                }
            }
        });

        ns_confirmesenha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verificarConfirmeSenha(ns_senha.getText().toString(), ns_confirmesenha.getText().toString());
                    if(v_senha && v_consenha){
                        positivo.setEnabled(true);
                    }
                }
            }
        });

        builder.setPositiveButton(getActivity().getResources().getString(R.string.opcao_ok), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                salvarSenha();
                getActivity().finish();
            }
        });

        builder.setNegativeButton(getActivity().getResources().getString(R.string.opcao_cancelar), new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positivo.setEnabled(false);
            }
        });

        return dialog;
    }

    public void salvarSenha(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(getActivity());
        Usuario usuario = usuarioDAO.buscaId(id_usuario);

        boolean sucesso = usuarioDAO.alterarsenha(usuario, id_usuario);
        if(sucesso){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.alterarsenha), Toast.LENGTH_LONG).show();
        }

    }

    public void verificarSenha(String senha){
        validar = Validar.ValidarSenha(senha);
        if(validar){
            v_senha = true;
        } else {
            v_senha = false;
            ns_senha.setError(getActivity().getResources().getString(R.string.validar_senha));
        }

    }

    public void verificarConfirmeSenha(String senha, String confirmesenha){
        validar = Validar.ValidarConfirmeSenha(senha, confirmesenha);
        if(validar){
            v_consenha = true;
        } else {
            v_consenha = false;
            ns_confirmesenha.setError(getActivity().getResources().getString(R.string.senhas_diferentes));
        }
    }
}