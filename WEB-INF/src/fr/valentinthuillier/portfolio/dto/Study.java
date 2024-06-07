package fr.valentinthuillier.portfolio.dto;

import java.util.Date;

public class Study {

    private final int id;
    private String name;
    private String description;
    private String lieu;
    private Date dateDebut;
    private Date dateFin;

    public Study(int id, String name, String description, String lieu, Date dateDebut, Date dateFin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Study(String name, String description, String lieu, Date dateDebut, Date dateFin) {
        this(-1, name, description, lieu, dateDebut, dateFin);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

}