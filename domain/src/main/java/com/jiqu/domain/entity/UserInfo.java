package com.jiqu.domain.entity;

/**
 * Created by CJJ on 2017/3/13.
 *
 */

public class UserInfo {

    String userName;
    String gender;
    String age;
    String motto;
    String identity;

    public UserInfo() {
    }

    public UserInfo(String userName, String gender, String age, String motto, String identity) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.motto = motto;
        this.identity = identity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
