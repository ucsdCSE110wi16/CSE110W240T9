package com.cse110.apk404.myCalendar.eventListHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

import java.io.Serializable;

public class CalendarSQLiteDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public CalendarSQLiteDB(Context context) {
        super(context, "cal123", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE calendar_db (email TEXT PRIMARY KEY, "
                + "password TEXT, data BLOB)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS calendar_db");

        // Create tables again
        onCreate(db);
    }

    public void addUser(CalendarUser user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        try {
            values.put("data", Serializer.serialize(user.getData()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        db.insert("calendar_db", null, values);
        db.close();
    }

    public CalendarUser getUserByEmail(String email) {
        email = email.replace('@', '_');

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM calendar_db WHERE email LIKE '" + email + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();

        ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list = null;
        try {
            list = (ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>>) Serializer.deserialize(cursor.getBlob(2));
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        CalendarUser user = new CalendarUser(cursor.getString(0), cursor.getString(1), list);

        return user;
    }

    public ArrayList<String> getAllUserEmail() {
        ArrayList<String> userList = new ArrayList<String>();
        String selectQuery = "SELECT email FROM calendar_db ORDER BY email";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        do {
            userList.add(cursor.getString(0));
        } while (cursor.moveToNext());

        db.close();
        return userList;
    }

    public void updateUserData(CalendarUser user) {
        SQLiteDatabase db = this.getWritableDatabase();

        String deleteQuery = "DELECT FROM calendar_db WHERE email = " + user.getEmail();
        db.rawQuery(deleteQuery, null);
        db.close();

        addUser(user);
    }
}