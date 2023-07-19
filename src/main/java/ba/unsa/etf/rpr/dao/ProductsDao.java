package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Products;

import java.util.List;

public interface ProductsDao extends Dao<Products> {
    List<Products> getByPriceRange(Double min, Double max);
}
