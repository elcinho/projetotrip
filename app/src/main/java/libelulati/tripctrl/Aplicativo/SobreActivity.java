package libelulati.tripctrl.Aplicativo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import libelulati.tripctrl.Inicio.InicioActivity;
import libelulati.tripctrl.R;

public class SobreActivity extends AppCompatActivity {
    Context context;
    TextView tx_so_versao;
    String versao;
    PackageInfo packageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        context = SobreActivity.this;
        tx_so_versao = (TextView)findViewById(R.id.tx_so_versao);

        Versao();
        tx_so_versao.setText(versao);
    }

    public void Versao(){
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versao = packageInfo.versionName;
    }
}
