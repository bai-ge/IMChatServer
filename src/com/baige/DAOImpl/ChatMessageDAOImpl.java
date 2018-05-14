package com.baige.DAOImpl;

import com.baige.DAO.ChatMessageDAO;
import com.baige.exception.SqlException;
import com.baige.models.ChatMessage;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class ChatMessageDAOImpl extends BaseDAOImpl<ChatMessage> implements ChatMessageDAO {


    @Override
    public List<ChatMessage> findMsgRelate(int uid, int friendId) throws SqlException {
        List<ChatMessage> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.or (
                    Restrictions.and( Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))) );
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
        return list;
    }

    @Override
    public List<ChatMessage> findMsgRelateAfterTime(int uid, int friendId, long time) throws SqlException {
        List<ChatMessage> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.or (
                    Restrictions.and( Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))) );
            criteria.add(Restrictions.lt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
        return list;
    }

    @Override
    public List<ChatMessage> findMsgRelateBeforeTime(int uid, int friendId, long time) throws SqlException {
        List<ChatMessage> list = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.or (
                    Restrictions.and( Restrictions.eq(ChatMessageDAO.SENDER_ID, uid), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, friendId)),
                    Restrictions.and(Restrictions.eq(ChatMessageDAO.SENDER_ID, friendId), Restrictions.eq(ChatMessageDAO.RECEIVE_ID, uid))) );
            criteria.add(Restrictions.gt(ChatMessageDAO.SEND_TIME, time));
            criteria.addOrder(Order.desc(ChatMessageDAO.SEND_TIME)).setMaxResults(100);
            list = criteria.list();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException(e.getMessage());
        } finally {
            HibernateUtil.closeSession();
        }
        return list;
    }
}
