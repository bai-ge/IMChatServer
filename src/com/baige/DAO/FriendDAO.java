package com.baige.DAO;

import com.baige.exception.SqlException;
import com.baige.models.Friend;
import com.baige.models.FriendView;
import com.baige.models.User;

import java.util.List;

public interface FriendDAO {

    String ID = "id";
    String USER_ID = "userId";
    String FRIEND_ID = "friendId";
    String USER_ALIAS = "userAlias";
    String RELATE_TIME = "relateTime";
    String STATE = "state";
    String USER_READ_STATE = "userReadState";
    String FRIEND_READ_STATE = "friendReadState";
    String REMARK = "remark";

    Friend searchFriend(int userId, int friendId) throws SqlException; // 这俩id位置无关

    List<FriendView> searchFriend(int uid) throws SqlException;

    boolean changFriendAlias(int id, int uid, String alias) throws SqlException;

    Friend relateUser(int userId, int friendId) throws SqlException;

    Friend agreeFriend(int id, int userId, int friendId) throws  SqlException;

    Friend rejectFriend(int id, int userId, int friendId) throws SqlException;

    Friend deFriend(int id, int uerId, int friendId)throws SqlException;

    Friend deledtFriend(int id, int userId, int friendId)throws SqlException;

    List<Friend> getUnRead(int userId)throws SqlException;
}
