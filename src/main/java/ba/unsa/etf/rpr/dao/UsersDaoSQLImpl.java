package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Users;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class UsersDaoSQLImpl extends AbstractDao<Users> implements UsersDao {

    private Connection connection;

    public UsersDaoSQLImpl() {
        super("users");
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

    @Override
    public Users row2object(ResultSet rs) throws SQLException {
        try {
            Users users = new Users();
            users.setId(rs.getInt("id"));
            users.setFirstName(rs.getString("first_name"));
            users.setLastName(rs.getString("last_name"));
            users.setEmail(rs.getString("email"));
            users.setCity(rs.getString("city"));
            users.setAddress(rs.getString("address"));
            users.setMobileNumber(rs.getString("mobile_number"));
            users.setUsername(rs.getString("username"));
            users.setPassword(rs.getString("password"));
            return users;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Users object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("first_name", object.getFirstName());
        row.put("last_name", object.getLastName());
        row.put("email", object.getEmail());
        row.put("city", object.getCity());
        row.put("address", object.getAddress());
        row.put("mobile_number", object.getMobileNumber());
        row.put("username", object.getUsername());
        row.put("password", object.getPassword());
        return row;
    }
}
