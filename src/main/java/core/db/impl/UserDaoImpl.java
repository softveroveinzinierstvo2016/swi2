package core.db.impl;

import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import core.db.HibernateUtil;
import core.db.entity.User;
import core.db.ints.UserDao;
import core.utils.HashingUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import org.hibernate.Session;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements UserDao {

    private static Logger logger = Logger.getLogger("UserDaoImpl");
    private static final String CSV_SEPARATOR = ";";

    @Override
    public void addUser(User user) {
        logger.info("User adding");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(user);
        session.close();

    }

    @Override
    public void deleteUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<User> getAll() {
        logger.info("All users returning");
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }

    @Override
    public User getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateUser(User user) {
        Session session = null;
        Transaction tx = null;
        logger.info("User updating");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            session.update(user);

            tx.commit();

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {

            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getVerifedUser(String userName, String password) {
        logger.info("Verifed user returning");
        HashingUtils hashingUtils = new HashingUtils();

        try {
            password = hashingUtils.hash(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        criteria.add(Restrictions.eq("password", password));
        List<User> users = criteria.list();
        if (users.size() > 1) {
            return null;
        }
        if (users.size() == 0) {
            return null;
        } else {
            User user = users.get(0);

            session.close();
            return user;
        }
    }

    @Override
    public List<User> getAllStudents() {
        logger.info("All students returning");
        List<User> users = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("role", "student"));
        if (criteria.list().size() > 0) {
            users = criteria.list();
        }
        session.close();
        return users;
    }

    @Override
    public User getByName(List<User> users, String name) {
        logger.info("User by name returning");
        for (User userF : users) {
            if (userF.getName().equals(name)) {
                return userF;
            }
        }
        return null;
    }

    @Override
    public boolean checkUserName(String userName) {
        logger.info("Username checking");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("userName", userName));
        List<User> users = criteria.list();
        if (users.size() == 0) {
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    
}
