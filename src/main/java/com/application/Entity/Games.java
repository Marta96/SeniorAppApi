package com.application.Entity;

import com.application.Models.GamesObject;
import com.application.Utils.LevelGame;
import com.application.Utils.NameGame;
import com.application.Utils.StatusGame;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="GAMES")
public class Games {

    @Id
    @Column(name = "idGAMES")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusGame status;
    @Column(name = "time")
    private String time;
    @Column(name = "date")
    private Date date;
    @Column(name = "PATIENTS_idPATIENTS")
    private int patientId;
    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private LevelGame level;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private NameGame name;

    public Games() { }

    public Games(StatusGame status, String time, Date date, int patientId,
                 LevelGame level, NameGame name) {
        this.status = status;
        this.time = time;
        this.date = date;
        this.patientId = patientId;
        this.level = level;
        this.name = name;
    }

    public Games(GamesObject gamesObject) {
        this.status = gamesObject.getStatus();
        this.time = gamesObject.getTime();
        this.date = gamesObject.getDate();
        this.patientId = gamesObject.getIdPatients();
        this.level = gamesObject.getLevel();
        this.name = gamesObject.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusGame getStatus() {
        return status;
    }

    public void setStatus(StatusGame status) {
        this.status = status;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public LevelGame getLevel() {
        return level;
    }

    public void setLevel(LevelGame level) {
        this.level = level;
    }

    public NameGame getName() {
        return name;
    }

    public void setName(NameGame name) {
        this.name = name;
    }
}
