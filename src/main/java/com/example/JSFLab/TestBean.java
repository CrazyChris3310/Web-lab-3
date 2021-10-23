package com.example.JSFLab;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="testBean")
@RequestScoped
public class TestBean {
    String name;
    String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting name: " + name);
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Setting surname: " + surname);
        this.surname = surname;
    }
}
