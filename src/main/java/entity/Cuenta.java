package entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
/**
 *
 * @author Administrador
 */
public class Cuenta {
    private int id;
    private int empleado;
    private float montoDeuda;
    private int aquien;
    private String estado;
    private Date fecha;

    public Cuenta(int empleado, float montoDeuda, int aquien, String estado, Date fecha) {
        this.empleado = empleado;
        this.montoDeuda = montoDeuda;
        this.aquien = aquien;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Cuenta() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public float getMontoDeuda() {
        return montoDeuda;
    }

    public void setMontoDeuda(float montoDeuda) {
        this.montoDeuda = montoDeuda;
    }

    public int getAquien() {
        return aquien;
    }

    public void setAquien(int aquien) {
        this.aquien = aquien;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
