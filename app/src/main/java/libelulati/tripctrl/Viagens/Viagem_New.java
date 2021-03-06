package libelulati.tripctrl.Viagens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.lang.reflect.Type;

import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class Viagem_New extends DialogFragment{
    EditText ed_dvi_nome, ed_dvi_dtinic, ed_dvi_dtfim, ed_dvi_valor;
    Button positivo, negativo;
    boolean v_nome, v_dtini, v_dtfim, v_valor;
    int id_usuario = InicioActivity.getId_usuario();
    View dialogview;
    Validar validar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        dialogview = inflater.inflate(R.layout.dialog_viagem_novo, null);
        builder.setView(dialogview);
        builder.setTitle(getActivity().getResources().getString(R.string.novaviagem));

        ed_dvi_nome = (EditText)dialogview.findViewById(R.id.ed_dvi_nome);
        ed_dvi_dtinic = (EditText)dialogview.findViewById(R.id.ed_dvi_dtinic);
        ed_dvi_dtfim = (EditText)dialogview.findViewById(R.id.ed_dvi_dtfim);
        ed_dvi_valor = (EditText)dialogview.findViewById(R.id.ed_dvi_valor);

        ed_dvi_dtinic.setInputType(InputType.TYPE_NULL);
        ed_dvi_dtfim.setInputType(InputType.TYPE_NULL);

        validar = new Validar(getActivity());

        ed_dvi_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v_nome = validar.ValidarTexto(ed_dvi_nome.getText().toString(), ed_dvi_nome);
                    IsValido();
                }
            }
        });

        ed_dvi_dtinic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_dvi_dtinic.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    showDatePicker(v, ed_dvi_dtinic);
                }
                else{
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_dvi_dtinic.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    v_dtini = validar.ValidarDataInicio(ed_dvi_dtinic.getText().toString(), ed_dvi_dtinic);
                    IsValido();
                }
            }
        });

        ed_dvi_dtfim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_dvi_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    showDatePicker(v, ed_dvi_dtfim);
                }
                else{
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ed_dvi_dtfim.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    v_dtfim = validar.ValidarDataFim(ed_dvi_dtinic.getText().toString(), ed_dvi_dtfim.getText().toString(), ed_dvi_dtfim);
                    IsValido();

                }
            }
        });

        ed_dvi_valor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                v_valor = validar.ValidarValorTotal(ed_dvi_valor.getText().toString(), ed_dvi_valor);
                IsValido();
            }
        });

        ed_dvi_valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                v_valor = validar.ValidarValorTotal(ed_dvi_valor.getText().toString(), ed_dvi_valor);
                IsValido();
            }
        });

        builder.setPositiveButton(getActivity().getResources().getString(R.string.salvar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Salvar();
                Reload();
            }
        });

        builder.setNegativeButton(getActivity().getResources().getString(R.string.opcao_cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                Reload();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                positivo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                negativo = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                positivo.setTextColor(getActivity().getResources().getColor(R.color.colorGrey));
                negativo.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
                positivo.setEnabled(false);
            }
        });

        return dialog;
    }

    public void showDatePicker(View view, EditText ed_data){
        DialogFragment dialogFragment = new DatePicker(ed_data);
        dialogFragment.show(getFragmentManager(), "datepicker");
    }

    public void Salvar(){

        Viagem viagem = new Viagem();

        viagem.setUs_id(id_usuario);
        viagem.setVi_nome(ed_dvi_nome.getText().toString());
        viagem.setVi_dtinic(ed_dvi_dtinic.getText().toString());
        viagem.setVi_dtfim(ed_dvi_dtfim.getText().toString());
        viagem.setVi_valor(ed_dvi_valor.getText().toString());

        boolean sucesso = new Viagens_DAO(getActivity()).criar(viagem);
        if(sucesso){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sucesso_criar_viagem), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_criar_viagem), Toast.LENGTH_LONG).show();
        }

        dismiss();
    }

    public void Reload(){
        Intent reiniciar = new Intent(getActivity(), InicioActivity.class);
        getActivity().finish();
        startActivity(reiniciar);
        getActivity().overridePendingTransition(0, 0); reiniciar.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    public boolean IsValido(){
        if(v_nome && v_dtini && v_dtfim && v_valor){
            positivo.setEnabled(true);
            positivo.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            return true;
        }
        else {
            return false;
        }
    }
}
