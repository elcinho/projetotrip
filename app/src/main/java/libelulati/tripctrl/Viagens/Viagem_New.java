package libelulati.tripctrl.Viagens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import libelulati.tripctrl.Funcoes.DatePicker;
import libelulati.tripctrl.Funcoes.Validar;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class Viagem_New extends DialogFragment{
    EditText ed_dvi_nome, ed_dvi_dtinic, ed_dvi_dtfim, ed_dvi_valor;
    boolean v_nome, v_dtini, v_dtfim, v_valor;
    Button positivo, negativo;
    int id_usuario = InicioActivity.getId_usuario();
    View dialogview;

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

        ed_dvi_nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                IsValido();
            }
        });

        ed_dvi_nome.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    VerificarNome();
                }
            }
        });

        ed_dvi_dtinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_dvi_dtinic);
            }
        });

        ed_dvi_dtinic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    VerificarDtini();
            }
        });

        ed_dvi_dtfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ed_dvi_dtfim.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    VerificarDtfim();
            }
        });
        ed_dvi_dtfim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(v, ed_dvi_dtfim);
            }
        });

        ed_dvi_valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VerificarValor();
            }

            @Override
            public void afterTextChanged(Editable s) {
                IsValido();
            }
        });


        builder.setPositiveButton(getActivity().getResources().getString(R.string.salvar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Salvar();
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

    public void VerificarNome(){
        boolean validar = false;
        validar = Validar.ValidarNome(String.valueOf(ed_dvi_nome.getText().toString()));
        if(!validar){
            ed_dvi_nome.setError(getActivity().getResources().getString(R.string.validar_nome));
            v_nome = false;
        }
        else{
            v_nome = true;
        }
    }

    public void VerificarDtini(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean validar = false;
        try {
            Date data = sdf.parse(String.valueOf(ed_dvi_dtinic.getText().toString()));
            validar = Validar.ValidarDataInicio(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar) {
            ed_dvi_dtinic.setError(getActivity().getResources().getString(R.string.validar_dtinicio));
            v_dtini = false;
        }
        else{
            v_dtini = true;
        }
    }

    public void VerificarDtfim(){
        boolean validar = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date inicio = sdf.parse(String.valueOf(ed_dvi_dtinic.getText().toString()));
            Date fim = sdf.parse(String.valueOf(ed_dvi_dtfim.getText().toString()));
            validar = Validar.ValidarDataFim(inicio, fim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!validar){
            ed_dvi_dtfim.setError(getActivity().getResources().getString(R.string.validar_dtfim));
            v_dtfim = false;
        }
        else{
            v_dtfim = true;
        }
    }

    public void VerificarValor(){
        boolean validar = false;
        double valor = 0;
        if(ed_dvi_valor.length() > 0){
            valor = Double.parseDouble(ed_dvi_valor.getText().toString());
            if(valor != 0){
                validar = Validar.ValidarValor(valor);
                if(!validar){
                    ed_dvi_valor.setError(getActivity().getResources().getString(R.string.validar_valor));
                    v_valor = false;
                }
                else{
                    v_valor = true;
                }
            }
        }
        else{
            v_valor = true;
        }
    }

    public boolean IsValido(){
        if(v_nome && v_dtini && v_dtfim && v_valor){
            positivo.setEnabled(true);
            positivo.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            return true;
        }
        else{
            return false;
        }
    }
}
