package fr.valentinthuillier.portfolio.dao;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProjetDao implements IDao<Projet> {

    private static final Logger log = Logger.getLogger(ProjetDao.class.getName());

    @Override
    public Projet find(int id) {
        Projet projet = null;
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM projets WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                projet = new Projet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return projet;
    }

    @Override
    public Projet[] findAll() {
        List<Projet> projets = new ArrayList<>();
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM projets");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projets.add(new Projet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return projets.toArray(new Projet[0]);
    }

    @Override
    public boolean save(Projet projet) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO projets (name, description) VALUES (?, ?)");
            ps.setString(1, projet.getName());
            ps.setString(2, projet.getDescription());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Projet projet) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("UPDATE projets SET name = ?, description = ? WHERE id = ?");
            ps.setString(1, projet.getName());
            ps.setString(2, projet.getDescription());
            ps.setInt(3, projet.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Projet projet) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM projets WHERE id = ?");
            ps.setInt(1, projet.getId());
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM projets");
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
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM projets");
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
