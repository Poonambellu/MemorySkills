package com.example.memoryskills;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper( context );
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess( context );
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public int Verify(String name) {
        this.db = openHelper.getWritableDatabase();
        int Date_Of_Birth =0;
        c = db.rawQuery( "Select Date_Of_Birth from tblUser_Details where User_Name= '" + name + " ' AND  Date_Of_Birth = '" + Date_Of_Birth + "'", new String[]{} );
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String Date_of_Birth = c.getString( 0 );


        }
        return (c.getCount());
    }


    public boolean insertData(String User_Name, String Date_Of_Birth){
        this.db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues( );


        contentValues.put("User_Name", User_Name);
        contentValues.put("Date_Of_Birth",Date_Of_Birth);
        long result = db.insert("tblUser_Details",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


}
