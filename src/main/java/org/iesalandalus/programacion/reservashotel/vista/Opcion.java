package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("Salir") {
         @Override
        public void ejecutar(){
             vista.terminar();}
    },
    INSERTAR_HUESPED("Inserte un huésped."){
        @Override
        public void ejecutar(){
            vista.insertarHuesped();}
    },
    BUSCAR_HUESPED("Busca huésped."){
        @Override
        public void ejecutar(){
            vista.buscarHuesped();}
    },
    BORRAR_HUESPED("Borra huésped."){
        @Override
        public void ejecutar(){
            vista.borrarHuesped();}
    },
    MOSTRAR_HUESPEDES("Muestra huésped."){
        @Override
        public void ejecutar(){
            vista.mostrarHuespedes();}
    },
    INSERTAR_HABITACION("Inserta habitación."){
        @Override
        public void ejecutar(){
            vista.insertarHabitacion();}
    },
    BUSCAR_HABITACION("Busca habitación."){
        @Override
        public void ejecutar(){
            vista.buscarHabitacion();}
    },
    BORRAR_HABITACION("Borra habitación."){
        @Override
        public void ejecutar(){
            vista.borrarHabitacion();}
    },
    MOSTRAR_HABITACIONES("Muestra habitaciones."){
        @Override
        public void ejecutar(){
            vista.mostrarHabitaciones();}
    },
    INSERTAR_RESERVA("Inserta reserva."){
        @Override
        public void ejecutar(){
            vista.insertarReserva();}
    },
    ANULAR_RESERVA("Anula reserva."){
        @Override
        public void ejecutar(){
            vista.anularReserva();}
    },
    MOSTRAR_RESERVAS("Muestra reserva."){
        @Override
        public void ejecutar(){
            vista.mostrarReservas();}
    },
    LISTAR_RESERVAS_HUESPED("Lista las reservas de un huésped"){
        @Override
        public void ejecutar(){
            vista.mostrarReservaHuesped();}
    },
    LISTAR_RESERVAS_TIPO_HABITACION("Lista reservas por tipo de habitación"){
        @Override
        public void ejecutar(){
            vista.mostrarReservasTipoHabitacion();}
    },
    CONSULTAR_DISPONIBILIDAD("Consulta disponibilidad."){
        @Override
        public void ejecutar(){
            vista.comprobarDisponibilidad();}
    },
    REALIZAR_CHECKIN("Realiza el CheckIn"){
        @Override
        public void ejecutar(){
            vista.realizarCheckIn();}
    },
    REALIZAR_CHECKOUT("Realiza el CheckOut"){
        @Override
        public void ejecutar(){
            vista.realizarCheckOut();}
    };


    private static Vista vista;
    private  String mensajeAMostrar;
    private Opcion(String mensajeAMostrar){
        this.mensajeAMostrar=mensajeAMostrar;
    }




    private static Opcion getOpcionSegunOrdinal(int ordinal) {
        if ((ordinal >= 0 && ordinal <= values().length - 1))
            return values()[ordinal];
        else
            throw new IllegalArgumentException("Ordinal de la opción no válida");
    }

    public static void setVista(Vista vista){
        if (vista == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
        }
        Opcion.vista=vista;
    }

    public abstract void ejecutar();
    @Override
    public String toString() {
        return String.format("%d.- %s", ordinal(), mensajeAMostrar);
    }
}
