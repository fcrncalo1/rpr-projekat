package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface ProductsDao extends Dao {

    List getByBrandName(String name);

    List getByPriceRange(Double min, Double max);
}
