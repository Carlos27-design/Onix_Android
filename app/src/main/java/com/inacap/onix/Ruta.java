package com.inacap.onix;

public class Ruta {
    private int id;
    private String direccionInicioNombre;
    private String direccionInicio;
    private String direccionFinalNombre;
    private String direccionFinal;
    private String distancia;
    private String fechaInicio;
    private String fechaFin;

    public Ruta() {
    }

    public Ruta(int id, String direccionInicioNombre, String direccionInicio, String direccionFinalNombre, String direccionFinal, String distancia, String fechaInicio, String fechaFin) {
        this.id = id;
        this.direccionInicioNombre = direccionInicioNombre;
        this.direccionInicio = direccionInicio;
        this.direccionFinalNombre = direccionFinalNombre;
        this.direccionFinal = direccionFinal;
        this.distancia = distancia;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionInicioNombre() {
        return direccionInicioNombre;
    }

    public void setDireccionInicioNombre(String direccionInicioNombre) {
        this.direccionInicioNombre = direccionInicioNombre;
    }

    public String getDireccionInicio() {
        return direccionInicio;
    }

    public void setDireccionInicio(String direccionInicio) {
        this.direccionInicio = direccionInicio;
    }

    public String getDireccionFinalNombre() {
        return direccionFinalNombre;
    }

    public void setDireccionFinalNombre(String direccionFinalNombre) {
        this.direccionFinalNombre = direccionFinalNombre;
    }

    public String getDireccionFinal() {
        return direccionFinal;
    }

    public void setDireccionFinal(String direccionFinal) {
        this.direccionFinal = direccionFinal;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "direccionInicioNombre='" + direccionInicioNombre + ", direccionFinalNombre='" + direccionFinalNombre + ", distancia='" + distancia + ", fechaInicio='" + fechaInicio + ", fechaFin='" + fechaFin;
    }
}
