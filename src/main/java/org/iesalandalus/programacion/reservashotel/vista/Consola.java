package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.chrono.JapaneseEra.values;

public class Consola {

    private Consola(){

    }

    public static void mostrarMenu(){

        for( Opcion opcion:Opcion.values()){
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int numOpcion;
        do {
            System.out.println("Elige una opción: ");
            numOpcion = Entrada.entero();

        } while (numOpcion < 0 && numOpcion >= values().length - 1);
        return Opcion.values()[numOpcion];

    }

    public static Huesped leerHuesped() {

        System.out.print("Introduce el nombre del huésped: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce el dni del cliente: ");
        String dni = Entrada.cadena();
        if (Huesped.comprobarLetraDni(dni)){
            dni=dni;
        }

        System.out.print("Introduce el teléfono del huésped: ");
        String telefono = Entrada.cadena();
        if (telefono.matches(Huesped.ER_TELEFONO)){
            telefono=telefono;
        }else {
            throw new IllegalArgumentException("ERROR: El teléfono del huésped no tiene un formato válido.");
        }
        System.out.print("Introduce el correo del huésped: ");
        String correo = Entrada.cadena();

        if (correo.matches(Huesped.ER_CORREO)){
            correo=correo;
        }
        else {
            throw  new IllegalArgumentException("ERROR: El correo del huésped no tiene un formato válido.");
        }
        System.out.print("Introduce la fecha de nacimiento del huésped en formato dia/mes/año: ");
        String fechaNacimiento = Entrada.cadena();

        return new Huesped (nombre,dni,telefono,correo,LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA)));

    }
    public static Huesped getHuespedPorDni()throws NullPointerException{



        String dni;

        LocalDate formatoDia = LocalDate.parse("23/07/1980");

        do {
            System.out.print("Introduce el dni del cliente: ");
            dni = Entrada.cadena();
        } while (dni.equals(""));

       return new Huesped("Pepito Perez Perez",dni,"900101010","loquesea@gmail.com",formatoDia);

    }


    public static Habitacion leerHabitacion()throws IllegalArgumentException{

        int opcion;
        int planta;
        int puerta;
        double precio;

        do {
            System.out.print("Introduce el número de planta. ");
            planta = Entrada.entero();
        }while (planta <0 || planta>3);
        do {
            System.out.print("Introduce el número de puerta. ");
            puerta = Entrada.entero();
        }while (puerta <0 || puerta>14);
        do {
            System.out.print("Introduce el precio de la habitación. ");
            precio = Entrada.entero();
        }while (precio <40 || precio>150);
        String identificador=(String.format("%d%d",planta,puerta));

        do{
            System.out.println("Elige una opción para el tipo de habitación:");
            System.out.println("1.- Simple");
            System.out.println("2.- Doble");
            System.out.println("3.- triple");
            System.out.println("4.- Suite");

            opcion=Entrada.entero();

        }while (opcion<=0 || opcion>4);

        switch (opcion){
            case 1:
                return new Simple(planta,puerta,precio,identificador);
            case 2:
                return new Doble(planta,puerta,precio,identificador,1,2);
            case 3:
                return new Triple(planta,puerta,precio,identificador,2,3,0);
            case 4:
                return new Suite(planta,puerta,precio,identificador,1,true);
            default:
                throw new IllegalArgumentException("El tipo de habitación no es correcto.");
        }

    }
    public static Habitacion leerHabitacionPorIdentificador(){
        int planta;
        int puerta;

        do {
            System.out.print("Introduce el número de planta. ");
            planta = Entrada.entero();

        }while (planta <0 || planta>3);
        do {
            System.out.print("Introduce el número de puerta. ");
            puerta = Entrada.entero();
        }while (puerta <0 || puerta>14);
        String identificador=(String.format("%d%d",planta,puerta));
        return new Simple(planta,puerta,40,identificador);

    }

    public static TipoHabitacion leerTipoHabitacion(){
        int tipoHabi;
        do {
            System.out.print("Introduce el tipo de habitación 1.-SUITE 2.-SIMPLE 3.-DOBLE 4.-TRIPLE .");
            tipoHabi = Entrada.entero();
        } while (tipoHabi < 1 && tipoHabi > 4);
        return TipoHabitacion.values()[tipoHabi];

    }

    public static Regimen leerRegimen(){
        int tipoRegi;
        do {
            System.out.print("Introduce el tipo de habitación 1.-SOLO ALOJAMIENTO " +
                    "2.-ALOJAMIENTO DESAYUNO 3.-MEDIA_PENSION 4.-PENSION_COMPLETA .");
            tipoRegi = Entrada.entero();
        } while (tipoRegi < 1 || tipoRegi > 4);
        return Regimen.values()[tipoRegi];

    }
    public static Reserva leerReserva() {
        int numeroPersonas;
        Huesped huesped = new Huesped(Consola.leerHuesped());
        Habitacion habitacion = Consola.leerHabitacion();
        Regimen regimen;
        regimen=Consola.leerRegimen();

        LocalDate fechaInicioReserva=leerFecha("Introduce la fecha de inicio de la reserva en formato dia/mes/año:");

        LocalDate fechaFinReserva = leerFecha("Introduce la fecha de fin de la reserva en formato dia/mes/año:");

        System.out.print("Introduce el número de personas. ");
        numeroPersonas = Entrada.entero();

        return new Reserva(huesped,habitacion,regimen,fechaInicioReserva,fechaFinReserva,numeroPersonas);

    }
    public static LocalDate leerFecha(String mensaje)throws DateTimeParseException {

        LocalDate fechaTiempo=null;

        do {
            System.out.print(mensaje);
            String fecha = Entrada.cadena();
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA);
            fechaTiempo = LocalDate.parse(fecha, formatoFecha);
        } while (fechaTiempo==null);
        return fechaTiempo;
    }
    public static LocalDateTime leerFechaHora(String mensaje) throws DateTimeParseException {
        LocalDateTime fechaHora=null;

        do {

            System.out.println(mensaje);
            String fechaHoraTiempo = Entrada.cadena();

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            fechaHora = LocalDateTime.parse(fechaHoraTiempo, formatoFecha);
        }while (fechaHora==null);

        return fechaHora;
    }
}