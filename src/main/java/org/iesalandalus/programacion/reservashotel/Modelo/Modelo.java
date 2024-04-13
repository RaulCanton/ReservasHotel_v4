package org.iesalandalus.programacion.reservashotel.Modelo;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Habitaciones;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Huespedes;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Reservas;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Modelo implements IModelo {

    private IReservas reservas;
    private IHabitaciones habitaciones;
    private IHuespedes huespedes;
    private IFuenteDatos fuenteDatos;

    public Modelo(IFuenteDatos fuenteDatos){
        huespedes =fuenteDatos.crearHuespedes();
        habitaciones = fuenteDatos.crearHabitaciones();
        reservas =fuenteDatos.crearReservas();

    }

    public void comenzar() {
        huespedes.comenzar();
        habitaciones.comenzar();
        reservas.comenzar();
    }
    public void terminar(){
        huespedes.terminar();;
        habitaciones.terminar();
        reservas.terminar();
    }

    private void setFuenteDatos(IFuenteDatos fuenteDatos){
        this.fuenteDatos=fuenteDatos;

    }

    public List<Reserva> getReservas (Habitacion habitacion){
        return reservas.getReservas(habitacion);
    }

    public void insertar(Huesped huesped)throws OperationNotSupportedException{

            huespedes.insertar(huesped);
    }

    public Huesped buscar(Huesped huesped) {

        return huespedes.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException{

        huespedes.borrar(huesped);
    }

    public List<Huesped> getHuespedes(){

        return huespedes.get();
    }

    public  void insertar(Habitacion habitacion)throws OperationNotSupportedException{

            habitaciones.insertar(habitacion);
    }
    public Habitacion buscar(Habitacion habitacion)throws NullPointerException {

        return habitaciones.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException{

        habitaciones.borrar(habitacion);
        System.out.println("Se ha borrado la habitación.");
    }

    public List<Habitacion> getHabitaciones(){

        return habitaciones.get();
    }
    public List <Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){

        return habitaciones.get(tipoHabitacion);
    }


    public void insertar(Reserva reserva)throws OperationNotSupportedException{

            reservas.insertar(reserva);
    }
    public void borrar(Reserva reserva) throws OperationNotSupportedException{

        reservas.borrar(reserva);
    }

    public Reserva buscar(Reserva reserva) {

      return reservas.buscar(reserva);
    }

    public List<Reserva> getReservas(){

        return reservas.get();
    }
    public List<Reserva> getReservas (Huesped huesped)throws NullPointerException{

        return reservas.getReservas(huesped);
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion)throws NullPointerException{

        return reservas.getReservas(tipoHabitacion);
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion){

        return reservas.getReservasFuturas(habitacion);
    }

    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) throws NullPointerException, OperationNotSupportedException,IllegalArgumentException {

        reservas.realizarCheckin(reserva,fecha);
    }

    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) throws NullPointerException, OperationNotSupportedException,IllegalArgumentException {

        reservas.realizarCheckout(reserva,fecha);
    }

}
