package com.baige.DAOImpl;

import com.baige.DAO.FriendDAO;
import com.baige.commen.State;
import com.baige.exception.SqlException;
import com.baige.models.Friend;
import com.baige.models.FriendView;
import com.baige.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class FriendDAOImpl extends BaseDAOImpl<Friend> implements FriendDAO {

    @Override
    public Friend searchFriend(int userId, int friendId) throws SqlException {
        Friend friend = null;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Criteria criteria = session.createCriteria(Friend.class).add(Restrictions.or(
                    Restrictions.and(Restrictions.eq(FriendDAO.USER_ID, userId), Restrictions.eq(FriendDAO.FRIEND_ID, friendId)),
                    Restrictions.and(Restrictions.eq(FriendDAO.USER_ID, friendId), Restrictions.eq(FriendDAO.FRIEND_ID, userId))));
            friend = (Friend) criteria.uniqueResult();
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friend;
    }
    private FriendView initFriendView(Object[] objects){
        if(objects == null || objects.length == 0){
            return null;
        }
        //u.id, u.alias, f.userAlias, f.relateTime, f.state, f.userReadState, f.remark
        FriendView friendView = new FriendView();
        if(objects.length >= 11){
            friendView.setFriendId((Integer) objects[0]);
            friendView.setFriendName((String) objects[1]);
            friendView.setAlias((String) objects[2]);
            friendView.setFriendAlias((String) objects[3]);
            if(objects[4] != null){
                friendView.setRelateTime((Long) objects[4]);
            }
            if(objects[5] != null) {
                friendView.setState((Integer) objects[5]);
            }
            if(objects[6] != null) {
                friendView.setReadState((Integer) objects[6]);
            }
            friendView.setRemake((String) objects[7]);
            friendView.setFriendImgName((String) objects[8]);
            friendView.setFriendDeviceId((String) objects[9]);
            if(objects[10] != null){
                friendView.setId((Integer) objects[10]);
            }
        }
        return friendView;
    }

    @Override
    public List<FriendView> searchFriend(int uid) throws SqlException {
        List<FriendView> friendViews = new ArrayList<>();
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            //查询左边用户的朋友 即 friend.userId 的朋友
            Query queryLeft = session.createQuery("select u.id, u.name, u.alias, f.userAlias, f.relateTime, f.state, f.userReadState, f.remark, u.imgName, u.deviceId, f.id from User u, Friend f where f.friendId = u.id and f.userId = :uid and u.id <> :uuid");
            queryLeft.setInteger("uid", uid);
            queryLeft.setInteger("uuid", uid);
            List listLeft = queryLeft.list();

            if(listLeft != null && listLeft.size() > 0){
                for (int i = 0; i < listLeft.size(); i++){
                   FriendView friendView = initFriendView((Object[]) listLeft.get(i));
                   if(friendView != null){
                       friendView.setUid(uid);
                       friendViews.add(friendView);
                   }
                }
            }
            //查询右边用户的朋友 即 friend.friendId 的朋友
            Query queryRight = session.createQuery("select u.id, u.name, u.alias, f.friendAlias, f.relateTime, f.state, f.friendReadState, f.remark, u.imgName, u.deviceId, f.id from User u, Friend f where f.userId = u.id and f.friendId = :uid and u.id <> :uuid");
            queryRight.setInteger("uid", uid);
            queryRight.setInteger("uuid", uid);
            List listRight = queryRight.list();
            if(listRight != null && listRight.size() > 0){
                for (int i = 0; i < listRight.size(); i++){
                    FriendView friendView = initFriendView((Object[]) listRight.get(i));
                    if(friendView != null){
                        friendView.setUid(uid);
                        friendViews.add(friendView);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friendViews;
    }

    @Override
    public boolean changFriendAlias(int id, int uid, String alias) throws SqlException {
        int count = 0;
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        try {
            Query ql = session.createQuery("update Friend f set f.userAlias  =:alias  where f.id = :id and f.userId = :uid");
            ql.setString("alias", alias);
            ql.setInteger("id", id);
            ql.setInteger("uid", uid);
            count = ql.executeUpdate();
            if(count == 0){
                Query qr = session.createQuery("update Friend f set f.friendAlias  =:alias  where f.id = :id and f.friendId = :uid");
                qr.setString("alias", alias);
                qr.setInteger("id", id);
                qr.setInteger("uid", uid);
                count = qr.executeUpdate();
            }
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

    @Override
    public Friend relateUser(int userId, int friendId) throws SqlException {
        Friend friend = searchFriend(userId, friendId);
        if (friend == null) {
            friend = new Friend();
            friend.setUserId(userId);
            friend.setFriendId(friendId);
            friend.setRelateTime(System.currentTimeMillis());
            friend.setFriendReadState(State.UNREAD_STATE);
            friend.setState(userId * 10 + State.RELATETION_ADD);
            doSave(friend);
            System.out.println("添加"+friend.toString());
            return friend;
        } else {
            int state = friend.getState() == null ? 0 : friend.getState();
            if (state != State.RELATETION_ADD && state != State.RELATETION_AGREE) {
                friend.setUserAlias("");
                friend.setFriendAlias("");
                friend.setUserId(userId);
                friend.setFriendId(friendId);
                friend.setFriendReadState(State.UNREAD_STATE);
                friend.setState(userId * 10 +State.RELATETION_ADD);
                doUpdate(friend);
                System.out.println("更新"+friend.toString());
                return friend;
            }
        }
        return null;
    }

    @Override
    public Friend agreeFriend(int id, int userId, int friendId) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        Friend friend = null;
        try {
            Query q = session.createQuery("update Friend f set f.state =:state, f.userReadState = :user_read where f.id = :id and f.state = :old_state");
            q.setInteger("state", userId * 10 + State.RELATETION_AGREE);
            q.setInteger("user_read", State.UNREAD_STATE);
            q.setInteger("id", id);
            q.setInteger("old_state", friendId * 10 + State.RELATETION_ADD);
            count = q.executeUpdate();
            if (count == 1) {
                friend = doGetById(id);
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friend;
    }

    @Override
    public Friend rejectFriend(int id, int userId, int friendId) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        Friend friend = null;
        try {
            Query q = session.createQuery("update Friend f set f.state =:state, f.userReadState = :user_read where f.id = :id and f.state = :old_state");
            q.setInteger("state", userId * 10 + State.RELATETION_REJECT);
            q.setInteger("user_read", State.UNREAD_STATE);
            q.setInteger("id", id);
            q.setInteger("old_state", friendId * 10 + State.RELATETION_ADD);
            count = q.executeUpdate();
            if (count == 1) {
                friend = doGetById(id);
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friend;
    }

    @Override
    public Friend deFriend(int id, int userId, int friendId) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        Friend friend = null;
        try {
            Query q = session.createQuery("update Friend f set f.state =:state where f.id = :id and (f.userId = :uid or f.friendId = :uid)");
            q.setInteger("state", userId * 10 + State.RELATETION_DEFRIEND);
            q.setInteger("id", id);
            q.setInteger("uid", userId);
            count = q.executeUpdate();
            if (count == 1) {
                friend = doGetById(id);
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friend;
    }

    @Override
    public Friend deleteFriend(int id, int userId, int friendId) throws SqlException {
        Session session = HibernateUtil.getSession(); // 生成session实例
        Transaction tx = session.beginTransaction(); // 创建transaction实例
        int count = 0;
        Friend friend = null;
        try {
            Query q = session.createQuery("update Friend f set f.state =:state where f.id = :id and (f.userId = :uid or f.friendId = :uid)");
            q.setInteger("state", userId * 10 + State.RELATETION_DELETE);
            q.setInteger("id", id);
            q.setInteger("uid", userId);
            count = q.executeUpdate();
            if (count == 1) {
                friend = doGetById(id);
            }
            tx.commit(); // 提交事务;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 回滚事务
            throw new SqlException("sql execute fail !");
        } finally {
            HibernateUtil.closeSession();
        }
        return friend;
    }

    @Override
    public List<Friend> getUnRead(int userId) throws SqlException {
        return null;
    }
}
