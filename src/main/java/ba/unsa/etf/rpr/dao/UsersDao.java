package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Users;

import java.util.List;

/**
 * Dao interface for users bean
 */
public interface UsersDao extends Dao<Users> {

    /**
     * Lists all users with given name
     * @param name
     * @return list of users
     */
    List<Users> getByName(String name);

    /**
     * Returns a user with the given username
     * @param username
     * @return users object
     */
    Users getByUsername(String username);

}
