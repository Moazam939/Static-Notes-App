package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.notes.RecycleModel.Models;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Orders";
    private static final int DB_VERSION = 1;

    private static final String  KEY_ID = "id";
    private static final String TITLE = "title";
    private static final String NOTES = "notes";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" CREATE TABLE " + DB_NAME +
                " ( "  + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + TITLE + " Text ," + NOTES + " Text " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertion(String title, String notes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(NOTES, notes);
        long  db = sqLiteDatabase.insert(DB_NAME, null,values);

        if (db == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Models> selection(){
        ArrayList<Models> list = new ArrayList<Models>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ID,TITLE, NOTES FROM " + DB_NAME , null);
        if(cursor.moveToFirst()){
            do {
                Models models = new Models();
                models.setId(cursor.getString(0));
                models.setTitle(cursor.getString(1));
                models.setNotes(cursor.getString(2));

                list.add(models);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public Cursor getOrders(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(" SELECT * FROM Orders Where id = " + id , null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public boolean updation(String id, String title, String notes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(TITLE, title);
        values.put(NOTES, notes);
        long  db = sqLiteDatabase.update(DB_NAME, values, " id = ? ", new String[]{id});

        if (db == -1)
            return false;
        else
            return true;
    }

    public void deletion(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(DB_NAME,  KEY_ID + " = ? ", new String[]{id});
    }
}