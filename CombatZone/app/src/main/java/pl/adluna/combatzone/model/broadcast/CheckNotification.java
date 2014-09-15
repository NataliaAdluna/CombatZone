package pl.adluna.combatzone.model.broadcast;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import pl.adluna.combatzone.model.db.DatabaseManager;
import pl.adluna.combatzone.model.network.GetNotificationTask;
import pl.adluna.combatzone.model.service.Notifications;


/**
 * Created by Natalia Stawowy on 12/08/14.
 */
public class CheckNotification extends BroadcastReceiver {
    private String date ="yes";
    private String new1="no";
    String timestamp;

    @Override
    public void onReceive(final Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        final DatabaseManager db = new DatabaseManager(context);
        db.getWritableDatabase();
        Cursor cursor=db.getAll(db.getReadableDatabase());
        while(cursor.moveToNext())
        {
            if(Boolean.valueOf(cursor.getString(1))){
                if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

                    if (isNetworkAvailable(context)) {
                        checkAndNotify(context);
                    }

                    CharSequence text = "Hello toast!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    //set new alarm after reboot
                    setAlarm(context);
                } else {
                    String state = intent.getStringExtra("from");
                    if (state != null && state.equals("combatzone")) {
                        if (isNetworkAvailable(context)) {
                            checkAndNotify(context);
                        }
                    }
                }
            } else
            {
                cancelAlarm(context);
            }
        }

        wl.release();
    }
    public void checkAndNotify(final Context context){
        GetNotificationTask dbTask = new GetNotificationTask("http://www.adluna.webd.pl/combatzone_panel/notification.php") {
            @Override
            protected void onPostExecute(JSONObject obj) {
                try {
                    final DatabaseManager db = new DatabaseManager(context);
                    Cursor cursor = db.getAll(db.getWritableDatabase());
                    while (cursor.moveToNext()) {
                        timestamp=cursor.getString(0);
                    }

                    JSONArray jsonObject = obj.getJSONArray("news");
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject object = jsonObject.getJSONObject(i);
                        date = object.getString("data");
                        if(stringToTimestamp(date).compareTo(Timestamp.valueOf(timestamp))>0){
                            db.addTimestamp(String.valueOf(CheckNotification.stringToTimestamp(date)));
                            context.startService(new Intent(context, Notifications.class));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        dbTask.execute();

    }

    public static Timestamp stringToTimestamp(String date){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            return new java.sql.Timestamp(parsedDate.getTime());
        }catch(Exception e){

        }
        return null;
    }

    public static void setAlarm(Context context)
    {

        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CheckNotification.class);
        intent.putExtra("from","combatzone");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000  *  15 , pi);
    }

    public static void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, CheckNotification.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}