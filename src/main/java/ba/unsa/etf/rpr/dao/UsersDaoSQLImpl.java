package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UsersDaoSQLImpl implements UsersDao {

    private Connection connection;

    public UsersDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582897","sql7582897","z2bc7SG6Nh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Users getById(int id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setMobileNumber(rs.getString("mob_num"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                rs.close();
                return user;
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users add(Users item) {
        String insert = "INSERT INTO users(first_name,last_name,email,city,address,mob_num,username,password) " +
                        "VALUES(?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getFirstName());
            stmt.setString(2,item.getLastName());
            stmt.setString(3,item.getEmail());
            stmt.setString(4,item.getCity());
            stmt.setString(5,item.getAddress());
            stmt.setString(6,item.getMobileNumber());
            stmt.setString(7,item.getUsername());
            stmt.setString(8,item.getPassword());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Users update(Users item) {
        String updt = "UPDATE users SET first_name = ?, last_name = ?, email = ?, city = ?, address = ?" +
                        ", mob_num = ?, username = ?, password = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(updt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,item.getFirstName());
            stmt.setObject(2,item.getLastName());
            stmt.setObject(3,item.getEmail());
            stmt.setObject(4,item.getCity());
            stmt.setObject(5,item.getAddress());
            stmt.setObject(6,item.getMobileNumber());
            stmt.setObject(7,item.getUsername());
            stmt.setObject(8,item.getPassword());
            stmt.setObject(9,item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String dlt = "DELETE FROM users WHERE user_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(dlt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> getAll() {
        String query = "SELECT * FROM users";
        List<Users> users = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setMobileNumber(rs.getString("mob_num"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Users> getByName(String name) {
        String query = "SELECT * FROM users WHERE first_name = ?";
        List<Users> users = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("user_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setMobileNumber(rs.getString("mob_num"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
