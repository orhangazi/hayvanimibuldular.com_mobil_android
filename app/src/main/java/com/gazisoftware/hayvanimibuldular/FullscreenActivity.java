package com.gazisoftware.hayvanimibuldular;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.webkit.WebViewClient;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "";

    public Button karekod_tara;

    private WebView webview;
    public WebViewClient webViewClient;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        webViewClient = new WebViewClient();
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(webViewClient);

        webview.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20120716 Firefox/15.0a2");
        webview.loadUrl("http://176.218.214.101/hayvanimibuldular.com");

        karekod_tara = (Button)findViewById(R.id.karekod_tara);

        final Activity activity = this;
        karekod_tara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Karekoda tutun");
                integrator.setCameraId(1); //0 arka kamera 1 ön kamera
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    public void hakkinda_activiy_goster (View view){
        Toast.makeText(this,"Hakkında",Toast.LENGTH_LONG).show();
    }

    /*@Override
    public boolean onCreateOptionsMenu (Menu menu) {

        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_ana, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hakkinda_menu:
                Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if (result.getContents()==null){
                Toast.makeText(this, "Taramayı iptal ettin", Toast.LENGTH_LONG).show();
            }else
            {
                Intent intent = new Intent(this,BilgilerActivity.class);
                String sonuc;
                sonuc = result.getContents();
                intent.putExtra(EXTRA_MESSAGE,sonuc);
                startActivity(intent);
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
