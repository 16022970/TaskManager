package com.example.a16022970.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_SEC = "sec";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, " + COLUMN_DESC + " TEXT," + COLUMN_SEC + " TEXT" + ") ";
        db.execSQL(createTable);

//        db.execSQL("ALTER TABLE " + TABLE_TASK + " ADD COLUMN module_name TEXT ");
//        Log.i("info", "altered tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 1; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "Buy Milk");
            values.put(COLUMN_DESC, "Low Fat");
            values.put(COLUMN_SEC, "5");

            db.insert(TABLE_TASK, null, values);
        }
        Log.i("info", "dummy records inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }

    public long insertTask(String name, String description, String sec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC, description);
        values.put(COLUMN_SEC, sec);

        long result = db.insert(TABLE_TASK, null, values);
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert ", "" + result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT "
                + COLUMN_ID + ", "
                + COLUMN_NAME + ", "
                + COLUMN_DESC + ", "
                + COLUMN_SEC
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String sec = cursor.getString(3);

                Task obj = new Task(id, name,description, sec);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }




}
