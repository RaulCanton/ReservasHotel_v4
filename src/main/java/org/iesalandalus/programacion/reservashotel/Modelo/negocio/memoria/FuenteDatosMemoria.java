package org.iesalandalus.programacion.reservashotel.Modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Habitaciones;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Huespedes;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.Reservas;

public class FuenteDatosMemoria implements IFuenteDatos {
    @Override
    public IHuespedes crearHuespedes() {
        return new Huespedes();
    }

    @Override
    public IHabitaciones crearHabitaciones() {
        return new Habitaciones();
    }

    @Override
    public IReservas crearReservas() {
        return new Reservas();
    }
}
