package com.alinautumn.web_lab3.beans;

import com.alinautumn.web_lab3.dao.ShotDao;
import com.alinautumn.web_lab3.utils.AreaHitChecker;
import com.alinautumn.web_lab3.entity.Shot;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
@Named("shotBean")
public class ShotBean implements Serializable {
    private Shot shot;
    @Inject
    private ShotDao shotDao;
    private List<Shot> shotsList = new CopyOnWriteArrayList<>();
    private int timezone;

    @PostConstruct
    public void postConstruct() {
        shot = new Shot();
        shotDao.createEntityManager();
        shotsList = shotDao.getShotsFromDB();
    }

    public void add() {
            LocalDateTime startTime = LocalDateTime.now(ZoneOffset.UTC);
            shot.setStatus(AreaHitChecker.isHit(shot));
            shot.setCurrentTime(System.currentTimeMillis());
            shot.setScriptTime(Math.round(LocalDateTime.now().minusNanos(startTime.getNano()).getNano() * 0.001));
            shotsList.add(shot);
            shotDao.addShotToDB(shot);
            shot = shot.clone();
    }

    public void addThroughPlot(){
        String string_x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String string_y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String string_r = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");
        shot.setX(Double.parseDouble(string_x));
        shot.setY(Double.parseDouble(string_y));
        shot.setR(Double.parseDouble(string_r));
        add();
    }

    public void clear(){
        shotDao.clearShotsInBD();
        shotsList = shotDao.getShotsFromDB();
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

    public List<Shot> getShotsList() {
        return shotsList;
    }

    public void setShotsList(LinkedList<Shot> shotsList) {
        this.shotsList = shotsList;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone() {
        timezone = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone"));
    }
}
