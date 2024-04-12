package org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.utilidades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoClients;

import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Huesped;

import org.bson.Document;
import org.iesalandalus.programacion.reservashotel.Modelo.dominio.Reserva;

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
    private static final String TIPO_TRPLE = "TRIPLE";
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
        return new Huesped(documentoHuesped.getString(NOMBRE),documentoHuesped.getString(DNI), documentoHuesped.getString(CORREO),
                documentoHuesped.getString(TELEFONO),documentoHuesped.getLocalDate(FECHA_NACIMIENTO));
    }


    public static Document getDocumento(Habitacion habitacion) {
        if (habitacion == null) {
            return null;
        }

        int planta = habitacion.getPlanta();
        double precio = habitacion.getPrecio();
        int puerta = habitacion.getPuerta();
        String identificador = habitacion.getIdentificador();

        return new Document().append(PLANTA, planta).append(PUERTA,puerta).append(PRECIO,precio).append(IDENTIFICADOR,identificador);
    }
    public static Document getDocumento(Reserva reserva) {
        if (reserva == null) {
            return null;
        }
        Huesped huesped = reserva.getHuesped();
        Habitacion habitacion = reserva.getHabitacion();
        Document dHuesped = getDocumento(huesped);
        Document dHabitacion = getDocumento(habitacion);


        return new Document().append(HUESPED, dHuesped).append(HABITACION, dHabitacion);
    }



}
