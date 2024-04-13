package org.iesalandalus.programacion.reservashotel.Controlador;

import org.iesalandalus.programacion.reservashotel.Modelo.IModelo;
import org.iesalandalus.programacion.reservashotel.Modelo.Modelo;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.vista.Vista;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public class Controlador {

    private Vista vista;

    private IModelo modelo;


    public Controlador(IModelo modelo, Vista vista){

        if (modelo==null){
            throw new IllegalArgumentException("ERROR: el modelo no puede ser nulo.");
        }

        if (vista==null){
            throw new IllegalArgumentException("ERROR: La vista no puede ser nula");
        }

        this.modelo=modelo;
        this.vista=vista;
        this.vista.setControlador(this);
    }

    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }
    public void terminar(){
        modelo.terminar();
        vista.terminar();
    }
    public List<Reserva> getReservas(Habitacion habitacion){
       return modelo.getReservas(habitacion);
    }

    public void insertar(Huesped huesped)throws OperationNotSupportedException{
        modelo.insertar(huesped);
    }
    public Huesped buscar(Huesped huesped){
       return modelo.buscar(huesped);
    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        modelo.borrar(huesped);
    }

    public List<Huesped> getHuespedes(){
        return modelo.getHuespedes();
    }

    public void insertar(Habitacion habitacion)throws OperationNotSupportedException{
        modelo.insertar(habitacion);

    }
    public Habitacion buscar(Habitacion habitacion)throws NullPointerException{
        return modelo.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        modelo.borrar(habitacion);
    }

    public List<Habitacion> getHabitaciones (){
        return modelo.getHabitaciones();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion){
        return modelo.getHabitaciones();
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException{
        modelo.insertar(reserva);
    }

    public void borrar (Reserva reserva)throws OperationNotSupportedException{
        modelo.borrar(reserva);
    }
    public Reserva buscar(Reserva reserva){
        return modelo.buscar(reserva);

    }

    public List<Reserva> getReservas(){
        return modelo.getReservas();
    }
    public List<Reserva> getReservas(Huesped huesped)throws NullPointerException{
        return modelo.getReservas();
    }

    public List<Reserva> getResevas(TipoHabitacion tipoHabitacion)throws NullPointerException{
        return modelo.getReservas();
    }

    public List<Reserva> getReservasFuturas(Habitacion habitacion){
        return modelo.getReservasFuturas(habitacion);
    }

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha)throws NullPointerException,OperationNotSupportedException,IllegalArgumentException{
        modelo.realizarCheckin(reserva,fecha);
    }
    public void realizarCheckOut(Reserva reserva,LocalDateTime fecha)throws OperationNotSupportedException,NullPointerException,IllegalArgumentException{
        modelo.realizarCheckout(reserva,fecha);
    }

}
