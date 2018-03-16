package com.roni.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.roni.config.ApplicationProperties;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Component
public class BackendServiceImpl implements BackendService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties properties;

    public boolean setEmpleadoFingerPrint(FingerPrintRequest request, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token );
        HttpEntity<FingerPrintRequest> entity = new HttpEntity<FingerPrintRequest>(request, headers);
        ResponseEntity<FingerPrintRequest> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/huellas",
                                                                        HttpMethod.POST,
                                                                        entity,
                                                                        FingerPrintRequest.class);


        return response.getStatusCode().is2xxSuccessful();
    }

    public boolean registerEmpleadoAction(String empleadoId, String registroId, String token) {
        RegistroEmpleadoRequest request = new RegistroEmpleadoRequest();
        request.setFechaHora(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX").withZone(ZoneOffset.UTC).format(Instant.now()) );
        EmpleadoResponse empleado = new EmpleadoResponse();
        empleado.setId( Long.valueOf(empleadoId) );
        RegistroResponse registro = new RegistroResponse();
        registro.setId( Long.valueOf(registroId) );
        request.setEmpleado(empleado);
        request.setRegistro(registro);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token );
        HttpEntity<RegistroEmpleadoRequest> entity = new HttpEntity<RegistroEmpleadoRequest>(request, headers);
        ResponseEntity<RegistroEmpleadoRequest> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/registro-empleados",
                                                                        HttpMethod.POST,
                                                                        entity,
                                                                        RegistroEmpleadoRequest.class);


        return response.getStatusCode().is2xxSuccessful();
    }

    public String getEmpleadoFingerPrint(String id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token );
        HttpEntity<HuellaResponse> entity = new HttpEntity<HuellaResponse>(headers);
        ResponseEntity<HuellaResponse> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/huellas/" + id,
                                                                        HttpMethod.GET,
                                                                        entity,
                                                                        HuellaResponse.class);

        return response.getBody().getTemplate();
    }

    public EmpleadoResponse getEmpleadoById(String id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token );
        HttpEntity<EmpleadoResponse> entity = new HttpEntity<EmpleadoResponse>(headers);
        ResponseEntity<EmpleadoResponse> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/empleados/" + id,
                                                                        HttpMethod.GET,
                                                                        entity,
                                                                        EmpleadoResponse.class);

        return response.getBody();
    }

    
    public LoginResponse getToken() {
        HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("admin","admin"));
        ResponseEntity<LoginResponse> response = restTemplate.exchange(properties.getRegistroUrl() + "/api/authenticate",
                                                                        HttpMethod.POST,
                                                                        request,
                                                                        LoginResponse.class);

        return response.getBody();
    }
}