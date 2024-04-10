package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.Controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.Modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {

    public static void main(String[] args)  {
        try {

            Modelo modelo = new Modelo();
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo, vista);
            controlador.comenzar();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
}
