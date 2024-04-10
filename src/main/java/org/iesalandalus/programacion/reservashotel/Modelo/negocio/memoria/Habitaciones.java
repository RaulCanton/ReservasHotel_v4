package org.iesalandalus.programacion.reservashotel.Modelo.negocio.memoria;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHabitaciones;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
public class Habitaciones implements IHabitaciones {
    private List<Habitacion> coleccionHabitacion;

    public Habitaciones(){
        coleccionHabitacion=new ArrayList<>();
    }
    @Override
    public List<Habitacion> get(){
        return coleccionHabitacion;
    }
    @Override
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: El tipo de habitación no puede ser nulo.");
        }

        List<Habitacion> habitacionTipoHabitacion = new ArrayList<>();
        for (Habitacion habitacion : coleccionHabitacion) {

            if (tipoHabitacion == TipoHabitacion.SIMPLE && habitacion instanceof Simple) {
                habitacionTipoHabitacion.add(new Simple((Simple) habitacion));
            } else if (tipoHabitacion == TipoHabitacion.DOBLE && habitacion instanceof Doble) {
                habitacionTipoHabitacion.add(new Doble((Doble) habitacion));

            } else if (tipoHabitacion == TipoHabitacion.TRIPLE && habitacion instanceof Triple) {
                habitacionTipoHabitacion.add(new Triple((Triple) habitacion));
                ;
            } else if (tipoHabitacion == TipoHabitacion.SUITE && habitacion instanceof Suite) {
                habitacionTipoHabitacion.add(new Suite((Suite) habitacion));
            }
        }
            return habitacionTipoHabitacion;
    }
    @Override
    public int getTamano() {

        return coleccionHabitacion.size();
    }
    @Override
    public void insertar (Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }

        if (!coleccionHabitacion.contains(habitacion))
        {
            coleccionHabitacion.add(habitacion);
        }
        else
        {
            throw new OperationNotSupportedException("ERROR:Ya existe una habitación con esos datos.");
        }
    }

    @Override
    public Habitacion buscar (Habitacion habitacion)throws NullPointerException{
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede buscar una habitación nula.");
        }

        int indice=coleccionHabitacion.lastIndexOf(habitacion);

        if (indice == -1){
            return null;
        }else{
            return coleccionHabitacion.get(indice);
        }

    }
    @Override
    public void borrar (Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        int indice=coleccionHabitacion.lastIndexOf(habitacion);

        if (indice == -1){
            throw new OperationNotSupportedException("ERROR: No existe ningúna habitación con ese nombre.");
        }else{
            coleccionHabitacion.remove(indice);
        }
    }
}
