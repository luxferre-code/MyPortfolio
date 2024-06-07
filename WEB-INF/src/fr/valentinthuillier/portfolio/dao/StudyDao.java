package fr.valentinthuillier.portfolio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Study;

public class StudyDao implements IDao<Study> {

    @Override
    public Study find(int id) {
        Study study = null;
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT name, description, lieu, date_debut, date_fin FROM study WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                study = new Study(
                    id,
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("lieu"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
                );
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return study;
    }

    @Override
    public Study[] findAll() {
        List<Study> studies = new ArrayList<>();

        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT id, name, description, lieu, date_debut, date_fin FROM study");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                studies.add(new Study(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("lieu"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
                ));
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return studies.toArray(new Study[0]);        
    }

    @Override
    public boolean save(Study object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO study(name, description, lieu, date_debut, date_fin) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setString(3, object.getLieu());
            ps.setDate(4, new Date(object.getDateDebut().getTime()));
            ps.setDate(5, new Date(object.getDateFin().getTime()));
            return ps.executeUpdate() == 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Study object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE study SET name = ?, description = ?, lieu = ?, date_debut = ?, date_fin = ? WHERE id = ?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setString(3, object.getLieu());
            ps.setDate(4, new Date(object.getDateDebut().getTime()));
            ps.setDate(5, new Date(object.getDateFin().getTime()));
            ps.setInt(6, object.getId());
            return ps.executeUpdate() == 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Study object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM study WHERE id = ?");
            ps.setInt(1, object.getId());
            return ps.executeUpdate() == 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM study");
            return ps.executeUpdate() >= 1;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public int count() {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM study");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
}
