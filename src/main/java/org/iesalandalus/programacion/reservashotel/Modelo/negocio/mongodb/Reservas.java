package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades.MongoDB;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class Reservas implements IReservas {

    private static final String COLECCION = "reservas";

    private MongoCollection<Document> coleccionReservas;



    public Reservas (){ }

    @Override
    public void comenzar() {
        coleccionReservas = MongoDB.getBD().getCollection(COLECCION);
    }

    @Override
    public void terminar() {
        MongoDB.cerrarConexion();
    }

    @Override
    public List<Reserva> get() {
        List<Reserva> reservas = new ArrayList<>();
        Document docOrdenFecgaIni = new Document().append(MongoDB.FECHA_INICIO_RESERVA, 1);
        for (Document documentoReserva : coleccionReservas.find().sort(docOrdenFecgaIni)) {
            reservas.add(MongoDB.getReserva(documentoReserva));
        }
        return reservas;
    }

    @Override
    public int getTamano() {
        return (int)coleccionReservas.countDocuments();
    }


    @Override
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede insertar una reserva nula.");
        }
        if (buscar(reserva) != null) {
            throw new OperationNotSupportedException("La reserva ya existe.");
        } else {
            coleccionReservas.insertOne(MongoDB.getDocumento(reserva));
        }
    }

    @Override
    public Reserva buscar(Reserva reserva) {
        Document buscoReserva = new Document().append(MongoDB.HUESPED,MongoDB.getDocumento(reserva.getHuesped()));
        Document documentoReserva = coleccionReservas.find(buscoReserva).first();

        return MongoDB.getReserva(documentoReserva);

    }


    @Override
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede borrar una reserva nula.");
        }

        if (buscar(reserva) != null) {
            coleccionReservas.deleteOne(eq(MongoDB.HUESPED,reserva.getHuesped()));
        } else {
            throw new OperationNotSupportedException("La reserva a borrar no existe.");
        }
    }


    @Override
    public List<Reserva> getReservas(Huesped huesped)throws NullPointerException {
        if (huesped == null) {
            throw new IllegalArgumentException("No se puede obtener una reserva nula.");
        }
        List<Reserva> reservashuesped = new ArrayList<>();
        Document buscoResHuesped = new Document().append(MongoDB.HUESPED,MongoDB.getDocumento(huesped));
        for (Document documentoReserva : coleccionReservas.find(buscoResHuesped)) {
            reservashuesped.add(MongoDB.getReserva(documentoReserva));
        }
        return reservashuesped;
    }



    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion)throws NullPointerException{
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        List <Reserva> reservasTipoHabitacion = new ArrayList<>();
        Document buscoResTipo = new Document().append(MongoDB.TIPO,tipoHabitacion.toString());
        for (Document documentoReserva : coleccionReservas.find(buscoResTipo)) {
            if (tipoHabitacion.equals(reservasTipoHabitacion)) {
                reservasTipoHabitacion.add(MongoDB.getReserva(documentoReserva));
            }
        }
        return reservasTipoHabitacion;
    }

    public List<Reserva> getReservas(Habitacion habitacion)throws NullPointerException{
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        List <Reserva> reservasHabitacion = new ArrayList<>();
        Document buscoResHabitacion = new Document().append(MongoDB.HABITACION_IDENTIFICADOR,habitacion.getIdentificador());
        for (Document documentoReserva : coleccionReservas.find(buscoResHabitacion)) {
            Habitacion habitacionReserva = MongoDB.getHabitacion(documentoReserva.get(MongoDB.HABITACION,Document.class));
            if (habitacionReserva.getIdentificador().equals(habitacion.getIdentificador())) {
                reservasHabitacion.add(MongoDB.getReserva(documentoReserva));
            }
        }
        return reservasHabitacion;
    }
    public List<Reserva> getReservasFuturas (Habitacion habitacion){
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        List<Reserva> reservasHabitacion = new ArrayList<>();

        for (Document documentoReserva : coleccionReservas.find()) {
            Reserva reserva=MongoDB.getReserva(documentoReserva);
            if (reserva.getHabitacion().equals(habitacion)
            && reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasHabitacion.add(reserva);
            }
        }
        return reservasHabitacion;
    }

    public void realizarCheckin (Reserva reserva, LocalDateTime fecha)throws NullPointerException,IllegalArgumentException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }
        Reserva reservaCheckIn = buscar(reserva);
        if (reservaCheckIn == null) {
            throw new IllegalArgumentException("La reserva no existe.");
        }

        UpdateResult actualizarCheckIn = coleccionReservas.updateOne(
                new Document(MongoDB.HUESPED, MongoDB.getDocumento(reservaCheckIn.getHuesped())),
                new Document("$set", new Document(MongoDB.CHECKIN, fecha)));

        if (actualizarCheckIn.getModifiedCount() == 0) {

            throw new IllegalStateException("No se pudo realizar el checkIn.");
        }
    }
    public void realizarCheckout (Reserva reserva, LocalDateTime fecha)throws NullPointerException,IllegalArgumentException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }

        Reserva reservaCheckOut = buscar(reserva);
        if (reservaCheckOut == null) {
            throw new IllegalArgumentException("La reserva no existe.");
        }

        UpdateResult actualizarCheckOut = coleccionReservas.updateOne(
                new Document(MongoDB.HUESPED, MongoDB.getDocumento(reservaCheckOut.getHuesped())),
                new Document("$set", new Document(MongoDB.CHECKIN, fecha)));

        if (actualizarCheckOut.getModifiedCount() == 0) {

            throw new IllegalStateException("No se pudo realizar el checkIn.");
        }
    }
}
