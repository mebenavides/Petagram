package com.mebenavides.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mebenavides.petagram.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBasesDatos.DATABASE_NAME, null, ConstantesBasesDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBasesDatos.TABLE_MASCOTAS + "(" +
                ConstantesBasesDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBasesDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBasesDatos.TABLE_MASCOTAS_FOTO + " INTEGER" + ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBasesDatos.TABLE_LIKES_MASCOTA + "(" +
                ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " INTEGER, " +
                ConstantesBasesDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBasesDatos.TABLE_MASCOTAS + "(" + ConstantesBasesDatos.TABLE_MASCOTAS_ID + ")" +
                ")";
        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + ConstantesBasesDatos.TABLE_LIKES_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST" + ConstantesBasesDatos.TABLE_MASCOTAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBasesDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while(registros.moveToNext()){
            Mascota mascotaAcutal = new Mascota();
            mascotaAcutal.setId(registros.getInt(0));
            mascotaAcutal.setNombre(registros.getString(1));
            mascotaAcutal.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesBasesDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstantesBasesDatos.TABLE_LIKES_MASCOTA +
                    " WHERE " + ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " = " + mascotaAcutal.getId();
            Cursor registrosLikes = db.rawQuery(queryLikes, null);

            if (registrosLikes.moveToNext()) {
                mascotaAcutal.setRate(registrosLikes.getInt(0));
            }else{
                mascotaAcutal.setRate(0);
            }
            mascotas.add(mascotaAcutal);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBasesDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBasesDatos.TABLE_LIKES_MASCOTA, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query = "SELECT COUNT(" + ConstantesBasesDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBasesDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " = " + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }
}
