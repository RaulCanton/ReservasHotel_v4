package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

public class Triple extends Habitacion {

    private static final int NUM_MAXIMO_PERSONAS=3;
    final int MIN_NUM_BANOS=1;
    final int MAX_NUM_BANOS=2;

    final int MIN_NUM_CAMAS_INDIVIDUALES=2;
    final int MAX_NUM_CAMAS_INDIVIDUALES=3;
    final int MIN_NUM_CAMAS_DOBLES=0;
    final int MAX_NUM_CAMAS_DOBLES=1;

    private int numBanos;
    private int numCamasIndividuales;
    private int numCamasDobles;

    public Triple(int planta, int puerta, double precio, int numBanos, int numCamasIndividuales, int numCamasDobles) {
        super(planta, puerta, precio);
        setIdentificador();
        setNumBanos(numBanos);
        setNumCamasIndividuales(numCamasIndividuales);
        setNumCamasDobles(numCamasDobles);
        validaNumCamas();
    }

    public Triple(Triple habitacionTriple) {
        super(habitacionTriple);
        setNumBanos(habitacionTriple.getNumBanos());
        setNumCamasIndividuales(habitacionTriple.getNumCamasIndividuales());
        setNumCamasDobles(getNumCamasDobles());
        validaNumCamas();
    }

    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        this.numBanos = numBanos;
    }

    public int getNumCamasIndividuales() {
        return numCamasIndividuales;
    }

    public void setNumCamasIndividuales(int numCamasIndividuales) {
        this.numCamasIndividuales = numCamasIndividuales;
        validaNumCamas();
    }

    public int getNumCamasDobles() {
        return numCamasDobles;
    }

    public void setNumCamasDobles(int numCamasDobles) {
        this.numCamasDobles = numCamasDobles;
        validaNumCamas();
    }

    private void validaNumCamas(){
        if(!((numCamasIndividuales==MIN_NUM_CAMAS_INDIVIDUALES && numCamasDobles==MAX_NUM_CAMAS_DOBLES)|| (numCamasIndividuales==MAX_NUM_CAMAS_INDIVIDUALES))){
            throw new IllegalArgumentException("El n�mero de camas elegido no es correcto.");
        }

    }
    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitaci�n=%s, habitaci�n triple, capacidad=%d personas, " +
                        "ba�os=%d, camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(),getNumBanos(),getNumCamasIndividuales(),getNumCamasDobles());
    }
}
