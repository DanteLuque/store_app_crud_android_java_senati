package com.senati.store.models;

public class StoreModel {
    private int id;
    private String desarrollador;
    private String nombre;
    private String licencia;
    private int version;
    private int espacioMb;
    private double precio;

    public StoreModel(
            int id,
            String desarrollador,
            String nombre,
            String licencia,
            int version,
            int espacioMb,
            double precio
    ) {
        this.id = id;
        this.desarrollador = desarrollador;
        this.nombre = nombre;
        this.licencia = licencia;
        this.version = version;
        this.espacioMb = espacioMb;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getEspacioMb() {
        return espacioMb;
    }

    public void setEspacioMb(int espacioMb) {
        this.espacioMb = espacioMb;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
