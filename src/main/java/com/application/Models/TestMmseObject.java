package com.application.Models;

import com.application.Entity.TestMmse;

import java.util.Date;

public class TestMmseObject {
    private int id;
    private int result;
    private String time;
    private Date date;
    private int idPatients;

    public TestMmseObject() { }

    public TestMmseObject(int result, String time, Date date, int idPatients) {
        this.result =result;
        this.time = time;
        this.date = date;
        this.idPatients = idPatients;
    }

    public TestMmseObject(TestMmse testMmse) {
        this.id = testMmse.getId();
        this.result = testMmse.getResult();
        this.time = testMmse.getTime();
        this.date = testMmse.getDate();
        this.idPatients = testMmse.getIdPatients();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdPatients() {
        return idPatients;
    }

    public void setIdPatients(int idPatients) {
        this.idPatients = idPatients;
    }
}
