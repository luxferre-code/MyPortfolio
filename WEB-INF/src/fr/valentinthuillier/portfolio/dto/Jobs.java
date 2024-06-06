package fr.valentinthuillier.portfolio.dto;

import java.time.LocalDate;
import java.util.Date;

public class Jobs {
    
    private final int id;
    private String name;
    private String description;
    private String entreprise;
    private Date startDate;
    private Date endDate;

    public Jobs(int id, String name, String description, String entreprise, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.entreprise = entreprise;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDates() {
        return LocalDate.parse(startDate.toString()).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + LocalDate.parse(endDate.toString()).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
