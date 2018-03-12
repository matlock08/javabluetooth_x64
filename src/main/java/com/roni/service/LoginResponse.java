package com.roni.service;

public class LoginResponse {
    private String id_token;
    
    public LoginResponse() {

    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    } 

    

    public String getId_token() {
        return this.id_token ;
    }

   
}