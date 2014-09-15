package pl.adluna.combatzone.model.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import pl.adluna.combatzone.R;
import pl.adluna.combatzone.view.activity.NewsActivity;


/**
 * Created by Natalia Stawowy on 04/09/14.
 */
public class Notifications extends IntentService {
    private String sub = "yes";
    private String new1 = "no";
    private Context context;
    Intent intent1;

    public Notifications() {
        super("name");
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    private void showNotification(Intent intent) {

        // Look up the notification manager server
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        intent1 = new Intent(this, NewsActivity.class);
        String contentTitle = "CombatZone";
        String contentText = "Sprawdź nowe aktualności";
        intent1.putExtra("from", "combatzone");

        // Sets an ID for the notification, so it can be updated
        int notifyID = 1;

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        // Because the ID remains unchanged, the existing notification is
        // updated.

        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());


        Vibrator mVibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(300);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification(intent);
        return START_STICKY;
    }

}
