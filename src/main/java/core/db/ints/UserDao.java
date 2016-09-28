package core.db.ints;

import core.db.entity.User;
import java.util.List;

/**
 * The UserDao is a data access object interface. Interface contains methods for
 * implementation(adding, deleting, getting, updating).
 *
 * @author Rastislav Vocko
 * @version 1.0
 * @since 2016-04-25
 */
public interface UserDao {

    /**
     * Method for saving a new user.
     */
    void addUser(User user);

    /**
     * Method for deleting a new user.
     */
    void deleteUser(User user);

    /**
     *Method which returns list of all users.
     */
    List<User> getAll();

    /**
     * Method for searching by id. Method returns user.
     */
    User getById(Long id);

    /**
     * Method for updating user.
     */
    void updateUser(User user);

    /**
     * Method is used for verifing userName and password at logging. Method
     * return verifed user.
     */
    User getVerifedUser(String name, String password);

    /**
     * Method for searching all users with role "student". Method returns list
     * off users.
     */
    List<User> getAllStudents();

    /**
     * Method for searching by name. Method returns user.
     */
    User getByName(List<User> users, String name);

    /**
     * Method which checking uniqueness of userName. Method return Boolean.
     */
    boolean checkUserName(String userName);

}
