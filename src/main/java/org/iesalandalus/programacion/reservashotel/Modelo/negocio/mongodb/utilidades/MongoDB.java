package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoClients;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.*;

import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDB {

    public static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd-MM_yyyy");

    public static final DateTimeFormatter FORMATO_DIA_HORA = DateTimeFormatter.ofPattern("dd-MM_yyyy HH:mm");

    private static final String SERVIDOR=  "35.246.226.125";

    private static final int PUERTO =27017;

    private static final String BD = "reservashotel";
    private static final String USUARIO = "reservashotel";
    private static final String CONTRASENA = "reservashotel-2024";
    private static final String HUESPED="huesped";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";
    private static final String CORREO = "correo";
    private static final String FECHA_NACIMIENTO = "fecha_nacimiento";
    private static final String HUESPED_DNI = HUESPED +"."+DNI;
    private static final String HABITACION= "habitacion";
    private static final String IDENTIFICADOR = "identificador";
    private static final String PLANTA = "planta";
    private static final String PUERTA = "puerta";
    private static final String PRECIO = "precio";
    private static final String HABITACION_IDENTIFICADOR = HABITACION +"."+ IDENTIFICADOR;
    private static final String TIPO = "tipo";
    private static final String HABITACION_TIPO = HABITACION + "."+ TIPO;
    private static final String TIPO_SIMPLE = "SIMPLE";
    private static final String TIPO_DOBLE = "DOBLE";
    private static final String TIPO_TRIPLE = "TRIPLE";
    private static final String TIPO_SUITE = "SUITE";
    private static final String CAMAS_INDIVIDUALES = "camas_individuales";
    private static final String CAMAS_DOBLES = "camas dobles";
    private static final String BANOS ="banos";
    private static final String JACUZZI = "jacuzzi";
    private static final String REGIMEN ="regimen";
    private static final String FECHA_INICIO_RESERVA ="fecha_inicio_reserva";
    private static final String FECHA_FIN_RESERVA = "fecha_fin_reserva";
    private static final String CHECKIN = "checkin";
    private static final String CHECKOUT = "checkout";
    private static final String PRECIO_RESERVA = "precio_reserva";
    private static final String NUMERO_PRESONAS = "numero_personas";

    private static MongoClient conexion = null;
    private MongoDB() {
    }

    public static MongoDatabase getBD() {
        if (conexion == null) {
            establecerConexion();
        }
        return conexion.getDatabase(BD);
    }

    private static MongoClient establecerConexion() {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);
        if (conexion == null) {
            MongoCredential credenciales = MongoCredential.createScramSha1Credential(USUARIO, BD, CONTRASENA.toCharArray());
            conexion = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder ->
                                    builder.hosts(Arrays.asList(new ServerAddress(SERVIDOR, PUERTO))))
                            .credential(credenciales)
                            .build());
            System.out.println("Conexi�n a MongoDB realizada correctamente.");
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            conexion.close();
            conexion = null;
            System.out.println("Conexi�n a MongoDB cerrada.");
        }
    }

    public static Document getDocumento(Huesped huesped) {
        if (huesped == null) {
            return null;
        }
        String nombre = huesped.getNombre();
        String dni = huesped.getDni();
        String telefono = huesped.getTelefono();
        String correo = huesped.getCorreo();
        LocalDate fecha_nacimiento= huesped.getFechaNacimiento();
        return new Document().append(NOMBRE, nombre).append(DNI, dni).append(TELEFONO, telefono).append(CORREO, correo).
                append(FECHA_NACIMIENTO,fecha_nacimiento);
    }

    public static Huesped getHuesped(Document documentoHuesped) {
        if (documentoHuesped == null) {
            return null;
        }
        String nombre = documentoHuesped.getString(NOMBRE);
        String dni = documentoHuesped.getString(DNI);
        String telefono = documentoHuesped.getString(TELEFONO);
        String correo = documentoHuesped.getString(CORREO);
        LocalDate fecha_nacimiento= LocalDate.parse(documentoHuesped.getString(FECHA_NACIMIENTO));

        return new Huesped(nombre,dni,correo,telefono,fecha_nacimiento);
    }


    public static Document getDocumento(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }

        int planta = habitacion.getPlanta();
        double precio = habitacion.getPrecio();
        int puerta = habitacion.getPuerta();
        String identificador = habitacion.getIdentificador();

        Document docuHabitacion = new Document().append(PLANTA, planta).append(PUERTA,puerta)
                .append(PRECIO,precio).append(IDENTIFICADOR,identificador);

        if (habitacion instanceof Doble) {
            Doble habitacionDoble = (Doble) habitacion;
            docuHabitacion.append(CAMAS_INDIVIDUALES, habitacionDoble.getNumCamasIndividuales())
                    .append(CAMAS_DOBLES, habitacionDoble.getNumCamasDobles());
        }
        if (habitacion instanceof Triple) {
            Triple habitacionTriple = (Triple) habitacion;
            docuHabitacion.append(BANOS, habitacionTriple.getNumBanos()).append(CAMAS_INDIVIDUALES, habitacionTriple.getNumCamasIndividuales())
                    .append(CAMAS_DOBLES, habitacionTriple.getNumCamasDobles());
        }
        if (habitacion instanceof Suite) {
            Suite habitacionSuite = (Suite) habitacion;
            docuHabitacion.append(BANOS, habitacionSuite.getNumBanos()).append(JACUZZI, habitacionSuite.isTieneJacuzzi());
        }

        return docuHabitacion;
    }

    public static Habitacion getHabitacion(Document documentoHabitacion) {
        if (documentoHabitacion == null) {
            return null;
        }

        int planta = documentoHabitacion.getInteger(PLANTA);
        double precio = documentoHabitacion.getDouble(PRECIO);
        int puerta = documentoHabitacion.getInteger(PUERTA);
        String identificador = documentoHabitacion.getString(IDENTIFICADOR);
        String tipo = documentoHabitacion.getString(TIPO);


        if (tipo.equals(TIPO_SIMPLE)) {
            return new Simple(planta, puerta, precio,identificador);
        }
        if (tipo.equals(TIPO_DOBLE)) {
            int camasIndividuales = documentoHabitacion.getInteger(CAMAS_INDIVIDUALES);
            int camasDobles = documentoHabitacion.getInteger(CAMAS_DOBLES);
            return new Doble(planta, puerta, precio,identificador, camasIndividuales, camasDobles);
        }
        if (tipo.equals(TIPO_TRIPLE)) {
            int camasIndividuales = documentoHabitacion.getInteger(CAMAS_INDIVIDUALES);
            int camasDobles = documentoHabitacion.getInteger(CAMAS_DOBLES);
            int numeroBanos = documentoHabitacion.getInteger(BANOS);
            return new Triple(planta, puerta, precio,identificador, camasIndividuales, camasDobles,numeroBanos);
        }
        if (tipo.equals(TIPO_SUITE)){
            int numeroBanos = documentoHabitacion.getInteger(BANOS);
            boolean jacuzzi = documentoHabitacion.getBoolean(JACUZZI);
            return  new Suite(planta,puerta,precio,identificador,numeroBanos,jacuzzi);
        }
        else {

            return null;
        }
    }

    public static Document getDocumento(Reserva reserva) {
        if (reserva == null) {
            return null;
        }
        Huesped huesped = reserva.getHuesped();
        Habitacion habitacion = reserva.getHabitacion();
        Regimen regimen = reserva.getRegimen();
        LocalDate fechaInicioReserva = reserva.getFechaInicioReserva();
        LocalDate fechaFinReserva =reserva.getFechaFinReserva();
        Document dHuesped = getDocumento(huesped);
        Document dHabitacion = getDocumento(habitacion);

        return new Document().append(HUESPED, dHuesped).append(HABITACION, dHabitacion).
                append(REGIMEN,regimen).append(FECHA_INICIO_RESERVA,fechaInicioReserva)
                .append(FECHA_FIN_RESERVA,fechaFinReserva);
    }


    public static Reserva getReserva(Document documentoReserva) {
        if (documentoReserva == null) {
            return null;
        }
        Document dHuesped = (Document) documentoReserva.get(HUESPED);
        Document dHabitacion = (Document) documentoReserva.get(HABITACION);

        Habitacion habitacion = getHabitacion(dHabitacion);
        Habitacion habitacion = new Ha(((Document) documentoReserva.get(HABITACION))) {
        };
        Huesped huesped = new Huesped(getHuesped((Document) documentoReserva.get(HUESPED)));
        Document dPermanencia = (Document) documentoReserva.get(PERMANENCIA);
        LocalDate dia = LocalDate.parse(dPermanencia.getString(DIA), FORMATO_DIA);
        String tipoPermanencia = dPermanencia.getString(TIPO);
        Permanencia permanencia = null;
        if (tipoPermanencia.equals(TIPO_TRAMO)) {
            Tramo tramo = Tramo.valueOf(dPermanencia.getString(TRAMO));
            permanencia = new PermanenciaPorTramo(dia, tramo);
        } else {
            LocalTime hora = LocalTime.parse(dPermanencia.getString(HORA), FORMATO_HORA);
            permanencia = new PermanenciaPorHora(dia, hora);
        }
        return new Reserva(profesor, aula, permanencia);
    }

}
