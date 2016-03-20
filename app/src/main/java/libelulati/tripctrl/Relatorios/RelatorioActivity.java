package libelulati.tripctrl.Relatorios;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Categorias.Categoria;
import libelulati.tripctrl.Categorias.Categorias_DAO;
import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.Inicio.Totais;
import libelulati.tripctrl.Inicio.Totais_DAO;
import libelulati.tripctrl.R;

public class RelatorioActivity extends AppCompatActivity {
    Context context;
    int idusuario = InicioActivity.getId_usuario(), idviagem = InicioActivity.getId_viagem();
    int relatorio;
    PieChart pieChart;
    BarChart barChart;
    TextView gr_titulo;
    ImageView gr_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        context = RelatorioActivity.this;

        gr_titulo = (TextView)findViewById(R.id.gr_titulo);
        gr_voltar = (ImageView)findViewById(R.id.gr_voltar);
        pieChart = (PieChart)findViewById(R.id.gr_pier);
        barChart = (BarChart)findViewById(R.id.gr_barra);

        gr_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it_voltar = new Intent(context, InicioActivity.class);
                startActivity(it_voltar);
                finish();
            }
        });

        Intent it_relatorio = getIntent();
        Bundle bundle = it_relatorio.getExtras();

        relatorio = bundle.getInt("relatorio");

        switch(relatorio){
            case 1:
                RelatorioPlanejamentoXGastos();
                break;
            case 2:
                RelatorioPlanejamentoXGastoCategoria();
                break;
            case 3:
                RelatorioPlanejamentoCategoria();
                break;
            case 4:
                RelatorioGastoCategoria();
                break;
        }
    }

    public void RelatorioPlanejamentoXGastos(){
        String planejamento = "planejamento", gasto = "gasto";
        float val_planejado = 0, val_gasto = 0, porc_planejado = 0, porc_gasto = 0;

        Totais_DAO totais_dao = new Totais_DAO(context);
        Totais totalplanejado = totais_dao.buscarNome(planejamento);
        Totais totalgasto = totais_dao.buscarNome(gasto);

        if(totalplanejado != null){
            val_planejado = Float.parseFloat(totalplanejado.getTo_total());
        }

        if(totalgasto != null){
            val_gasto = Float.parseFloat(totalgasto.getTo_total());
        }

        porc_gasto = (val_gasto * 100 )/val_planejado;
        porc_planejado = 100 - porc_gasto;

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        if(val_gasto == 0 && val_planejado == 0){
            gr_titulo.setText(context.getResources().getString(R.string.re_naoencontrado));        }
        else{
            if(val_gasto == 0){
                pieChart.setVisibility(View.VISIBLE);
                entries.add(new Entry(val_planejado, 0));
                labels.add(context.getResources().getString(R.string.planejado) + " - " + String.valueOf(porc_planejado) + "%");
                gr_titulo.setText(context.getResources().getString(R.string.re_valorPlanejado));
            }
            if(val_planejado == 0){
                pieChart.setVisibility(View.VISIBLE);
                entries.add(new Entry(val_gasto, 0));
                labels.add(context.getResources().getString(R.string.gasto) + " - " +String.valueOf(porc_gasto) + "%");
                gr_titulo.setText(context.getResources().getString(R.string.re_valorGasto));
            }
            else{
                pieChart.setVisibility(View.VISIBLE);
                entries.add(new Entry(val_gasto, 0));
                entries.add(new Entry(val_planejado - val_gasto, 1));
                labels.add(context.getResources().getString(R.string.gasto) + " - " + format(porc_gasto) + "%");
                labels.add(context.getResources().getString(R.string.planejado) + " - " + format(porc_planejado) + "%");
                gr_titulo.setText(context.getResources().getString(R.string.re_gastoxplanejado));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        PieData data = new PieData(labels, dataSet);

        pieChart.setData(data);
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        pieChart.setDescription("");

        ArrayList<Integer> cores = new ArrayList<Integer>();
        if(cores.size() == 0){
            cores.add(context.getResources().getColor(R.color.colorRed));
            cores.add(context.getResources().getColor(R.color.colorGreen));
        }

        dataSet.setColors(cores);
    }

    public void RelatorioPlanejamentoXGastoCategoria(){
        float val_planejamento = 0, val_gasto = 0, val_total = 0;
        int indice = 0;

        List<Categoria> categorias = new Categorias_DAO(context).listar(idusuario);
        List<Totais> totais = new Totais_DAO(context).listar(idviagem);

        ArrayList<BarEntry> entries_pl = new ArrayList<>();
        ArrayList<BarEntry> entries_ga = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        for(int i = 0; i < categorias.size(); i++){
            for(int j = 0; j < totais.size(); j++){
                if(categorias.get(i).getCa_nome().equals(totais.get(j).getTo_nome())){
                    val_planejamento = Float.parseFloat(totais.get(j).getTo_planejamento());
                    val_gasto = Float.parseFloat(totais.get(j).getTo_gasto());
                    val_total += (val_planejamento - val_gasto);
                    labels.add(categorias.get(i).getCa_nome());
                    entries_pl.add(new BarEntry(val_planejamento, indice));
                    entries_ga.add(new BarEntry(val_gasto, indice));
                    indice++;
                }
            }
        }

        ArrayList<Integer> cor_pl = new ArrayList<Integer>();
        if(cor_pl.size() == 0){
            cor_pl.add(context.getResources().getColor(R.color.colorGreen));
        }

        ArrayList<Integer> cor_ga = new ArrayList<Integer>();
        if(cor_ga.size() == 0){
            cor_ga.add(context.getResources().getColor(R.color.colorRed));
        }

        if(val_total != 0){
            barChart.setVisibility(View.VISIBLE);
            gr_titulo.setText(context.getResources().getString(R.string.re_gastoxplanejado_categoria));

            BarDataSet dataSet_pl = new BarDataSet(entries_pl, context.getResources().getString(R.string.planejado));
            dataSet_pl.setColors(cor_pl);
            BarDataSet dataSet_ga = new BarDataSet(entries_ga, context.getResources().getString(R.string.gasto));
            dataSet_ga.setColors(cor_ga);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(dataSet_pl);
            dataSets.add(dataSet_ga);

            BarData data = new BarData(labels, dataSets);

            barChart.setData(data);
            barChart.setDescription("");
            barChart.setBackgroundColor(Color.TRANSPARENT);

        }
        else{
            gr_titulo.setText(context.getResources().getString(R.string.re_naoencontrado));
        }
    }

    public void RelatorioPlanejamentoCategoria(){
        float val_planejamento = 0, val_total = 0;
        int indice = 0;

        List<Categoria> categorias = new Categorias_DAO(context).listar(idusuario);
        List<Totais> totais = new Totais_DAO(context).listar(idviagem);

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        for(int i = 0; i < categorias.size(); i++){
            for(int j = 0; j < totais.size(); j++){
                if(categorias.get(i).getCa_nome().equals(totais.get(j).getTo_nome())){
                    val_planejamento = Float.parseFloat(totais.get(j).getTo_planejamento());
                    val_total += val_planejamento;
                    labels.add(categorias.get(i).getCa_nome());
                    entries.add(new BarEntry(val_planejamento, indice));
                    indice++;
                }
            }
        }

        ArrayList<Integer> cores = new ArrayList<Integer>();
        if(cores.size() == 0){
            cores.add(context.getResources().getColor(R.color.colorGreen));
        }

        if(val_total != 0){
            barChart.setVisibility(View.VISIBLE);
            gr_titulo.setText(context.getResources().getString(R.string.re_planejado_categoria));

            BarDataSet dataSet = new BarDataSet(entries, context.getResources().getString(R.string.categoria));
            dataSet.setColors(cores);
            BarData data = new BarData(labels, dataSet);

            barChart.setData(data);
            barChart.setDescription("");
            barChart.setBackgroundColor(Color.TRANSPARENT);

        }
        else{
            gr_titulo.setText(context.getResources().getString(R.string.re_naoencontrado));
        }
    }

    public void RelatorioGastoCategoria(){
        float val_gasto = 0, val_total = 0;
        int indice = 0;

        List<Categoria> categorias = new Categorias_DAO(context).listar(idusuario);
        List<Totais> totais = new Totais_DAO(context).listar(idviagem);

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();

        for(int i = 0; i < categorias.size(); i++){
            for(int j = 0; j < totais.size(); j++){
                if(categorias.get(i).getCa_nome().equals(totais.get(j).getTo_nome())){
                    val_gasto = Float.parseFloat(totais.get(j).getTo_gasto());
                    val_total += val_gasto;
                    labels.add(categorias.get(i).getCa_nome());
                    entries.add(new BarEntry(val_gasto, indice));
                    indice++;
                }
            }
        }

        ArrayList<Integer> cores = new ArrayList<Integer>();
        if(cores.size() == 0){
            cores.add(context.getResources().getColor(R.color.colorRed));
        }

        if(val_total != 0){
            barChart.setVisibility(View.VISIBLE);
            gr_titulo.setText(context.getResources().getString(R.string.re_gasto_categoria));

            BarDataSet dataSet = new BarDataSet(entries, context.getResources().getString(R.string.categoria));
            dataSet.setColors(cores);
            BarData data = new BarData(labels, dataSet);

            barChart.setData(data);
            barChart.setDescription("");
            barChart.setBackgroundColor(Color.TRANSPARENT);

        }
        else{
            gr_titulo.setText(context.getResources().getString(R.string.re_naoencontrado));
        }
    }

    public static String format(float x){
        return String.format("%.2f", x);
    }

}
