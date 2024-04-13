package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHabitaciones;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHuespedes;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IReservas;

public class FuenteDatosMongoDB implements IFuenteDatos {


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
