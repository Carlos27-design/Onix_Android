package com.inacap.onix;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String correo;
    private String nroTelefonico;

    public Usuario() {
    }

    public Usuario(int id, String rut, String nombre, String apellido, String contrasena, String correo, String nroTelefonico) {
        this.id = id;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nroTelefonico = nroTelefonico;
    }

    protected Usuario(Parcel in){
        id = in.readInt();
        rut = in.readString();
        nombre = in.readString();
        apellido = in.readString();
        contrasena = in.readString();
        correo = in.readString();
        nroTelefonico = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNroTelefonico() {
        return nroTelefonico;
    }

    public void setNroTelefonico(String nroTelefonico) {
        this.nroTelefonico = nroTelefonico;
    }

    @Override
    public String toString() {
        return  ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", nroTelefonico='" + nroTelefonico + '\'';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(rut);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeString(contrasena);
        dest.writeString(correo);
        dest.writeString(nroTelefonico);
    }
}
