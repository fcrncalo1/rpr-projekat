package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Users;
import ba.unsa.etf.rpr.exceptions.UsersException;

import java.sql.SQLException;
import java.util.List;

public class UsersManager {
    public void validate(Users u) throws UsersException {
        if (u.getFirstName() == null || u.getFirstName().trim().length() == 0 || u.getLastName().trim().length() == 0) {
            throw new UsersException("Name must be at least one character!");
        }
    }

    public void delete(int id) throws UsersException {
        try {
            DaoFactory.usersDao().delete(id);
        } catch (SQLException e) {
            throw new UsersException(e.getMessage());
        }
    }

    public Users add(Users u) throws UsersException {
        try {
            validate(u);
            DaoFactory.usersDao().add(u);
        } catch (SQLException e) {
            throw new UsersException(e.getMessage());
        }
        return u;
    }

    public Users update(Users u) throws UsersException{
        try {
            return DaoFactory.usersDao().update(u);
        } catch (SQLException e) {
            throw new UsersException(e.getMessage());
        }
    }

    public List<Users> getAll() throws UsersException {
        try {
            return DaoFactory.usersDao().getAll();
        }catch (SQLException e) {
            throw new UsersException(e.getMessage());
        }
    }

    public Users getById(int uId) throws UsersException {
        try {
            return DaoFactory.usersDao().getById(uId);
        }catch (SQLException e) {
            throw new UsersException(e.getMessage());
        }
    }
}
