package fr.valentinthuillier.portfolio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.valentinthuillier.portfolio.DS;
import fr.valentinthuillier.portfolio.dto.Message;

public class MessageDao implements IDao<Message> {

    @Override
    public Message find(int id) {
        Message message = null;
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT name, mail, message, repondu FROM message WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                message = new Message(id, rs.getString("name"), rs.getString("mail"), rs.getString("message"), rs.getBoolean("repondu"));
            }

        } catch(Exception e) {
            //pass
        }
        return message;
    }

    @Override
    public Message[] findAll() {
        List<Message> messages = new ArrayList<>();
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT id, name, mail, message, repondu FROM message");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                messages.add(new Message(rs.getInt("id"), rs.getString("name"), rs.getString("mail"), rs.getString("message"), rs.getBoolean("repondu")));
            }

        } catch(Exception e) {
            //pass
        }
        return messages.toArray(new Message[0]);        
    }

    @Override
    public boolean save(Message object) {
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO message(name, mail, message) VALUES(?, ?, ?)");
            ps.setString(1, object.getName());
            ps.setString(2, object.getMail());
            ps.setString(3, object.getMessage());
            ps.executeUpdate();

        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Message object) {
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE message SET repondu = ? WHERE id = ?");
            ps.setBoolean(1, object.isRepondu());
            ps.setInt(2, object.getId());
            ps.executeUpdate();

        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Message object) {
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM message WHERE id = ?");
            ps.setInt(1, object.getId());
            ps.executeUpdate();

        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM message");
            ps.executeUpdate();

        } catch(Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int count() {
        int count = 0;
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM message");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch(Exception e) {
            //pass
        }
        return count;
    }

    public int countUnanswered() {
        int count = 0;
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM message WHERE repondu = 0");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }

        } catch(Exception e) {
            //pass
        }
        return count;
    }
    
    public Message[] findAllUnanswered() {
        List<Message> messages = new ArrayList<>();
        try(Connection con = DS.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT id, name, mail, message, repondu FROM message WHERE repondu = 0");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                messages.add(new Message(rs.getInt("id"), rs.getString("name"), rs.getString("mail"), rs.getString("message"), rs.getBoolean("repondu")));
            }

        } catch(Exception e) {
            //pass
        }
        return messages.toArray(new Message[0]);        
    }
    
}
