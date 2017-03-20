package com.jiqu.domain.entity;

import com.annno.Mapper;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
@Mapper
public class Service extends RealmObject {
    //类型名称
    @SerializedName("TypeName")
    private String typeName;
    //服务ID
    @SerializedName("ServiceId")
    private String serviceId;
    //服务名称
    @SerializedName("ServiceName")
    private String serviceName;

    public Service() {
    }

    public Service(String typeName, String serviceId, String serviceName) {
        this.typeName = typeName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "Service{" +
                "typeName='" + typeName + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
