package fr.valentinthuillier.portfolio.dto;

public class Projet {
    private int id;
    private String name;
    private String description;

    public Projet(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageURL() {
        return "contents/projets/" + this.name.replace(" ", "-") + ".png";
    }
}
