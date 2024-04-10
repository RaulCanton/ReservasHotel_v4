package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

public class Suite extends Habitacion{
    private static final int NUM_MAXIMO_PERSONAS=4;
    final int MIN_NUM_BANOS=1;
    final int MAX_NUM_BANOS=3;

    private int numBanos;
    private boolean tieneJacuzzi;



    public Suite(int planta, int puerta, double precio,String identificador, int numBanos, boolean tieneJacuzzi) {
        super(planta, puerta, precio);
        setNumBanos(numBanos);
        setTieneJacuzzi(tieneJacuzzi);
    }

    public Suite(Suite habitacionSuite){
        super(habitacionSuite);
        setNumBanos(habitacionSuite.getNumBanos());
        setTieneJacuzzi(habitacionSuite.isTieneJacuzzi());
    }
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }
    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos)throws IllegalArgumentException {
        if (numBanos < MIN_NUM_BANOS || numBanos > MAX_NUM_BANOS) {
            throw new IllegalArgumentException("ERROR: Baños introducidos erróneo..");
        }

        this.numBanos = numBanos;
    }

    public boolean isTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {

        this.tieneJacuzzi = tieneJacuzzi;
    }

    public String toString(){
        return String.format("identificador=%s (%d-%d), precio habitación=%s, habitación suite, capacidad=%d personas, " +
                        "baños=%d, tiene Jacuzzi?",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(),getNumBanos(),isTieneJacuzzi());
    }
}
