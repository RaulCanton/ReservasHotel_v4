package org.iesalandalus.programacion.reservashotel.Modelo;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

public interface IModelo {
    public void comenzar();

    public void terminar();
    public void insertar(Huesped huesped)throws OperationNotSupportedException;
    public Huesped buscar(Huesped huesped);
    public void borrar(Huesped huesped)throws OperationNotSupportedException;

    public  void insertar(Habitacion habitacion)throws OperationNotSupportedException;

    public Habitacion buscar(Habitacion habitacion)throws NullPointerException;

    public void borrar(Habitacion habitacion)throws OperationNotSupportedException;
    public List<Habitacion> getHabitaciones();
    public List <Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion);

    public void insertar(Reserva reserva)throws OperationNotSupportedException;

    public void borrar(Reserva reserva)throws OperationNotSupportedException;

    public Reserva buscar(Reserva reserva);
    public List<Huesped> getHuespedes();
    public List<Reserva> getReservas();
    public List<Reserva> getReservas (Huesped huesped)throws NullPointerException;
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion)throws NullPointerException;
    public List<Reserva> getReservasFuturas(Habitacion habitacion);
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha)throws NullPointerException, OperationNotSupportedException,IllegalArgumentException;
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha)throws NullPointerException, OperationNotSupportedException,IllegalArgumentException;







 }
