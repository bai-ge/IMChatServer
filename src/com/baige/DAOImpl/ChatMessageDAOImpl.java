package com.baige.DAOImpl;

import com.baige.commen.State;
import com.baige.DAO.ChatMessageDAO;
import com.baige.exception.SqlException;
import com.baige.models.ChatMessage;
import com.baige.models.User;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageDAOImpl extends BaseDAOImpl<ChatMessage> implements ChatMessageDAO {



    @Override
    public List<ChatMessage> findUnreadMsgByReceiveId(int id) throws SqlException {
        List<ChatMessage> list = new ArrayList<>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.eq(State.BEAN_CHATMSG_RECEIVE_ID,id));
            criteria.add(Restrictions.eq(State.BEAN_CHATMSG_CONTEXT_STATE,State.MSG_STATE_UNREAD));
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
    public List<ChatMessage> findUnreadMsgByReceiveName(String name) throws SqlException {
        List<ChatMessage> list = new ArrayList<>();
        List<User> userList;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例

        UserDAOImpl userDAO = new UserDAOImpl();
        userList = userDAO.getIdByName(name);
        if (userList != null && !userList.isEmpty()){
            list = findUnreadMsgByReceiveId(userList.get(0).getId());
        }
        return list;
    }

    @Override
    public List<ChatMessage> findMsgBySendId(int id) throws SqlException {
        List<ChatMessage> list = new ArrayList<>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.eq(State.BEAN_CHATMSG_SENDER_ID,id));
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
    public List<ChatMessage> findMsgBySendName(String name) throws SqlException {
        List<ChatMessage> list = new ArrayList<>();
        List<User> userList;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例

        UserDAOImpl userDAO = new UserDAOImpl();
        userList = userDAO.getIdByName(name);
        if (userList != null && !userList.isEmpty()){
            list = findMsgBySendId(userList.get(0).getId());
        }
        return list;
    }

    @Override
    public List<ChatMessage> findHistoryMsg(int id) throws SqlException {
        List<ChatMessage> list = new ArrayList<>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            // 对应的“name” 是Bean中的字段名而不是数据库中表内的字段名
            Criteria criteria = session.createCriteria(ChatMessage.class);
            criteria.add(Restrictions.or(Restrictions.eq(State.BEAN_CHATMSG_SENDER_ID,id),Restrictions.eq(State.BEAN_CHATMSG_RECEIVE_ID,id)));
            criteria.addOrder(Order.desc("")).setMaxResults(100);
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
