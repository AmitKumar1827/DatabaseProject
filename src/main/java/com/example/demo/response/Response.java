package com.example.demo.response;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private T data;
    private Error error;
    private boolean result;

    public Response(T data) {
        result = true;
        this.data = data;
    }

    public Response(Error error) {
        result = false;
        this.error = error;
    }
}
