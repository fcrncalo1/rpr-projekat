package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Users;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ProductsDaoSQLImpl implements ProductsDao {

    private Connection connection;

    public ProductsDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7582897","sql7582897","z2bc7SG6Nh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Products getById(int id) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                rs.close();
                return product;
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Products add(Products item) {
        String insert = "INSERT INTO products(name,price,quantity) VALUES(?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,item.getName());
            stmt.setDouble(2,item.getPrice());
            stmt.setInt(3,item.getQuantity());
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
    public Products update(Products item) {
        String updt = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE product_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(updt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,item.getName());
            stmt.setObject(2,item.getPrice());
            stmt.setObject(3,item.getQuantity());
            stmt.setObject(4,item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String dlt = "DELETE FROM products WHERE product_id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(dlt,Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Products> getAll() {
        String query = "SELECT * FROM products";
        List<Products> products = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Products> getByBrandName(String name) {
        return null;
    }

    @Override
    public List<Products> getByPriceRange(Double min, Double max) {
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        List<Products> products = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setDouble(1,min);
            stmt.setDouble(2,max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
