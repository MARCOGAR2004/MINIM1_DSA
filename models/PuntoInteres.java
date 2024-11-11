package edu.upc.dsa.models;

import edu.upc.dsa.models.ElementType;

public class PuntoInteres {
    int latitud;
    int longitud;
    ElementType tipo;
    public PuntoInteres(int latitud, int longitud, ElementType tipo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public ElementType getTipo() {
        return tipo;
    }

    public void setTipo(ElementType tipo) {
        this.tipo = tipo;
    }
}
