package com.example.starter.domain;

public class XkcdDomainException extends RuntimeException {

    public XkcdDomainException(final String msg) {
        super(msg);
    }

    public XkcdDomainException(final String msg,
                               final Exception e) {
        super(msg, e);
    }
}
