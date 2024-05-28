package fr.valentinthuillier.portfolio.dto;

import fr.valentinthuillier.portfolio.dao.CompetenceDao;

import java.util.Objects;

public class Competence {

    private final int id;
    private String name;
    private String description;

    public Competence(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Competence(String name, String description) {
        this.id = new CompetenceDao().count() + 1;
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getImageURL() {
        return "images/competences/" + name.toLowerCase().replace(" ", "-") + ".png";
    }

}
