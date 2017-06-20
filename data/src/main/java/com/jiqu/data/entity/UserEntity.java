package com.jiqu.data.entity;

import io.realm.RealmObject;

/**
 * Created by CJJ on 2017/3/13.
 *domain 层由于不能依赖realm,无法让UserInfo继承RealmObject,但是为了方便，应用层直接使用了domain层的
 * 返回类类型数据，所以data层实体类
 *
 */
public class UserEntity extends RealmObject {
    private String userId;
    private String userName;
    private String gender;
    private String age;
    private String motto;
    private String identity;

    public UserEntity() {
    }

    public UserEntity(String userId, String userName, String gender, String age, String motto, String identity) {
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
