package libelulati.tripctrl.Planejamentos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import libelulati.tripctrl.Funcoes.MeuSpinner;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class Planejamento_new extends DialogFragment{
    EditText ed_dpl_categoria, ed_dpl_valor;
    Spinner sp_dpl_categoria;
    int id_usuario = InicioActivity.getId_usuario();
    int id_viagem;
    View dialogview;
    MeuSpinner meuSpinner = new MeuSpinner();
    List<String> listacategorias;

    public Planejamento_new(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        dialogview = inflater.inflate(R.layout.dialog_planejamento_novo, null);
        builder.setView(dialogview);
        builder.setTitle(getActivity().getResources().getString(R.string.novoplanejamento));

        ed_dpl_categoria = (EditText)dialogview.findViewById(R.id.ed_dpl_categoria);
        ed_dpl_valor = (EditText)dialogview.findViewById(R.id.ed_dpl_valor);
        sp_dpl_categoria = (Spinner)dialogview.findViewById(R.id.sp_dpl_categoria);

        listacategorias = new Planejamento_DAO(getActivity()).sp_categorias(id_usuario);

        meuSpinner.preencherSpinner(getActivity(), listacategorias, sp_dpl_categoria);
        meuSpinner.selecionarItem(sp_dpl_categoria, ed_dpl_categoria);

        builder.setPositiveButton(getActivity().getResources().getString(R.string.opcao_ok), new DialogInterface.OnClickListener() {
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
        return dialog;
    }

    public void Salvar(){

        Planejamento planejamento = new Planejamento();
        planejamento.setUs_id(id_usuario);
        planejamento.setVi_id(id_viagem);
        planejamento.setCa_id(ed_dpl_categoria.getText().toString());
        planejamento.setPl_valor(ed_dpl_valor.getText().toString());

        boolean sucesso = new Planejamento_DAO(getActivity()).criar(planejamento);
        if(sucesso){
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.sucesso_criar_planejamento), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.erro_criar_planejamento), Toast.LENGTH_LONG).show();
        }
        dismiss();
    }

    public boolean IsValido(){
        return true;
    }
}
