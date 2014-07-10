package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import pl.adluna.combatzone.R;


/**
 * Created by Natalia Stawowy on 01.07.14.
 */
public class MenuActivity extends Activity{
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        mContext=this;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        ImageView news = (ImageView) findViewById(R.id.news);
        ImageView facebook =(ImageView) findViewById(R.id.facebook);
        ImageView training = (ImageView) findViewById(R.id.training);
       // ImageView fighterstore =(ImageView) findViewById(R.id.fighterstore);
        ImageView page = (ImageView) findViewById(R.id.page);
        ImageView seminar =(ImageView) findViewById(R.id.seminar);
        ImageView navi =(ImageView) findViewById(R.id.navigation);
        ImageView foto = (ImageView) findViewById(R.id.foto);
        ImageView play =(ImageView) findViewById(R.id.movies);
        if (isNetworkAvailable()) {

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),VideoActivity.class);
                startActivityForResult(intent, 0);

            }
        });
            foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),ImageGalleryActivity.class);
                    startActivityForResult(intent,0);

                }
            });
            navi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(getString(R.string.navi_url));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            });
            news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),NewsActivity.class);
                    startActivityForResult(intent,0);

                }
            });

            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(getString(R.string.facebook));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            training.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),TrainingActivity.class);
                    startActivityForResult(intent,0);
                }
            });

          /*  fighterstore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(getString(R.string.fightershop));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });*/

            page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(getString(R.string.combatzone));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });

            seminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),SeminarActivity.class);
                    startActivityForResult(intent,0);
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                    , Toast.LENGTH_LONG).show();
            finish();
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
