package com.roni.service;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Empleado.
 */
public class RegistroResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
        

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
