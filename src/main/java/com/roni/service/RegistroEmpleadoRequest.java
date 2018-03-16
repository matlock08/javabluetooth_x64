package com.roni.service;

public class RegistroEmpleadoRequest {
    private String fechaHora;
    private EmpleadoResponse empleado;
    private RegistroResponse registro;

    public RegistroEmpleadoRequest() {

    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    } 

    public String getFechaHora() {
        return this.fechaHora ;
    }

    public void setEmpleado(EmpleadoResponse empleado) {
        this.empleado = empleado;
    } 

    public EmpleadoResponse getEmpleado() {
        return this.empleado ;
    }

    public void setRegistro(RegistroResponse registro) {
        this.registro = registro;
    } 

    public RegistroResponse getRegistro() {
        return this.registro ;
    }

   
}