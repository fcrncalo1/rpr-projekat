package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.sql.*;
import java.util.*;

/**
 * Abstract class for CRUD methods required in the DAO layer
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{
    protected static Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try{
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println("Can't connect to the database");
            e.printStackTrace();

        }
    }

    /**
     * A method that gives the connection attribute of the class
     * @return connection attribute
     */
    public Connection getConnection(){
        return this.connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs
     * @return specified table Bean
     * @throws SQLException
     */
    public abstract T row2object(ResultSet rs) throws SQLException;

    /**
     * Method for mapping Object into Map
     * @param object bean
     * @return key,value map of Object
     */
    public abstract Map<String, Object> object2row(T object);

    /**
     * Method that returns a row with the given id
     * @param id
     * @return object row from the database
     * @throws SQLException
     */
    public T getById(int id) throws SQLException {
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new SQLException("Object not found");
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Method that returns all records from a certain table
     * @return list of objects
     * @throws SQLException
     */
    public List<T> getAll() throws SQLException {
        String query = "SELECT * FROM "+ tableName;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Method that deletes a record from the table with the given id
     * @param id
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Method that adds a record to the database table
     * @param item
     * @return object item
     * @throws SQLException
     */
    public T add(T item) throws SQLException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Method that updates a record from a table in the database
     * @param item
     * @return object item
     * @throws SQLException
     */
    public T update(T item) throws SQLException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new SQLException(e.getMessage(), e);
        }
    }

    /**
     * Helper method for creating INSERT statements
     * @param row map of attribute names and values
     * @return key,value entry eg. (id, firstName, lastName, ...) ?,?,?, ...
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    /**
     * Method for creating a query for UPDATE
     * @param row
     * @return String in id=?,column1=?... format
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}
