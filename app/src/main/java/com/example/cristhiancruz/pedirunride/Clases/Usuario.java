package com.example.cristhiancruz.pedirunride.Clases;

/**
 * Created by Cristhian Cruz on 14/04/2018.
 */

public class Usuario {
    public String nombre;
    public String lugar;
    public String telefono;
    public String foto;
    public String correo;
    public String contraseña;

    public Usuario() {
    }

    public Usuario(String nombre, String lugar, String telefono, String foto, String correo, String contraseña) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.telefono = telefono;
        this.foto = foto;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", lugar='" + lugar + '\'' +
                ", telefono='" + telefono + '\'' +
                ", foto='" + foto + '\'' +
                ", correo='" + correo + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}

