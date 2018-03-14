package com.roni.service;

public interface BackendService {
    public LoginResponse getToken();
    public EmpleadoResponse getEmpleadoById(String id, String token);
    public boolean setEmpleadoFingerPrint(FingerPrintRequest request, String token);
}