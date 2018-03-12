package com.roni.service;

public interface BackendService {
    public LoginResponse getToken();
    public EmpleadoResponse getEmpleadoById(String id, String token);
}