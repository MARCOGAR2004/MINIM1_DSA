package edu.upc.dsa.models;

import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.PuntoInteres;

public class RegistroUsuarioPunto {
    String idUsuario;
    int longitud;
    int latitud;

    public RegistroUsuarioPunto(String idUsuario, int longitud, int latitud) {
        this.idUsuario = idUsuario;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }
}
