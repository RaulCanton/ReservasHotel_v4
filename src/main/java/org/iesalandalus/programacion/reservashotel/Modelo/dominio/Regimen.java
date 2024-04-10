package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

public enum Regimen {

    SOLO_ALOJAMIENTO("Solo alojamiento",0),ALOJAMIENTO_DESAYUNO("Desayuno incluido",15),
    MEDIA_PENSION("Media pensión",30),PENSION_COMPLETA("Pensión completa",50);

     String descripcion;
     double incrementoPrecio;
    private Regimen(String descripcion, double incrementoPrecio){
        this.descripcion=descripcion;
        this.incrementoPrecio=incrementoPrecio;
    }

    public double getIncrementoPrecio(){
        return incrementoPrecio;
    }

    public String toString(){
        return String.format(descripcion,getIncrementoPrecio());
    }
}
