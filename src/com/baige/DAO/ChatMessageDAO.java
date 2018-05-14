package com.baige.DAO;

import com.baige.exception.SqlException;
import com.baige.models.ChatMessage;

import java.util.List;

public interface ChatMessageDAO {

    String ID = "id";
    String SENDER_ID = "senderId";
    String RECEIVE_ID = "receiveId";
    String SEND_TIME = "sendTime";
    String CONTEXT = "context";
    String CONTEXT_TYPE = "contextType";
    String CONTEXT_STATE = "contextState";
    String REMARK = "remark";

    List<ChatMessage> findMsgRelate(int uid, int friendId) throws SqlException;

    List<ChatMessage> findMsgRelateAfterTime(int uid, int friendId, long time) throws SqlException;

    List<ChatMessage> findMsgRelateBeforeTime(int uid, int friendId, long time) throws SqlException;

}
