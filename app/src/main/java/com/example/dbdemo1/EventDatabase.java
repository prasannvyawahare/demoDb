package com.example.dbdemo1;

import static com.example.dbdemo1.EventDatabaseInfo.EVENT;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import net.sqlcipher.database.DatabaseObjectNotClosedException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventDatabase extends SQLiteOpenHelper {
    private static final String TAG = EventDatabase.class.getSimpleName();
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "eventStore";
    private static final String PASSWORD = "K2smedLa5efKaYAQ";
    private SQLiteDatabase mEventDatabase;
    private static final String DATABASE_ALTER_TEAM = "ALTER TABLE "
            + EVENT + " ADD COLUMN " + " TEXT;";

    public void saveEvents(Profile event) {

        ContentValues values = new ContentValues();
        values.put(EventDatabaseInfo.Event.MAIN_ENTITY, event.getName());
        values.put(EventDatabaseInfo.Event.PAYLOAD, event.getAge());

        mEventDatabase.insertOrThrow(EVENT, null, values);
    }
    @SuppressLint("Range")
    public List<Profile> getAllEarlierEvent() {
        List<Profile> uhxEventList = new ArrayList<>();
        Cursor cursor = mEventDatabase.rawQuery("SELECT * FROM " + EVENT + " ORDER BY id", null);
        try {
            if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                do {
                    Profile uhxEvent = new Profile();
                    uhxEvent.setName(cursor.getString(cursor.getColumnIndex(EventDatabaseInfo.Event.MAIN_ENTITY)));
                    uhxEvent.setAge(cursor.getInt(cursor.getColumnIndex(EventDatabaseInfo.Event.PAYLOAD)));
                    uhxEvent.setId(cursor.getInt(cursor.getColumnIndex(EventDatabaseInfo.Event.ID)));
                    uhxEventList.add(uhxEvent);
                } while (cursor.moveToNext());
            } else {
                return uhxEventList;
            }
        } finally {
            cursor.close();
        }
        return uhxEventList;
    }

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase.loadLibs(context);
        try {
            if (mEventDatabase != null && mEventDatabase.isOpen()) {
                mEventDatabase.close();
            }
            mEventDatabase = getWritableDatabase(PASSWORD);
        } catch (DatabaseObjectNotClosedException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("logmeee",e.getMessage());
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(EventDatabaseInfo.CREATE_TABLE_QUERY);
            Log.d("DATABASE", "tablecreate");
        } catch (Exception e) {
                Log.d("Database",  "Table not created");
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL(DATABASE_ALTER_TEAM);
        }
    }
}

