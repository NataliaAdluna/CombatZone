package pl.adluna.combatzone.view.activity;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.model.broadcast.CheckNotification;
import pl.adluna.combatzone.model.db.DatabaseManager;
import pl.adluna.combatzone.model.network.GetNotificationTask;

/**
 * Created by Natalia Stawowy on 15/09/14.
 */
public class NotificationsActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);

        final CheckBox notificationsCheckBox = (CheckBox) findViewById(R.id.notificationsCheckBox);
        final DatabaseManager db = new DatabaseManager(getApplicationContext());
        db.getWritableDatabase();
        Cursor cursor=db.getAll(db.getReadableDatabase());
        while(cursor.moveToNext()) {
            if (Boolean.valueOf(cursor.getString(1)))
                notificationsCheckBox.setChecked(true);
        }


        notificationsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseManager db = new DatabaseManager(getApplicationContext());
                db.getWritableDatabase();
                db.changeNotifications(notificationsCheckBox.isChecked());
                if(notificationsCheckBox.isChecked())
                    startNotify();
            }
        });
    }

    public void startNotify(){
        if (isNetworkAvailable()) {
            GetNotificationTask dbTask = new GetNotificationTask("http://www.adluna.webd.pl/combatzone_panel/notification.php") {
                @Override
                protected void onPostExecute(JSONObject obj) {
                    try {
                        JSONArray jsonObject = obj.getJSONArray("news");
                        for (int i = 0; i < jsonObject.length(); i++) {
                            JSONObject object = jsonObject.getJSONObject(i);
                            String date = object.getString("data");

                            final DatabaseManager db = new DatabaseManager(getApplicationContext());
                            db.getWritableDatabase();
                            db.addTimestamp(String.valueOf(CheckNotification.stringToTimestamp(date)));
                            // db.addTimestamp("1389297600");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            dbTask.execute();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.wifi_connection)
                    , Toast.LENGTH_SHORT).show();
        }


        CheckNotification.setAlarm(this);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
