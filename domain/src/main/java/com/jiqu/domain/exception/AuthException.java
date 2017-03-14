package com.jiqu.domain.exception;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
public class AuthException extends Throwable{

    public AuthException() {
    }

    public AuthException(String s) {
        super(s);
    }

    public AuthException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
