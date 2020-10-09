package com.mebenavides.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.mebenavides.petagram.Contacto;
import com.mebenavides.petagram.Mascota;
import com.mebenavides.petagram.R;

import java.util.ArrayList;

public class ConstructorMascotas {
    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
        /*
        ArrayList<Mascota>mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.jirafa,"Pato", 3));
        mascotas.add(new Mascota(R.drawable.leon,"María", 5));
        mascotas.add(new Mascota(R.drawable.tigre,"Ralf", 1));
        mascotas.add(new Mascota(R.drawable.mono,"Mono", 0));
        mascotas.add(new Mascota(R.drawable.leon,"Lion", 2));
        */
        BaseDatos db = new BaseDatos(context);
        insertarMascotas(db);
        return db.obtenerTodasMascotas();
    }

    public void insertarMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_NOMBRE, "Pato");
        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_FOTO, R.drawable.jirafa);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_NOMBRE, "Juan");
        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_FOTO, R.drawable.leon);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_NOMBRE, "Mono");
        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_FOTO, R.drawable.mono);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_NOMBRE, "Ralf");
        contentValues.put(ConstantesBasesDatos.TABLE_MASCOTAS_FOTO, R.drawable.tigre);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBasesDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBasesDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
      BaseDatos db = new BaseDatos(context);
      return db.obtenerLikesMascota(mascota);
    }
}
