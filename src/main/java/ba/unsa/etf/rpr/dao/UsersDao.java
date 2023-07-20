package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

import java.util.List;

public interface UsersDao extends Dao<Users> {

    List<Users> getByName(String name);
    Users getByUsername(String username);

}
