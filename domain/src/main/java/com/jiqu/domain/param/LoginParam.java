package com.jiqu.domain.param;

/**
 * Created by CJJ on 2017/3/7.
 */

public class LoginParam {

    public String userName;
    public String password;
    public String captcha;

    public LoginParam(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginParam(String userName, String password, String captcha) {
        this.userName = userName;
        this.password = password;
        this.captcha = captcha;
    }
}
