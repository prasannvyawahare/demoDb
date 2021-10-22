package com.example.dbdemo1;

/**
 * Created by leena on 9/5/18.
 */

public class EventDatabaseInfo {
    public static final String EVENT = "event";
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + EVENT + "(" +
            Event.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Event.MAIN_ENTITY + " TEXT," +
            Event.HEADERS + " TEXT," +
            Event.RETRY_COUNT + " TEXT," +
            Event.PAYLOAD + " TEXT)";

    public static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + EVENT;

    interface Event {
        String ID = "id";
        String MAIN_ENTITY = "main_entity";
        String PAYLOAD = "payload";
        String RETRY_COUNT = "retry_count";
        String HEADERS = "headers";
    }


}
