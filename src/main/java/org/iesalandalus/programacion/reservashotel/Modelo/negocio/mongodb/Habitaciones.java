package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHabitaciones;

import com.mongodb.client.MongoCollection;
import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades.MongoDB;

import static com.mongodb.client.model.Filters.eq;

public class Habitaciones implements IHabitaciones {

    private static final String COLECCION = "habitaciones";

    private MongoCollection<Document> coleccionHabitaciones;

    public Habitaciones(){}

    @Override
    public void comenzar() {
        coleccionHabitaciones = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    @Override
    public List<Habitacion> get() {
        List<Habitacion> habitaciones = new ArrayList<>();
        Document docOrdenIden = new Document().append(MongoDB.IDENTIFICADOR, 1);
        for (Document documentoHabitacion : coleccionHabitaciones.find().sort(docOrdenIden)) {
            habitaciones.add(MongoDB.getHabitacion(documentoHabitacion));
        }
        return habitaciones;
    }

    @Override
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        return null;
    }


    @Override
    public int getTamano() {
        return (int)coleccionHabitaciones.countDocuments();
    }
    @Override
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede insertar una habitaci�n nula.");
        }
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("La habitaci�n ya existe.");
        } else {
            coleccionHabitaciones.insertOne(MongoDB.getDocumento(habitacion));
        }
    }

    @Override
    public Habitacion buscar(Habitacion habitacion) {
        Document buscoIden = new Document().append(MongoDB.IDENTIFICADOR,habitacion.getIdentificador());
        Document documentoHabitacion = coleccionHabitaciones.find(buscoIden).first();

        return MongoDB.getHabitacion(documentoHabitacion);

    }

    @Override
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede borrar una habitaci�n nula.");
        }

        if (buscar(habitacion) != null) {
            coleccionHabitaciones.deleteOne(eq(MongoDB.IDENTIFICADOR,habitacion.getIdentificador()));
        } else {
            throw new OperationNotSupportedException("La habitaci�n a borrar no existe.");
        }
    }
}
