package org.iesalandalus.programacion.reservashotel.Modelo;

import org.iesalandalus.programacion.reservashotel.Modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.reservashotel.Modelo.negocio.mongodb.FuenteDatosMongoDB;

public enum FactoriaFuenteDatos {


    MEMORIA {
        public IFuenteDatos crear() {
            return new FuenteDatosMemoria();
        }
    },
    MONGODB {
        public IFuenteDatos crear () {
            return new FuenteDatosMongoDB();
        }

    };
    public abstract IFuenteDatos crear();
}
