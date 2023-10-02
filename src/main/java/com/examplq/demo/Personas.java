package com.examplq.demo;

public class Personas {
    public String code_admin;
    public String code;
    public String name;
    public String lastname;
    public String charge;
    public String date;
    public String bussines;

    public Personas(String code_admin, String code, String name, String lastname, String charge, String date, String bussines) {
        this.code_admin = code_admin;
        this.code = code;
        this.name = name;
        this.lastname = lastname;
        this.charge = charge;
        this.date = date;
        this.bussines = bussines;
    }

    public String getCode_admin() {
        return code_admin;
    }

    public void setCode_admin(String code_admin) {
        this.code_admin = code_admin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBussines() {
        return bussines;
    }

    public void setBussines(String bussines) {
        this.bussines = bussines;
    }
}