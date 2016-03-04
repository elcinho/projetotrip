package libelulati.tripctrl.TipoPagamento;


import android.content.ContentValues;
import android.content.Context;

import libelulati.tripctrl.Dados.Dados;
import libelulati.tripctrl.Dados.Nomes;

public class TipoPagamento_DAO extends Dados{
    public TipoPagamento_DAO(Context context) {
        super(context);
    }

    public boolean criar(TipoPagamento tipoPagamento){
        ContentValues values = new ContentValues();

        values.put(Nomes.getUsId(), tipoPagamento.getUs_id());
        values.put(Nomes.getTpNome(), tipoPagamento.getTp_nome());

        return true;
    }
}
