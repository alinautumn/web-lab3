package com.alinautumn.web_lab3.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "shots")
public class Shot implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double x;
    private double y;
    private double r;
    private boolean status;
    private long currentTime;
    private long scriptTime;
    public Shot(){

    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public long getScriptTime() {
        return scriptTime;
    }

    public void setScriptTime(long scriptTime) {
        this.scriptTime = scriptTime;
    }

    public String getFormattedTime() {
        return new SimpleDateFormat("dd.MM.yy HH:mm:ss")
                .format(new Date(currentTime));
    }

    @Override
    public Shot clone() {
        Shot cloned = new Shot();
        cloned.x = x;
        cloned.y = y;
        cloned.r = r;
        return cloned;
    }
}