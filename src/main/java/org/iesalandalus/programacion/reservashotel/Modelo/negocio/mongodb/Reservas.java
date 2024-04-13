package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb;

import com.mongodb.client.MongoCollection;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades.MongoDB;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import javax.swing.text.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reservas implements IReservas {

    private List<Reserva> coleccionReserva;

    private MongoCollection<Document> coleccionReservas;



    public Reservas ()         {
         coleccionReserva= new ArrayList<>();
    }

    public List <Reserva> get(){
        return coleccionReserva;
    }

    public int getTamano() {
        return coleccionReserva.size();
    }

    public void insertar (Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        if (!coleccionReserva.contains(reserva)){
            coleccionReserva.add(new Reserva(reserva));
        }else {
            throw new OperationNotSupportedException("ERROR:Y existe una reserva con esos datos.");
        }
    }



    public void borrar (Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        if (coleccionReserva.contains(reserva)){
            coleccionReserva.remove(reserva);
        } else {
            throw new OperationNotSupportedException("ERROR:No existe ningúna reserva con ese nombre.");
        }
    }


    public List <Reserva> getReservas (Huesped huesped){
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }
        List <Reserva> reservasHuesped = new ArrayList<>();

        for (Reserva reserva : coleccionReserva) {
            if (reserva.getHuesped().equals(huesped)) {
                reservasHuesped.add(new Reserva(reserva));
            }
        }
        return reservasHuesped;

    }
    @Override
    public List<Reserva> getReservas() {
        List<Reserva> reservas = new ArrayList<>();
        for (Document documentoReserva : coleccionReservas.find()) {
            reservas.add(MongoDB.(documentoReserva));
        }
        ;
        return reservas;
    }

    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion)throws NullPointerException{
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un aula nula.");
        }
        List <Reserva> reservasTipoHabitacion = new ArrayList<>();

        for (Reserva reserva : coleccionReserva) {
            if (reserva.getHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasTipoHabitacion;
    }
    public List<Reserva> getReservasFuturas (Habitacion habitacion){
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        List<Reserva> reservasHabitacion = new ArrayList<>();

        for (Reserva reserva : reservasHabitacion) {
            if (reserva.getHabitacion().equals(habitacion)
            && reserva.getFechaInicioReserva().isAfter(LocalDate.now())) {
                reservasHabitacion.add(new Reserva(reserva));
            }
        }
        return reservasHabitacion;
    }

    public void realizarCheckin (Reserva reserva, LocalDateTime fecha)throws NullPointerException,IllegalArgumentException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }

        System.out.print("Introduce la fecha de checkIn.");
        String fechaCheckIn= Entrada.cadena();
        fecha= LocalDate.from(LocalDateTime.parse(fechaCheckIn)).atStartOfDay();
        reserva.setCheckIn(fecha);
    }
    public void realizarCheckout (Reserva reserva, LocalDateTime fecha)throws NullPointerException,IllegalArgumentException{
        if (reserva == null) {
            throw new NullPointerException("ERROR: La reserva no puede ser nula.");
        }

        System.out.print("Introduce la fecha de checkOut.");
        String fechaCheckOut= Entrada.cadena();
        fecha= LocalDate.from(LocalDateTime.parse(fechaCheckOut)).atStartOfDay();
        reserva.setCheckOut(fecha);
    }
}
