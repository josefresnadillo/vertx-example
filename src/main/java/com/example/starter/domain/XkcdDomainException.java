package com.example.starter.domain;

public class XkcdDomainException extends RuntimeException {

    public XkcdDomainException(String msg) {
        super(msg);
    }

    public XkcdDomainException(String msg, Exception e) {
        super(msg, e);
    }
}
