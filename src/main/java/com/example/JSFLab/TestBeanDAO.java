package com.example.JSFLab;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="testBeanDAO")
@ApplicationScoped
public class TestBeanDAO {
    private List<TestBean> beans = new ArrayList<>();

    public List<TestBean> getBeans() {
        return beans;
    }

    public String addBean(TestBean bean) {
        beans.add(bean);
        return "/facelets/header";
    }
}
