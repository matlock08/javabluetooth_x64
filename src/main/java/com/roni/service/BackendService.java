package com.roni.service;

public interface BackendService {
    public LoginResponse getToken();
    public String getEmpleadoById(String id);
}