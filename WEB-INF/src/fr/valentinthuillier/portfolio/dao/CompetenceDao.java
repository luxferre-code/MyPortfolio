package fr.valentinthuillier.portfolio.dao;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Competence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CompetenceDao implements IDao<Competence> {

    private static final Logger log = Logger.getLogger(CompetenceDao.class.getName());

    @Override
    public Competence find(int id) {
        Competence competence = null;
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM competences WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                competence = new Competence(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return competence;
    }

    @Override
    public Competence[] findAll() {
        List<Competence> competences = new ArrayList<>();
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM competences");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                competences.add(new Competence(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return competences.toArray(new Competence[0]);
    }

    @Override
    public boolean save(Competence object) {
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO competences (name, description) VALUES (?, ?)");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Competence object) {
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("UPDATE competences SET name = ?, description = ? WHERE id = ?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setInt(3, object.getId());
            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Competence object) {
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM competences WHERE id = ?");
            ps.setInt(1, object.getId());
            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM competences");
            return ps.executeUpdate() == count();
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        try(Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM competences");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(SQLException e) {
            log.severe(e.getMessage());
        }
        return -1;
    }
}
