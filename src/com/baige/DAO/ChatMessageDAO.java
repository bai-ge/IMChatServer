package com.baige.DAO;

import com.baige.exception.SqlException;
import com.baige.models.ChatMessage;

import java.util.List;

public interface ChatMessageDAO {
    List<ChatMessage> findUnreadMsgByReceiveId(int id) throws SqlException;

    List<ChatMessage> findUnreadMsgByReceiveName(String name) throws SqlException;

    List<ChatMessage> findMsgBySendId(int id) throws SqlException;

    List<ChatMessage> findMsgBySendName(String name) throws SqlException;

    List<ChatMessage> findHistoryMsg(int id) throws SqlException; // 返回最近100条聊天记录
}
