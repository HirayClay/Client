package com.jiqu.data.mapper;

import com.jiqu.domain.entity.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: CJJ
 * @date 2017/3/14
 */
public class ServiceMapper {


    public Service map(Service service) {

        Service s = new Service();
        s.setTypeName(service.getTypeName());
        s.setServiceName(service.getServiceName());
        s.setServiceId(service.getServiceId());
        return s;
    }

    public List<Service> map(List<Service> serviceList) {
        List<Service> services = new ArrayList<>();
        if (serviceList != null && !serviceList.isEmpty()) {
            for (Service s :
                    serviceList) {
                services.add(map(s));
            }

        }

        return services;
    }
}
