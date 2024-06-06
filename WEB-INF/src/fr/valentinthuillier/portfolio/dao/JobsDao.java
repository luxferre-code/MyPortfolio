package fr.valentinthuillier.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Jobs;

public class JobsDao implements IDao<Jobs> {

    @Override
    public Jobs find(int id) {
        Jobs jobs = null;
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                jobs = new Jobs(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("entreprise"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public Jobs[] findAll() {
        List<Jobs> jobs = new ArrayList<>();
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jobs");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                jobs.add(new Jobs(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("entreprise"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs.toArray(new Jobs[jobs.size()]);
    }

    @Override
    public boolean save(Jobs object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO jobs (name, description, entreprise, date_debut, date_fin) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setString(3, object.getEntreprise());
            ps.setDate(4, new java.sql.Date(object.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(object.getEndDate().getTime()));
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Jobs object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE jobs SET name = ?, description = ?, entreprise = ?, date_debut = ?, date_fin = ? WHERE id = ?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getDescription());
            ps.setString(3, object.getEntreprise());
            ps.setDate(4, new java.sql.Date(object.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(object.getEndDate().getTime()));
            ps.setInt(6, object.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Jobs object) {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM jobs WHERE id = ?");
            ps.setInt(1, object.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM jobs");
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int count() {
        try(Connection con = DS.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM jobs");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
