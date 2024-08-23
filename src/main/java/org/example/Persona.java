package org.example;

import java.time.LocalDateTime;
import java.util.Date;

public class Persona {
    protected String nombre;
    protected String estadoAsis;
    char genero;
    LocalDateTime fechaReg= LocalDateTime.now();
    Date FechaReg;
    public Persona(String nombre, String estadoAsis, Date FechaReg){
        this.nombre = nombre;
        this.estadoAsis = estadoAsis;
        this.FechaReg = FechaReg;
    }

    public Persona(String nombre, String estadoAsis, char genero) {
        this.nombre = nombre;
        this.estadoAsis = estadoAsis;
        this.genero=genero;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(LocalDateTime fechaReg) {
        this.fechaReg = fechaReg;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoAsis() {
        return estadoAsis;
    }

    public void setEstadoAsis(String estadoAsis) {
        this.estadoAsis = estadoAsis;

    }
}