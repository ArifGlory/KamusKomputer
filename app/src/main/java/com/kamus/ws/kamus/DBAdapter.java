package com.kamus.ws.kamus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glory on 28/09/2016.
 */
public class DBAdapter extends SQLiteAssetHelper {

    private static final String DB_NAME                 = "db_kamus3";
    private static final int        DB_VER                  = 1;
    public static final String TABLE_SEMUA              = "tb_semua";
    public static final String COL_ID             = "id";
    public static final String COL_NAMA           = "nama";
    public static final String COL_DESKRIPSI      = "deskripsi";
    public static final String COL_KATEGORI      = "kategori";
    public static final String COL_IS_BOOKMARK      = "isbookmark";
    public static final String COL_GAMBAR      = "gambar";

    public static DBAdapter        dbInstance;
    public static SQLiteDatabase db;



    /**
     * private Constructor, untuk menggunakan kelas ini gunakan getInstance()
     * @param context
     */


    private DBAdapter(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    public  static synchronized DBAdapter getInstance(Context context){

        if (dbInstance == null){
            dbInstance = new DBAdapter(context.getApplicationContext());
            db = dbInstance.getReadableDatabase();
        }

        return dbInstance;
    }

    public SQLiteDatabase ambilDB(){
        db = this.getWritableDatabase();
        return db;

    }

    public void getSemuaKata(){

    }

   /* public DBAdapter getInstance(Context context){

        if (dbInstance == null){

            dbInstance = new DBAdapter(context);
            db = dbInstance.getReadableDatabase();

        }

        return  dbInstance;
    }*/



    @Override
    public synchronized void close(){

        super.close();
        if (dbInstance!=null){

            dbInstance.close();
        }
    }


//method untuk mengambil semua data soal





}
