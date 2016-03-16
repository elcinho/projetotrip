package libelulati.tripctrl;

import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;

public class TesteGrafico extends AppCompatActivity {

    private PieChart gr_vw_inicio;
    private Segment segment_1;
    private Segment segment_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_grafico);

        gr_vw_inicio = (PieChart)findViewById(R.id.gr_vw_inicio);
        segment_1 = new Segment("Teste 01", 60);
        segment_2 = new Segment("Teste 02", 40);

        //EmbossMaskFilter embossMaskFilter = new EmbossMaskFilter(new float[]{1,1,1}, 0.4f, 8.2f, 10);

        SegmentFormatter segmentFormatter_1 = new SegmentFormatter();
        segmentFormatter_1.configure(getApplicationContext(), R.xml.seg1);
        //segmentFormatter_1.getFillPaint().setMaskFilter(embossMaskFilter);

        SegmentFormatter segmentFormatter_2 = new SegmentFormatter();
        segmentFormatter_2.configure(getApplicationContext(), R.xml.seg2);
        //segmentFormatter_2.getFillPaint().setMaskFilter(embossMaskFilter);

        gr_vw_inicio.addSeries(segment_1, segmentFormatter_1);
        gr_vw_inicio.addSeries(segment_2, segmentFormatter_2);

        gr_vw_inicio.getBorderPaint().setColor(Color.TRANSPARENT);
        gr_vw_inicio.getBackgroundPaint().setColor(Color.TRANSPARENT);

    }

}
