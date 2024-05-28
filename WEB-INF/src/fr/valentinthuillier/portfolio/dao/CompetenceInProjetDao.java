package fr.valentinthuillier.portfolio.dao;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.CompetenceInProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CompetenceInProjetDao implements IDao<CompetenceInProjet> {

    private static final Logger log = Logger.getLogger(CompetenceInProjetDao.class.getName());

    @Override
    public CompetenceInProjet find(int id) {
        // Cette méthode n'est pas applicable pour cette table, car elle a une clé primaire composite.
        throw new UnsupportedOperationException("Find by ID not supported for composite keys.");
    }

    public CompetenceInProjet find(int competenceId, int projetId) {
        CompetenceInProjet cip = null;
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM competences_in_projets WHERE competence_id = ? AND projet_id = ?");
            ps.setInt(1, competenceId);
            ps.setInt(2, projetId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cip = new CompetenceInProjet(
                        rs.getInt("competence_id"),
                        rs.getInt("projet_id")
                );
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return cip;
    }

    @Override
    public CompetenceInProjet[] findAll() {
        List<CompetenceInProjet> cips = new ArrayList<>();
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM competences_in_projets");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cips.add(new CompetenceInProjet(
                        rs.getInt("competence_id"),
                        rs.getInt("projet_id")
                ));
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return cips.toArray(new CompetenceInProjet[0]);
    }

    @Override
    public boolean save(CompetenceInProjet cip) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO competences_in_projets (competence_id, projet_id) VALUES (?, ?)");
            ps.setInt(1, cip.getCompetenceId());
            ps.setInt(2, cip.getProjetId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(CompetenceInProjet cip) {
        // Cette méthode n'est pas applicable car nous ne pouvons pas mettre à jour les clés primaires.
        throw new UnsupportedOperationException("Update not supported for composite keys.");
    }

    @Override
    public boolean delete(CompetenceInProjet cip) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM competences_in_projets WHERE competence_id = ? AND projet_id = ?");
            ps.setInt(1, cip.getCompetenceId());
            ps.setInt(2, cip.getProjetId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM competences_in_projets");
            return ps.executeUpdate() == count();
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM competences_in_projets");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return -1;
    }
}
