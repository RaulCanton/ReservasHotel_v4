package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IHuespedes;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades.MongoDB;

public class Huespedes implements IHuespedes {

    private List<Huesped> coleccionHuesped;

    private static final String COLECCION = "hu�spedes";

    private MongoCollection<Document> colecccionHuespedes;

    public Huespedes () {
       coleccionHuesped = new ArrayList<>();
    }

    @Override
    public void comenzar() {
        colecccionHuespedes = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    public List<Huesped> get(){
       return coleccionHuesped;
    }


    @Override
    public int getTamano() {
        return coleccionHuesped.size();
    }

    @Override
    public void insertar (Huesped huesped) throws OperationNotSupportedException{
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }
        int indice=coleccionHuesped.indexOf(huesped);
        if (indice==-1){
            coleccionHuesped.add(new Huesped(huesped));
        } else {
            throw new OperationNotSupportedException("ERROR:Ya existe un hu�sped con esos datos.");
        }
    }
    @Override
    public Huesped buscar (Huesped huesped){
        Huesped huespedEncontrado= null;
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un hu�sped nulo.");
        }

        if (coleccionHuesped.contains(huesped)){
            huespedEncontrado=new Huesped(coleccionHuesped.get(coleccionHuesped.indexOf(huesped)));
        }
            return huespedEncontrado;
    }
    @Override
    public void borrar (Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

        if (coleccionHuesped.contains(huesped)) {
            coleccionHuesped.remove(huesped);
        } else {
            throw new OperationNotSupportedException("ERROR:No existe el hu�sped a borrar.");
        }
    }
}
