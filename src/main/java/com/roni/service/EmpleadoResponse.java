package com.roni.service;

import java.io.Serializable;
import java.util.*;

/**
 * A Empleado.
 */
public class EmpleadoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String numeroEmpleado;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String genero;
    private String foto;
    private String fotoContentType;
    private String fechaAlta;
    private String estado;
    private Set<FingerPrintRequest> huellas = new HashSet<FingerPrintRequest>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<FingerPrintRequest> getHuellas() {
        return huellas;
    }

    public void setHuellas(Set<FingerPrintRequest> huellas) {
        this.huellas = huellas;
    }
   

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmpleadoResponse empleado = (EmpleadoResponse) o;
        if (empleado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), empleado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Empleado{" +
            "id=" + getId() +
            ", numeroEmpleado='" + getNumeroEmpleado() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellidoMaterno='" + getApellidoMaterno() + "'" +
            ", apellidoPaterno='" + getApellidoPaterno() + "'" +
            ", genero='" + getGenero() + "'" +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", fechaAlta='" + getFechaAlta() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
