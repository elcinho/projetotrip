package libelulati.tripctrl.Funcoes;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    int dia, mes, ano;
    EditText editText;

    public DatePicker(EditText editText) {
        this.editText = editText;
    }

    public void Calendario() {
        final Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void  AtualizarData(){
         editText.setText(new StringBuilder().append(dia).append("/").append(mes).append("/").append(ano));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendario();
        return new DatePickerDialog(getActivity(), this, ano, mes, dia);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        ano = year;
        mes = monthOfYear + 1;
        dia = dayOfMonth;

        AtualizarData();
    }
}
