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

    public void addData(StoreModel storeModel){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",storeModel.getId());
        values.put("DESARROLLADOR",storeModel.getDesarrollador());
        values.put("NOMBRE",storeModel.getNombre());
        values.put("LICENCIA",storeModel.getLicencia());
        values.put("VERSION",storeModel.getVersion());
        values.put("ESPACIO_MB",storeModel.getEspacioMb());
        values.put("PRECIO",storeModel.getPrecio());
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

    public void updateData(StoreModel storeModel){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("DESARROLLADOR",storeModel.getDesarrollador());
        values.put("NOMBRE",storeModel.getNombre());
        values.put("LICENCIA",storeModel.getLicencia());
        values.put("VERSION",storeModel.getVersion());
        values.put("ESPACIO_MB",storeModel.getEspacioMb());
        values.put("PRECIO",storeModel.getPrecio());

        sqLiteDatabase.update(
                TABLE_NAME,values,"ID=?",
                new String[]{String.valueOf(storeModel.getId())}
        );
    }


}
