package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.broadcast.CheckNotification;
import pl.adluna.combatzone.model.db.DatabaseManager;
import pl.adluna.combatzone.model.network.GetNotificationTask;
import pl.adluna.combatzone.model.service.Notifications;


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

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Context context = getApplicationContext();
                CharSequence text = "Coś poszło nie tak, spróbuj uruchomić aplikację jeszcze raz";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });

        ImageView news = (ImageView) findViewById(R.id.news);
        ImageView facebook =(ImageView) findViewById(R.id.facebook);
        ImageView training = (ImageView) findViewById(R.id.training);
        ImageView notifications =(ImageView) findViewById(R.id.notifications);
        ImageView page = (ImageView) findViewById(R.id.page);
        ImageView seminar =(ImageView) findViewById(R.id.seminar);
        ImageView navi =(ImageView) findViewById(R.id.navigation);
        ImageView foto = (ImageView) findViewById(R.id.foto);
        ImageView play =(ImageView) findViewById(R.id.movies);


        final DatabaseManager db = new DatabaseManager(this);
        db.getWritableDatabase();
        Cursor cursor=db.getAll(db.getReadableDatabase());
        while(cursor.moveToNext()) {
            if (Boolean.valueOf(cursor.getString(1))) {
                if (isNetworkAvailable()) {
                    CheckNotification.setAlarm(this);
                }  else {
                    Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    Intent intent = new Intent(view.getContext(), VideoActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
            foto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Intent intent = new Intent(view.getContext(), ImageGalleryActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }

                }
            });
            navi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Uri uri = Uri.parse(getString(R.string.navi_url));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }

                }
            });
            news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Intent intent = new Intent(view.getContext(), NewsActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });

            facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Uri uri = Uri.parse(getString(R.string.facebook));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });

            training.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Intent intent = new Intent(view.getContext(), TrainingActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });

            notifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Intent intent = new Intent(view.getContext(), NotificationsActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });

            page.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Uri uri = Uri.parse(getString(R.string.combatzone));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });

            seminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable()) {
                        Intent intent = new Intent(view.getContext(), SeminarActivity.class);
                        startActivityForResult(intent, 0);
                    }else{
                        Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }



}
