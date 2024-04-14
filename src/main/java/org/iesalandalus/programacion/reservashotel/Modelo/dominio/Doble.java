package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

public class Doble extends Habitacion{

    private static final int NUM_MAXIMO_PERSONAS=2;

    final int MIN_NUM_CAMAS_INDIVIDUALES=1;
    final int MAX_NUM_CAMAS_INDIVIDUALES=2;
    final int MIN_NUM_CAMAS_DOBLES=1;
    final int MAX_NUM_CAMAS_DOBLES=1;

    private int numCamasIndividuales;
    private int numCamasDobles;

    public Doble(int planta, int puerta, double precio, int numCamasIndividuales,int numCamasDobles) {
        super(planta, puerta, precio);
        setIdentificador();
        setNumCamasIndividuales(numCamasIndividuales);
        setNumCamasDobles(numCamasDobles);
        validaNumCamas();
    }

    public Doble(Doble habitacionDoble) {
        super(habitacionDoble);
        setNumCamasIndividuales(habitacionDoble.getNumCamasIndividuales());
        setNumCamasDobles(habitacionDoble.getNumCamasDobles());
        validaNumCamas();

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
        if(numCamasIndividuales !=MAX_NUM_CAMAS_INDIVIDUALES && (numCamasDobles<MIN_NUM_CAMAS_DOBLES || numCamasDobles>MAX_NUM_CAMAS_DOBLES)){
            throw new IllegalArgumentException("El n�mero de camas elegido no es correcto.");
        }
    }

    @Override
    public int getNumeroMaximoPersonas() {
        return NUM_MAXIMO_PERSONAS;
    }

    @Override
    public String toString() {
        return String.format("identificador=%s (%d-%d), precio habitaci�n=%s, habitaci�n doble, capacidad=%d personas, " +
                        "camas individuales=%d, camas dobles=%d",
                getIdentificador(), getPlanta(), getPuerta(), getPrecio(),
                getNumeroMaximoPersonas(),getNumCamasIndividuales(),getNumCamasDobles());
    }
}
