package com.application.Entity;

import com.application.Models.PatientsObject;
import com.application.Utils.LevelGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "PATIENTS")
public class Patients {

    @Id
    @Column(name = "idPATIENTS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "personalIdentity")
    private String personalIdentity;

    @Column(name = "information")
    private String information;

    @Column(name = "levelGames")
    @Enumerated(EnumType.STRING)
    private LevelGame levelGames;

    @Column(name = "levelOfMmse")
    private boolean levelOfMmse;

    public Patients() { }

    public Patients(PatientsObject patients) {
        this.id = patients.getId();
        this.name = patients.getName();
        this.surname = patients.getSurname();
        this.login = patients.getLogin();
        this.password = patients.getPassword();
        this.personalIdentity = patients.getPersonalIdentity();
        this.information = patients.getInformation();
        this.levelGames = patients.getLevel();
        this.levelOfMmse = patients.getLevelOfMMSE();
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

    public String getPersonalIdentity() {
        return personalIdentity;
    }

    public void setPersonalIdentity(String personalIdentity) {
        this.personalIdentity = personalIdentity;
    }

    public String getInformation() {
        return information;
    }

    public void setInformations(String information) {
        this.information = information;
    }

    public LevelGame getLevelGames() {
        return levelGames;
    }

    public void setLevelGames(LevelGame levelGames) {
        this.levelGames = levelGames;
    }

    public boolean isLevelOfMmse() {
        return levelOfMmse;
    }

    public void setLevelOfMmse(boolean levelOfMmse) {
        this.levelOfMmse = levelOfMmse;
    }

    public boolean getLevelOfMMSE() {
        return levelOfMmse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
}
