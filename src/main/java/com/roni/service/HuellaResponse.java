package com.roni.service;

public class HuellaResponse {
    private String id;
    private String template;
    private String imagen;
    
    public HuellaResponse() {

    }

    public void setId(String id) {
        this.id = id;
    } 

    public String getId() {
        return this.id;
    }

    public void setTemplate(String template) {
        this.template = template;
    } 

    public String getTemplate() {
        return this.template;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    } 

    public String getImagen() {
        return this.imagen;
    }

   
}