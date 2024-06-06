package fr.valentinthuillier.portfolio.dao;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdminDao implements IDao<Admin> {

    private static final Logger log = Logger.getLogger(AdminDao.class.getName());

    @Override
    public Admin find(int id) {
        Admin admin = null;
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM admin WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return admin;
    }

    @Override
    public Admin[] findAll() {
        List<Admin> admins = new ArrayList<>();
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM admin");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                admins.add(new Admin(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return admins.toArray(new Admin[0]);
    }

    @Override
    public boolean save(Admin admin) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO admin (username, password) VALUES (?, ?)");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Admin admin) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("UPDATE admin SET username = ?, password = ? WHERE id = ?");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setInt(3, admin.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Admin admin) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("DELETE FROM admin WHERE id = ?");
            ps.setInt(1, admin.getId());
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM admin");
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
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM admin");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return -1;
    }

    public boolean exists(String mail, String password) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("SELECT id FROM admin WHERE mail = ? AND password = ?");
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

    public boolean createAdmin(String username, String password) {
        try (Connection con = DS.getConnection()) {
            assert con != null;
            PreparedStatement ps = con.prepareStatement("INSERT INTO admin (mail, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        return false;
    }

}
