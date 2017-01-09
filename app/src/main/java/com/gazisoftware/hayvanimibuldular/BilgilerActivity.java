package com.gazisoftware.hayvanimibuldular;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class BilgilerActivity extends AppCompatActivity {
    public TextView text_adi;
    public TextView text_telefon;
    public TextView text_eposta;
    public TextView text_adres;
    public TextView text_facebook;
    public TextView text_twitter;
    public TextView text_instagram;
    public TextView text_googleplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilgiler);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String sonuc = intent.getStringExtra(FullscreenActivity.EXTRA_MESSAGE);

        text_adi = (TextView)findViewById(R.id.adi);
        text_telefon = (TextView)findViewById(R.id.telefon);
        text_eposta = (TextView)findViewById(R.id.eposta);
        text_adres = (TextView)findViewById(R.id.adres);
        text_facebook = (TextView)findViewById(R.id.facebook);
        text_twitter = (TextView)findViewById(R.id.twitter);
        text_instagram = (TextView)findViewById(R.id.instagram);
        text_googleplus = (TextView)findViewById(R.id.googleplus);

        try {
            JSONObject veri = new JSONObject(sonuc); // json
            String adi = veri.getString("adi");
            String adres = veri.getString("adres");
            String eposta = veri.getString("eposta");
            String telefon = veri.getString("telefon");
            String facebook = veri.getString("facebook");
            String twitter = veri.getString("twitter");
            String instagram = veri.getString("instagram");
            String googleplus = veri.getString("googleplus");

            text_adi.setText(Html.fromHtml("<strong>Adı:</strong> "+adi));
            text_telefon.setText(Html.fromHtml("<strong>Telefon:</strong> "+telefon));
            text_adres.setText(Html.fromHtml("<strong>Adres:</strong> "+adres));

            String eposta_link = "<strong>Eposta:</strong> <a href='mailto:"+eposta+"'>"+eposta+"</a>";
            text_eposta.setText(Html.fromHtml(eposta_link));
            text_eposta.setMovementMethod(LinkMovementMethod.getInstance());
            text_eposta.setLinkTextColor(Color.BLUE);

            String facebook_link = "<strong>Facebook:</strong> <a href='"+facebook+"'>"+facebook+"</a>";
            text_facebook.setMovementMethod(LinkMovementMethod.getInstance());
            text_facebook.setText(Html.fromHtml(facebook_link));
            text_facebook.setLinkTextColor(Color.BLUE);

            String twitter_link = "<strong>Twitter:</strong> <a href='https://www.twitter.com/"+twitter+"'>"+twitter+"</a>";
            text_twitter.setText(Html.fromHtml(twitter_link));
            text_twitter.setMovementMethod(LinkMovementMethod.getInstance());
            text_twitter.setLinkTextColor(Color.BLUE);

            String instagram_link = "<strong>İnstagram:</strong> <a href='https://www.instagram.com/"+instagram+"'>"+instagram+"</a>";
            text_instagram.setText(Html.fromHtml(instagram_link));
            text_instagram.setMovementMethod(LinkMovementMethod.getInstance());
            text_instagram.setLinkTextColor(Color.BLUE);

            String googleplus_link = "<strong>Googleplus:</strong> <a href='"+googleplus+"'>"+googleplus+"</a>";
            text_googleplus.setText(Html.fromHtml(googleplus_link));
            text_googleplus.setMovementMethod(LinkMovementMethod.getInstance());
            text_googleplus.setLinkTextColor(Color.BLUE);

        } catch (JSONException ex) {
            Toast.makeText(this,"",Toast.LENGTH_LONG).show();

            new AlertDialog.Builder(this)
                    .setTitle("Farklı Karekod")
                    .setMessage("Bu karekod hayvanimibuldular.com'un oluşturduğu bir karekod değil ya da yıpranmış. Lütfen HB no ile aramayı deneyin.")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
