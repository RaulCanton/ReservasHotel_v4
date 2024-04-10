package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {
    public static final int MAX_NUMERO_MESES_RESERVAS=6;
    public static final int MAX_HORAS_POSTERIOR_CHECKOUT=12;
    public static final String FORMATO_FECHA_RESERVA=("yyyy/M/d");
    public static final String FORMATO_FECHA_HORA_RESERVA="dd/MM/yyyy";

    private Huesped huesped;
    private Habitacion habitacion;
    private Regimen regimen;
    private LocalDate fechaInicioReserva;
    private LocalDate fechaFinReserva;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double precio;
    private int numeroPersonas;

    public Reserva(Huesped huesped,Habitacion habitacion,Regimen regimen,
                   LocalDate fechaInicioReserva,LocalDate fechaFinReserva,int numeroPersonas) {
        setHuesped(huesped);
        setHabitacion(habitacion);
        setRegimen(regimen);
        setFechaInicioReserva(fechaInicioReserva);
        setFechaFinReserva(fechaFinReserva);
        setNumeroPersonas(numeroPersonas);
        this.checkIn=null;
        this.checkOut=null;
    }

    public Reserva(Reserva reserva){
        if (reserva==null) {
            throw new NullPointerException("ERROR: No es posible copiar una reserva nula.");
        }
        setHuesped(reserva.getHuesped());
        setHabitacion(reserva.getHabitacion());
        setRegimen(reserva.getRegimen());
        setFechaInicioReserva(reserva.getFechaInicioReserva());
        setFechaFinReserva(reserva.getFechaFinReserva());
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        Objects.requireNonNull(huesped, "ERROR: El huésped de una reserva no puede ser nulo.");
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion){
        if ((habitacion==null)){
            throw new NullPointerException("La habitación no puede ser nula.");
        }
        this.habitacion=habitacion;

    }

    public Regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(Regimen regimen) {
        Objects.requireNonNull(regimen, "ERROR: El régimen de una reserva no puede ser nulo.");
        this.regimen = regimen;
    }

    public LocalDate getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(LocalDate fechaInicioreserva)throws NullPointerException,DateTimeException {
        if (fechaInicioreserva==null){
            throw new NullPointerException("ERROR: La fecha de inicio de una reserva no puede ser nula.");
        }
        LocalDate diaActual= LocalDate.now();
        LocalDate date=diaActual.plus(Period.ofMonths(MAX_NUMERO_MESES_RESERVAS));
        if(diaActual.isAfter(fechaInicioreserva)){
            throw new DateTimeException("ERROR: La fecha de inicio de la reserva no puede ser anterior al día de hoy.");
        }
        if (fechaInicioReserva.isAfter(date)){
            throw new DateTimeException("ERROR: La fecha de inicio de la reserva no puede ser posterior a seis meses.");
        }
        else {
            this.fechaInicioReserva = fechaInicioreserva;
        }
    }

    public LocalDate getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(LocalDate fechaFinReserva) {
        if ((fechaFinReserva==null)){
            throw new NullPointerException("ERROR: La fecha de fin de una reserva no puede ser nula.");
        }

        if (fechaFinReserva.isBefore(getFechaInicioReserva())){
            throw new DateTimeException("ERROR: La fecha de fin de la reserva debe ser posterior a la de inicio.");
        }
        else {
            this.fechaFinReserva = fechaFinReserva;
        }
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) throws NullPointerException,IllegalArgumentException {
        if (checkIn==null){
            throw new NullPointerException("ERROR: El checkin de una reserva no puede ser nulo.");
        }
        if(checkIn.isBefore(fechaInicioReserva.atStartOfDay())){
            throw new IllegalArgumentException("ERROR: El checkin de una reserva no puede ser anterior a la fecha de inicio de la reserva. ");
        }
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut)throws NullPointerException,IllegalArgumentException {
        if (checkOut==null){
            throw new NullPointerException("ERROR: El checkOut de una reserva no puede ser nulo.");
        }
        if(checkOut.isBefore(checkIn)){
            throw new IllegalArgumentException("ERROR: El checkOut de una reserva no puede ser anterior al checkIn. ");
        }
        LocalDateTime diaHoraOut =LocalDateTime.now().plusHours(MAX_HORAS_POSTERIOR_CHECKOUT);
        if (checkOut.isAfter(ChronoLocalDateTime.from(diaHoraOut))){
            throw new IllegalArgumentException(("ERROR: El checkout de una reserva puede ser como máximo 12 horas después de la fecha de fin de la reserva."));
        }
        this.checkOut = checkOut;
    }

    public double getPrecio() {
        return precio;
    }

    private void setPrecio() {
        int maxPersonas = habitacion.getNumeroMaximoPersonas();

        precio=precio+(maxPersonas*50);

        if (regimen==Regimen.SOLO_ALOJAMIENTO){
            this.precio=precio+(Regimen.SOLO_ALOJAMIENTO.getIncrementoPrecio()*numeroPersonas);
        }
        if (regimen==Regimen.ALOJAMIENTO_DESAYUNO){
            this.precio=precio+(Regimen.ALOJAMIENTO_DESAYUNO.getIncrementoPrecio()*numeroPersonas);
        }
        if (regimen==Regimen.MEDIA_PENSION){
            this.precio=precio+(Regimen.MEDIA_PENSION.getIncrementoPrecio()*numeroPersonas);
        }
        if (regimen==Regimen.PENSION_COMPLETA){
            this.precio=precio+(Regimen.PENSION_COMPLETA.getIncrementoPrecio()*numeroPersonas);
        }
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(habitacion, reserva.habitacion) && Objects.equals(fechaInicioReserva, reserva.fechaInicioReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion, fechaInicioReserva);
    }

    public String toString(){
        return String.format("Huesped: %s %s Habitación:%s - %s Fecha Inicio Reserva: %s Fecha Fin Reserva: %s Checkin: %s Checkout: %s Precio: %.2f Personas: %d",getHuesped().getNombre(), getHuesped().getDni(),
                getHabitacion().getIdentificador(), getFechaInicioReserva().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_RESERVA)),
                getFechaFinReserva(), getPrecio());

    }
}

























