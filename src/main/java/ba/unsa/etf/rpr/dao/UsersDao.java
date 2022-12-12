package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface UsersDao extends Dao {

    List getByName(String name);

}
