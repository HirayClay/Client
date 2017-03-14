package com.jiqu.client.mvp.view;

import com.jiqu.domain.entity.Service;

import java.util.List;

/**
 * @author: CJJ
 * @date 2017/3/13
 */
public interface ServiceView extends AuthLoadView {

    void onServiceLoad(List<Service> services);
}
