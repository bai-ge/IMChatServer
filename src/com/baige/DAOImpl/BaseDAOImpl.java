package com.baige.DAOImpl;

import com.baige.DAO.BaseDAO;
import com.baige.exception.SqlException;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


public class BaseDAOImpl<T> implements BaseDAO<T> {

    private Class<T> EntityClass; // 获取实体类

    @SuppressWarnings("unchecked")
    public BaseDAOImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        EntityClass = (Class<T>) type.getActualTypeArguments()[0]; // 获取实体类
    }

    @Override
    public void doSave(T t) throws SqlException {
        Session session = HibernateUtil.getSession();	// 生成session实例
        Transaction tx = session.beginTransaction();	// 创建transaction实例
        try {
            session.save(t);	// 使用session的save方法将持久化对象保存到数据库中
            tx.commit();	// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); 	// 回滚事务
            throw new SqlException(e.getMessage());
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public void doUpdate(T t) throws SqlException {
        Session session = HibernateUtil.getSession();	// 生成session实例
        Transaction tx = session.beginTransaction();	// 创建transaction实例
        try {
            session.update(t);	// 使用session的update方法更新持久化对象
            tx.commit();	// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); 	// 回滚事务
            throw new SqlException(e.getMessage());
        }
        finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public void doDelete(T t) throws SqlException {
        Session session = HibernateUtil.getSession();	// 生成session实例
        Transaction tx = session.beginTransaction();	// 创建transaction实例
        try {
            session.delete(t);	// 使用session的delte方法删除持久化对象
            tx.commit();	// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); 	// 回滚事务
            throw new SqlException(e.getMessage());
        }
        finally {
            HibernateUtil.closeSession();
        }
    }



    @Override
    public T doGetById(int id) throws SqlException {
        T t = null;
		Session session = HibernateUtil.getSession();	// 生成session实例
		Transaction tx = session.beginTransaction();	// 创建transaction实例
		try {
			t = (T) session.get(EntityClass, id);	// 使用session的get方法获取指定id的用户到内存中
			tx.commit();	// 提交事务
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback(); 	// 回滚事务
            throw new SqlException(e.getMessage());
		}
		finally {
			HibernateUtil.closeSession();
		}
		return t;
    }

    @Override
    public T doLoadById(int id) throws SqlException {
        T t = null;
        Session session = HibernateUtil.getSession();	// 生成session实例
        Transaction tx = session.beginTransaction();	// 创建transaction实例
        try {
            t = (T) session.load(EntityClass, id);	// 使用session的load方法获取指定id的用户到内存中
            tx.commit();	// 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); 	// 回滚事务
            throw new SqlException(e.getMessage());
        }
        finally {
            HibernateUtil.closeSession();
        }
        return t;
    }

    @Override
    public List<T> doFindAll() throws SqlException {
        List<T> list = new ArrayList<>();
        System.out.println("开始生成session实例");
        Session session = HibernateUtil.getSession(); // 生成session实例
        System.out.println("生成session实例成功");
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            System.out.println("获取所有记录");
            /**
             * 使用session对象的createCriteria方法创建criteria对象。
             * 使用criteria对象的list方法获取数据集合
             * 使用该对象不需要写hql语句，只需要指定实体类
             */
            Criteria criteria =session.createCriteria(EntityClass);
            list = criteria.list();
            System.out.println("事物提交");
            tx.commit(); // 提交事务
            System.out.println("事物提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            System.out.println("提交失败，回滚");
            throw new SqlException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
            System.out.println("关闭session");
        }
        return list;
    }
}
