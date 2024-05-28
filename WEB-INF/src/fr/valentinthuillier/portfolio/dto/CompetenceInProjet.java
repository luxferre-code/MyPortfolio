package fr.valentinthuillier.portfolio.dto;

public class CompetenceInProjet {
    private int competenceId;
    private int projetId;

    public CompetenceInProjet(int competenceId, int projetId) {
        this.competenceId = competenceId;
        this.projetId = projetId;
    }

    // Getters and setters
    public int getCompetenceId() { return competenceId; }
    public void setCompetenceId(int competenceId) { this.competenceId = competenceId; }
    public int getProjetId() { return projetId; }
    public void setProjetId(int projetId) { this.projetId = projetId; }
}
