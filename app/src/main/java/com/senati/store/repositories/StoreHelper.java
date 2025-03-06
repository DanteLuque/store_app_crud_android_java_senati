package com.senati.store.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.senati.store.models.StoreModel;

public class StoreHelper extends SQLiteOpenHelper {
    private static final String DATABASE="DBSTORE";
    private static final String TABLE_NAME="items";
    private  static final int VERSION=1;

    public StoreHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "DESARROLLADOR TEXT NOT NULL, " +
                        "NOMBRE TEXT NOT NULL," +
                        "LICENCIA TEXT NOT NULL," +
                        "VERSION INT NOT NULL," +
                        "ESPACIO_MB INT NOT NULL," +
                        "PRECIO REAL" +
                        ")";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public void addData(
            String desarrollador,
            String nombre,
            String licencia,
            int version,
            int espacioMb,
            double precio
    ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("DESARROLLADOR",desarrollador);
        values.put("NOMBRE",nombre);
        values.put("LICENCIA",licencia);
        values.put("VERSION",version);
        values.put("ESPACIO_MB",espacioMb);
        values.put("PRECIO",precio);
        database.insert(TABLE_NAME,null,values);
    }

    public Cursor showData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery(
                "SELECT * FROM "+TABLE_NAME+" ORDER BY ID DESC ",
                null);
        return cursor;
    }

    public void deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    public void updateData(
            String desarrollador,
            String nombre,
            String licencia,
            int version,
            int espacioMb,
            double precio,
            String id
    ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("DESARROLLADOR",desarrollador);
        values.put("NOMBRE",nombre);
        values.put("LICENCIA",licencia);
        values.put("VERSION",version);
        values.put("ESPACIO_MB",espacioMb);
        values.put("PRECIO",precio);

        sqLiteDatabase.update(
                TABLE_NAME,values,"ID=?",
                new String[]{id}
        );
    }


}
