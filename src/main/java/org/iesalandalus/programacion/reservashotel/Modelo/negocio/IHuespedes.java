package org.iesalandalus.programacion.reservashotel.Modelo.negocio;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface IHuespedes {

    public List<Huesped> get();

    public int getTamano();
    public void insertar (Huesped huesped)throws OperationNotSupportedException;
    public Huesped buscar (Huesped huesped);
    public void borrar (Huesped huesped)throws OperationNotSupportedException;
}
