package com.roni.service;

import java.util.Objects;


public class FingerPrintRequest {
    private static final long serialVersionUID = 1L;    
    private Long id;
    private String template;
    private byte[] imagen;
    private String imagenContentType;
    private EmpleadoResponse empleado;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getImagenContentType() {
        return imagenContentType;
    }

    public void setImagenContentType(String imagenContentType) {
        this.imagenContentType = imagenContentType;
    }

    public EmpleadoResponse getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoResponse empleado) {
        this.empleado = empleado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FingerPrintRequest huella = (FingerPrintRequest) o;
        if (huella.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), huella.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FingerPrintRequest{" +
            "id=" + getId() +
            ", template='" + getTemplate() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", imagenContentType='" + getImagenContentType() + "'" +
            "}";
    }
}


