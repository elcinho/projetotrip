package libelulati.tripctrl.Login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import libelulati.tripctrl.BancoDados.StringsNomes;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;
import libelulati.tripctrl.Usuario.Usuario;
import libelulati.tripctrl.Usuario.UsuarioDAO;

public class Login extends DialogFragment{
    EditText lo_email, lo_senha;
    Button positivo;
    boolean validar = false, v_email = false, valido = false;
    int id_usuario;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_login, null);
        builder.setView(dialogView);
        builder.setTitle(getActivity().getResources().getString(R.string.login));

        lo_email = (EditText)dialogView.findViewById(R.id.ed_lo_email);
        lo_senha = (EditText)dialogView.findViewById(R.id.ed_lo_senha);

        lo_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    verificarEmail();
                }
            }
        });

        lo_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposVazios();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lo_email.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposVazios();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lo_senha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposVazios();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lo_senha.removeTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                camposVazios();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        builder.setPositiveButton(getActivity().getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                verificarUsuarioSenha(lo_email.getText().toString());
                if(valido){
                    entrar();
                }
                else{
                    Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_autenticar), Toast.LENGTH_LONG).show();
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
                positivo.setEnabled(false);
            }
        });

        return dialog;
    }

    public void verificarEmail(){
        validar = Validar.ValidarEmail(lo_email.length(), lo_email.getText().toString());
        if(validar){
            v_email = true;
        }
        else{
            lo_email.setError(getActivity().getResources().getString(R.string.validar_email));
            v_email = false;
        }
    }

    public void verificarUsuarioSenha(String email){
        UsuarioDAO usuarioDAO = new UsuarioDAO(getActivity());
        Usuario usuario = usuarioDAO.buscaEmail(email);

        if(usuario == null){
            valido = false;
        }
        else{
            id_usuario = usuario.getUs_id();
            validar = Validar.ValidarUsuarioSenha(lo_email.getText().toString(), lo_senha.getText().toString(), usuario.getUs_email(), usuario.getUs_senha());
            if(validar){
                valido = true;
            }
            else{
                valido = false;
            }
        }
    }

    public void entrar(){
            Intent it_main_entrar = new Intent(getActivity(), InicioActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt(StringsNomes.getID(), id_usuario);

            it_main_entrar.putExtras(bundle);

            startActivityForResult(it_main_entrar, 1);
    }

    public void camposVazios(){
        if(lo_email.length() < 8 || lo_senha.length() < 6){
            if(!v_email){
                positivo.setEnabled(false);
            }
        }
        else {
            positivo.setEnabled(true);
        }
    }

}
