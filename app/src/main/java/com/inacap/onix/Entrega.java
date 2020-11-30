package com.inacap.onix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Entrega implements Parcelable {
    private int id;
    private int usuario_id;
    private int vehiculo_id;
    private int ruta_id;
    private int estado_id;
    private String direccionEntregaNombre;
    private String direccionEntrega;
    private String indicaciones;
    private String nroDocumentoEntregado;
    private String fechaInicio;
    private String fechaEntrega;
    private String recibidoRut;
    private String recibidoNombre;
    private String recibidoApellido;
    private ArrayList<Entrega> lista = new ArrayList<Entrega>();

    public Entrega() {
    }

    protected Entrega(Parcel in) {
        id = in.readInt();
        usuario_id = in.readInt();
        vehiculo_id = in.readInt();
        ruta_id = in.readInt();
        estado_id = in.readInt();
        direccionEntregaNombre = in.readString();
        direccionEntrega = in.readString();
        indicaciones = in.readString();
        nroDocumentoEntregado = in.readString();
        fechaInicio = in.readString();
        fechaEntrega = in.readString();
        recibidoRut = in.readString();
        recibidoNombre = in.readString();
        recibidoApellido = in.readString();
        lista = in.createTypedArrayList(Entrega.CREATOR);
    }

    public static final Creator<Entrega> CREATOR = new Creator<Entrega>() {
        @Override
        public Entrega createFromParcel(Parcel in) {
            return new Entrega(in);
        }

        @Override
        public Entrega[] newArray(int size) {
            return new Entrega[size];
        }
    };

    public Entrega(int id, int usuario_id, int vehiculo_id, int ruta_id, int estado_id, String direccionEntregaNombre, String direccionEntrega, String indicaciones, String nroDocumentoEntregado, String fechaInicio, String fechaEntrega, String recibidoRut, String recibidoNombre, String recibidoApellido) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.vehiculo_id = vehiculo_id;
        this.ruta_id = ruta_id;
        this.estado_id = estado_id;
        this.direccionEntregaNombre = direccionEntregaNombre;
        this.direccionEntrega = direccionEntrega;
        this.indicaciones = indicaciones;
        this.nroDocumentoEntregado = nroDocumentoEntregado;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.recibidoRut = recibidoRut;
        this.recibidoNombre = recibidoNombre;
        this.recibidoApellido = recibidoApellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(int vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public int getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(int ruta_id) {
        this.ruta_id = ruta_id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getDireccionEntregaNombre() {
        return direccionEntregaNombre;
    }

    public void setDireccionEntregaNombre(String direccionEntregaNombre) {
        this.direccionEntregaNombre = direccionEntregaNombre;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getNroDocumentoEntregado() {
        return nroDocumentoEntregado;
    }

    public void setNroDocumentoEntregado(String nroDocumentoEntregado) {
        this.nroDocumentoEntregado = nroDocumentoEntregado;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getRecibidoRut() {
        return recibidoRut;
    }

    public void setRecibidoRut(String recibidoRut) {
        this.recibidoRut = recibidoRut;
    }

    public String getRecibidoNombre() {
        return recibidoNombre;
    }

    public void setRecibidoNombre(String recibidoNombre) {
        this.recibidoNombre = recibidoNombre;
    }

    public String getRecibidoApellido() {
        return recibidoApellido;
    }

    public void setRecibidoApellido(String recibidoApellido) {
        this.recibidoApellido = recibidoApellido;
    }

    @Override
    public String toString() {
        return "Direccion Entrega: " + direccionEntregaNombre +  "- Indicaciones: " + indicaciones + "- N° de Documento:" + nroDocumentoEntregado;

    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(usuario_id);
        dest.writeInt(vehiculo_id);
        dest.writeInt(ruta_id);
        dest.writeInt(estado_id);
        dest.writeString(direccionEntregaNombre);
        dest.writeString(direccionEntrega);
        dest.writeString(indicaciones);
        dest.writeString(nroDocumentoEntregado);
        dest.writeString(fechaInicio);
        dest.writeString(fechaEntrega);
        dest.writeString(recibidoRut);
        dest.writeString(recibidoNombre);
        dest.writeString(recibidoApellido);
        dest.writeTypedList(lista);
    }
}
