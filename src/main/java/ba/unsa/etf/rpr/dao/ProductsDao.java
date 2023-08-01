package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Products;

import java.util.List;

/**
 * Dao interface for products bean
 */
public interface ProductsDao extends Dao<Products> {
    /**
     * Returns all products within the given price range
     * @param min
     * @param max
     * @return list of filtered products
     */
    List<Products> getByPriceRange(Double min, Double max);
}
