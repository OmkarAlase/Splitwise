package com.splitwise.demo.dtos;

import lombok.Data;

@Data
public class Response{
    private String message;
    private ResponseStatus status;

    public static Response getSuccessResponse(String message){
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    public static Response getFailureResponse(String message){
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(ResponseStatus.FAILURE);
        return response;
    }
}
