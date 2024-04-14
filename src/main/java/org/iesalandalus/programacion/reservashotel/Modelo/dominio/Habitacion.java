package org.iesalandalus.programacion.reservashotel.Modelo.dominio;

import java.util.Objects;

public abstract class Habitacion {

    public static final double MIN_PRECIO_HABITACION= 50;
    public static final double MAX_PRECIO_HABITACION = 150;
    public static final int MIN_NUMERO_PUERTA=0;
    public static final int MAX_NUMERO_PUERTA=10;
    public static final int MIN_NUMERO_PLANTA=1;
    public static final int MAX_NUMERO_PLANTA=3;

    protected String identificador;
    protected int planta;
    protected int puerta;
    protected double precio;



    public Habitacion(int planta, int puerta, double precio){
        setPlanta(planta);
        setPrecio(precio);
        setPuerta(puerta);
        setIdentificador(String.format("%d%d",getPlanta(),getPuerta()));
    }

    public Habitacion(Habitacion habitacion){

        if (habitacion==null) {
            throw new NullPointerException("ERROR: No es posible copiar una habitación nula.");
        }
        setPlanta(habitacion.getPlanta());
        setPrecio(habitacion.getPrecio());
        setPuerta(habitacion.getPuerta());
        setIdentificador(habitacion.getIdentificador());
    }

    public abstract int getNumeroMaximoPersonas();


    public String getIdentificador(){
       return identificador;
    }

    protected void setIdentificador(){
        if (identificador==null){
            throw new NullPointerException("El identificador no puede ser nulo.");
        }
        this.identificador= String.valueOf(getPlanta()) +String.valueOf(getPuerta());
    }
    protected void setIdentificador(String identificador){
        if (identificador==null){
            throw new NullPointerException("El identificador no puede ser nulo.");
        }
      this.identificador=identificador;
    }

    public int getPlanta(){
        return planta;
    }

    protected void setPlanta(int planta){

        if (planta<MIN_NUMERO_PLANTA || planta>MAX_NUMERO_PLANTA){
            throw new IllegalArgumentException("ERROR: No se puede establecer como planta de una habitación un valor menor que 1 ni mayor que 3.");
        }
        else {
            this.planta = planta;
        }
    }

    public int getPuerta(){
        return puerta;
    }

    protected void setPuerta(int puerta) {

        if (puerta < MIN_NUMERO_PUERTA || puerta > MAX_NUMERO_PUERTA) {
            throw new IllegalArgumentException("ERROR: No se puede establecer como puerta de una habitación un valor menor que 0 ni mayor que 14.");
        }
            this.puerta = puerta;
    }

    public double getPrecio(){
        return precio;
    }

    protected void setPrecio(double precio) {

            if (precio < MIN_PRECIO_HABITACION || precio > MAX_PRECIO_HABITACION) {
                throw new IllegalArgumentException("ERROR: No se puede establecer como precio de una habitación un valor menor que 40.0 ni mayor que 150.0.");
            }
        this.precio=precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion that = (Habitacion) o;
        return Objects.equals(identificador, that.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    public String toString (){
        return String.format("identificador=%s (%d-%d), precio habitación=%s",
                getIdentificador(),getPlanta(), getPuerta(), getPrecio());
    }

}


