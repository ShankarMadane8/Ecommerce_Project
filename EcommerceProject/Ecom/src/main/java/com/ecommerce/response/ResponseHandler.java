package com.ecommerce.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {
    private int statusCode;
    private String responseDescription;
    private Object data;

    public ResponseHandler(int value, String responseDescription, Object data) {
        this.statusCode=value;
        this.responseDescription=responseDescription;
        this.data=data;
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus status,String responseDescription, Object data) {
        ResponseHandler response = new ResponseHandler(status.value(), responseDescription, data);
        return new ResponseEntity<>(response, status);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
