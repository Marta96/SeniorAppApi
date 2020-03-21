package com.application.Entity;

import com.application.Models.CaregiversObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "CAREGIVERS")
public class Caregivers {

    @Id
    @Column(name = "idCAREGIVERS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;

    public Caregivers() {}

    public Caregivers(CaregiversObject caregiversObject) {
        this.setName(caregiversObject.getName());
        this.setSurname(caregiversObject.getSurname());
        this.setLogin(caregiversObject.getLogin());
        this.setPassword(caregiversObject.getPassword());
    }

    public Caregivers(String name, String surname, String login, String password) {
        this.setName(name);
        this.setSurname(surname);
        this.setLogin(login);
        this.setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonIgnore
    public String getLogin() {
        return login;
    }

    @JsonProperty
    private void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    private void setPassword(String password) {
        this.password = password;
    }
}
