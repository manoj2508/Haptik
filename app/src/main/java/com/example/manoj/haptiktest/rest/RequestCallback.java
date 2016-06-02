package com.example.manoj.haptiktest.rest;

public interface RequestCallback<T> {
    void onRequestSuccess(T t);

    void onRequestError(String error);
}