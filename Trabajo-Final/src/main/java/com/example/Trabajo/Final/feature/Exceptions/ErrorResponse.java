package com.example.Trabajo.Final.feature.Exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String mensaje;
    private String path;
    private Map<String, String> errors;

    public ErrorResponse(){
    }

    public ErrorResponse(int status, String error, String mensaje, String path){
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.mensaje = mensaje;
        this.path = path;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public int getStatus(){
        return status;
    }
    public String getError(){
        return error;
    }
    public String getMensaje(){
        return mensaje;
    }
    public String getPath(){
        return path;
    }
    public Map<String, String> getErrors(){
        return errors;
    }
    public void setErrors(Map<String, String> errors){
        this.errors = errors;
    }
}
