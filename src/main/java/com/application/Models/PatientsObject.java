package com.application.Models;

import com.application.Entity.Patients;
import com.application.Utils.LevelGame;

public class PatientsObject {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String personalIdentity;
    private String information;
    private LevelGame level;
    private boolean levelOfMMSE;
    private boolean active;

    public PatientsObject() { }

    public PatientsObject(String name, String surname, String login, String password, String personalIdentity,
                          String information, LevelGame level, boolean levelOfMMSE, boolean active) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.personalIdentity = personalIdentity;
        this.information = information;
        this.level = level;
        this.levelOfMMSE = levelOfMMSE;
        this.active = active;
    }

    public PatientsObject(Patients patients) {
        this.id = patients.getId();
        this.name = patients.getName();
        this.surname = patients.getSurname();
        this.personalIdentity = patients.getPersonalIdentity();
        this.information = patients.getInformation();
        this.level = patients.getLevelGames();
        this.levelOfMMSE = patients.getLevelOfMMSE();
        this.login = patients.getLogin();
        this.active = patients.isActive();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonalIdentity() {
        return personalIdentity;
    }

    public void setPersonalIdentity(String personalIdentity) {
        this.personalIdentity = personalIdentity;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LevelGame getLevel() {
        return level;
    }

    public void setLevel(LevelGame level) {
        this.level = level;
    }

    public boolean isLevelOfMMSE() {
        return levelOfMMSE;
    }

    public void setLevelOfMMSE(boolean levelOfMMSE) {
        this.levelOfMMSE = levelOfMMSE;
    }

    public boolean getLevelOfMMSE() {
        return levelOfMMSE;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
