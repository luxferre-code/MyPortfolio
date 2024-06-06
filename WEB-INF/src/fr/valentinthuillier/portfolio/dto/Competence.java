package fr.valentinthuillier.portfolio.dto;

import fr.valentinthuillier.portfolio.dao.CompetenceDao;

import java.util.Objects;

public class Competence {

    private final int id;
    private String name;

    public Competence(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Competence(String name) {
        this.id = new CompetenceDao().count() + 1;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
