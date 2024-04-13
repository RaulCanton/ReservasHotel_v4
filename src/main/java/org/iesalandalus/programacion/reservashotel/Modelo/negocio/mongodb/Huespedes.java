package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHuespedes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades.MongoDB;


import static com.mongodb.client.model.Filters.eq;

public class Huespedes implements IHuespedes {

    private static final String COLECCION = "huespedes";

    private MongoCollection<Document> colecccionHuespedes;

    public Huespedes () { }

    @Override
    public void comenzar() {
        colecccionHuespedes = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    @Override
    public List<Huesped> get() {
        List<Huesped> huespedes = new ArrayList<>();
        Document docOrdenDni = new Document().append(MongoDB.DNI, 1);
        for (Document documentoHuesped : colecccionHuespedes.find().sort(docOrdenDni)) {
            huespedes.add(MongoDB.getHuesped(documentoHuesped));
        }
        return huespedes;
    }

    @Override
    public int getTamano() {
        return (int)colecccionHuespedes.countDocuments();
    }

    @Override
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede insertar un huésped nulo.");
        }
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("El huésped ya existe.");
        } else {
            colecccionHuespedes.insertOne(MongoDB.getDocumento(huesped));
        }
    }

    @Override
    public Huesped buscar(Huesped huesped) {
        Document buscoDni = new Document().append(MongoDB.DNI,huesped.getDni());
        Document documentoHuesped = colecccionHuespedes.find(buscoDni).first();

        return MongoDB.getHuesped(documentoHuesped);

    }

    @Override
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede borrar un huésped nulo.");
        }

        if (buscar(huesped) != null) {
            colecccionHuespedes.deleteOne(eq(MongoDB.DNI,huesped.getDni()));
        } else {
            throw new OperationNotSupportedException("El huésped a borrar no existe.");
        }
    }
}
