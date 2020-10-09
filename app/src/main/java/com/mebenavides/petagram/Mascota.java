package com.mebenavides.petagram;

public class Mascota {
    private int id;
    private String nombre;
    private int foto;
    private int rate;

    public Mascota(int foto, String nombre, int rate) {
        this.foto = foto;
        this.nombre = nombre;
        this.rate = rate;

    }

    public Mascota() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
