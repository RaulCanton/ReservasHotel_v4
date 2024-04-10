package org.iesalandalus.programacion.reservashotel.Modelo.negocio;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IHabitaciones {

    public List<Habitacion> get();
    public List<Habitacion> get(TipoHabitacion tipoHabitacion);

    public int getTamano();
    public void insertar (Habitacion habitacion)throws OperationNotSupportedException;
    public Habitacion buscar (Habitacion habitacion);
    public void borrar (Habitacion habitacion) throws OperationNotSupportedException;

}
