package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.ProductsException;
import ba.unsa.etf.rpr.exceptions.UsersException;

import java.sql.SQLException;
import java.util.List;

public class ProductsManager {
    /**
     * Deletes a product with the given id
     * @param id
     * @throws ProductsException
     */
    public void delete(int id) throws ProductsException {
        try {
            DaoFactory.productsDao().delete(id);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    /**
     * Adds a product to the database
     * @param p
     * @return products object
     * @throws ProductsException
     */
    public Products add(Products p) throws ProductsException {
        try {
            DaoFactory.productsDao().add(p);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
        return p;
    }

    /**
     * Updates a product from the database
     * @param p
     * @return products object
     * @throws ProductsException
     */
    public Products update(Products p) throws ProductsException{
        try {
            return DaoFactory.productsDao().update(p);
        } catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    /**
     * Returns all products from the database
     * @return list of products
     * @throws ProductsException
     */
    public List<Products> getAll() throws ProductsException {
        try {
            return DaoFactory.productsDao().getAll();
        }catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }

    /**
     * Returns a product with the given id
     * @param uId
     * @return products object
     * @throws ProductsException
     */
    public Products getById(int uId) throws ProductsException {
        try {
            return DaoFactory.productsDao().getById(uId);
        }catch (SQLException e) {
            throw new ProductsException(e.getMessage());
        }
    }
}
