package com.project.bootcamp.exceptions;

public class ExceptionResponse {

    public String message;

    ExceptionResponse(String message){
        this.message= message;
    }

    public String getMessage() {
        return message;
    }

}
