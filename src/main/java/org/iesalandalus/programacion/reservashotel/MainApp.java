package org.iesalandalus.programacion.reservashotel;


import org.iesalandalus.programacion.reservashotel.Controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.Modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservashotel.Modelo.IModelo;
import org.iesalandalus.programacion.reservashotel.Modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

public class MainApp {

    public static void main(String[] args) {
        try {

            IModelo modelo = new Modelo(procesarArgumentosFuenteDatos(args));
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo, vista);
            controlador.comenzar();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static IFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
        IFuenteDatos fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
        for (String argumento : args) {
            if (argumento.equalsIgnoreCase("-fdmongodb")) {
                fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
            } else if (argumento.equalsIgnoreCase("-fdmemoria")) {
                fuenteDatos = FactoriaFuenteDatos.MEMORIA.crear();
            }
        }
        return fuenteDatos;
    }
}