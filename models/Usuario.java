package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {
    String id;
    String nombre;
    String apellido;
    String correo;
    String nacimiento;

    public Usuario() {
        this.setId(RandomUtils.getId());
    }
    public Usuario(String nombre, String apellido, String correo, String nacimiento) {
        this();
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setCorreo(correo);
        this.setNacimiento(nacimiento);
    }
    public Usuario(String id, String nombre, String apellido, String correo, String nacimiento) {
        this(nombre, apellido, correo, nacimiento);
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
}
