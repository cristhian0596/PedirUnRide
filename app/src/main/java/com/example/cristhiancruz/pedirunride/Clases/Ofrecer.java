package com.example.cristhiancruz.pedirunride.Clases;

/**
 * Created by Cristhian Cruz on 14/04/2018.
 */

public class Ofrecer {

    public String fecha;
    public String lugar_origen;
    public String lugar_destino;
    public String cantidad_disponibles;
    public String ida_vuelta;
    public String estado_ida_vuelta;
    public String comunidad;
    public String usuario;

    public Ofrecer() {
    }


    public Ofrecer(String fecha, String lugar_origen, String lugar_destino,
                   String cantidad_disponibles, String ida_vuelta, String estado_ida_vuelta,
                   String comunidad, String usuario) {
        this.fecha = fecha;
        this.lugar_origen = lugar_origen;
        this.lugar_destino = lugar_destino;
        this.cantidad_disponibles = cantidad_disponibles;
        this.ida_vuelta = ida_vuelta;
        this.estado_ida_vuelta = estado_ida_vuelta;
        this.comunidad = comunidad;
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar_origen() {
        return lugar_origen;
    }

    public void setLugar_origen(String lugar_origen) {
        this.lugar_origen = lugar_origen;
    }

    public String getLugar_destino() {
        return lugar_destino;
    }

    public void setLugar_destino(String lugar_destino) {
        this.lugar_destino = lugar_destino;
    }

    public String getCantidad_disponibles() {
        return cantidad_disponibles;
    }

    public void setCantidad_disponibles(String cantidad_disponibles) {
        this.cantidad_disponibles = cantidad_disponibles;
    }

    public String getIda_vuelta() {
        return ida_vuelta;
    }

    public void setIda_vuelta(String ida_vuelta) {
        this.ida_vuelta = ida_vuelta;
    }

    public String getEstado_ida_vuelta() {
        return estado_ida_vuelta;
    }

    public void setEstado_ida_vuelta(String estado_ida_vuelta) {
        this.estado_ida_vuelta = estado_ida_vuelta;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedir{" +
                "fecha='" + fecha + '\'' +
                ", lugar_origen='" + lugar_origen + '\'' +
                ", lugar_destino='" + lugar_destino + '\'' +
                ", cantidad_disponibles='" + cantidad_disponibles + '\'' +
                ", ida_vuelta='" + ida_vuelta + '\'' +
                ", estado_ida_vuelta='" + estado_ida_vuelta + '\'' +
                ", comunidad='" + comunidad + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
