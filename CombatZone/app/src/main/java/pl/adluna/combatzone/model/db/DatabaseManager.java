package pl.adluna.combatzone.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by natalia on 10/09/14.
 */
public class DatabaseManager  extends SQLiteOpenHelper {

    public DatabaseManager(Context context) {
        super(context, "combatzone.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists time (timestamp text, notifications text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public Cursor getAll(SQLiteDatabase sqLiteDatabase){
       // sqLiteDatabase.execSQL("drop table time");
        sqLiteDatabase.execSQL("create table if not exists time (timestamp text, notifications text)");
        String columns[]={"timestamp", "notifications"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("time",columns,null,null,null,null,null);
        return cursor;
    }

    public boolean addTimestamp(String timestamp){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = getAll(getWritableDatabase());
        String notifications;
        ContentValues values = new ContentValues();
        while (cursor.moveToNext()) {
            notifications=cursor.getString(1);
            values.put("notifications",notifications);
        }
        values.put("timestamp",timestamp);
        db.execSQL("drop table time");
        getAll(db);
        return db.insert("time", null, values)>0;
    }

    public boolean changeNotifications(Boolean decision){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = getAll(getWritableDatabase());
        String timestamp;
        ContentValues values = new ContentValues();
        while (cursor.moveToNext()) {
            timestamp=cursor.getString(0);
            values.put("timestamp",timestamp);
        }
        values.put("notifications",decision.toString());
        db.execSQL("drop table time");
        getAll(db);
        return db.insert("time", null, values)>0;
    }
}
