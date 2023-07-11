package com.example.calculadoraweb.model;

public class Usuario {

    private int id;
    private String nombre;
    private String correo;
    private String contra;
    private String codigo;

    public Usuario(int id, String nombre, String correo, String contra) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
    }

    public Usuario() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
