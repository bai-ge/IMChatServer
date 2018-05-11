package com.baige.DAOImpl;

import com.baige.DAO.UserDAO;
import com.baige.exception.SqlException;
import com.baige.models.User;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

    @Override
    public List<User> getIdByName(String name) throws SqlException {
        List<User> list = new ArrayList<User>();

        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("name",name));
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return list;
    }

    @Override
    public List<User> searchUserByName(String name) throws SqlException{
        List<User> list = new ArrayList<User>();

        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(User.class).add(Restrictions.eq(UserDAO.NAME,name));
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return list;
    }

    @Override
    public User searchUserByNameAndPassword(String name, String password) throws SqlException{
        User user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(User.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.NAME, name),
                            Restrictions.eq(UserDAO.PASSWORD, password)));
            user = (User) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }

    public User updateAliasByIdAndVer(int id, String verification, String alias) throws SqlException{
        User user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Query q = session.createQuery("update User u set u.alias  =:alias  where u.id = :id and u.verification = :verification");
            q.setString("alias", alias);
            q.setInteger("id", id);
            q.setString("verification", verification);
            q.executeUpdate();
            Criteria criteria =session.createCriteria(User.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.ID, id),
                            Restrictions.eq(UserDAO.VERIFICATION, verification)));
            user = (User) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }

    @Override
    public User searchUserByIdAndVerification(int id, String verification) throws SqlException {
        User user = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria =session.createCriteria(User.class).add(
                    Restrictions.and(Restrictions.eq(UserDAO.ID, id),
                            Restrictions.eq(UserDAO.VERIFICATION, verification)));
            user = (User) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }

    @Override
    public boolean updateHeadImgById(int id, String headImgName) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        try {
            Query q = session.createQuery("update User u set u.imgName  =:img  where u.id = :id");
            q.setString("img", headImgName);
            q.setInteger("id", id);
            count = q.executeUpdate();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return count == 1;
    }
}
