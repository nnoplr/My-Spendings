package com.novika.myspending;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME = "DBSpendings";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "Spendings";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_NOMINAL = "nominal";
    public static final String FIELD_DATE = "date";

    private static String CREATE_SPENDINGS = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_NAME + " TEXT," +
            FIELD_NOMINAL + " INTEGER," +
            FIELD_DATE + " TEXT)";

    private static final String DROP_SPENDINGS = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SPENDINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_SPENDINGS);
        onCreate(db);
    }
}
