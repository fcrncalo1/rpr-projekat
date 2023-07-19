package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Orders;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Users;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ProductsDaoSQLImpl extends AbstractDao<Products> implements ProductsDao {

    private Connection connection;

    public ProductsDaoSQLImpl() {
        super("products");
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

    @Override
    public Products row2object(ResultSet rs) throws SQLException {
        try {
            Products products = new Products();
            products.setId(rs.getInt("id"));
            products.setName(rs.getString("name"));
            products.setPrice(rs.getDouble("price"));
            products.setQuantity(rs.getInt("quantity"));
            return products;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Products object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("price", object.getPrice());
        row.put("quantity", object.getQuantity());
        return row;
    }
}
