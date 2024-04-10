package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

public enum TipoHabitacion {

SUITE("SUITE"),SIMPLE("SIMPLE"),DOBLE("MEDIANA"),TRIPLE("TRIPLE");

    private String cadenaAMostrar;

    private TipoHabitacion(String cadenaAMostrar){

        this.cadenaAMostrar=cadenaAMostrar;
    }

     public String toString(){
        return cadenaAMostrar;
    }
}



