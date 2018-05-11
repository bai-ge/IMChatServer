package com.baige.commen;

public class State {
    public static final int MSG_STATE_UNREAD = 1000;
    public static final int MSG_STATE_READED = 1001;

    public static final String BEAN_CHATMSG_SENDER_ID = "senderId";
    public static final String BEAN_CHATMSG_RECEIVE_ID = "receiveId";
    public static final String BEAN_CHATMSG_SEND_TIME = "sendTime";
    public static final String BEAN_CHATMSG_RECEIVE_TIME = "receiveTime";
    public static final String BEAN_CHATMSG_CONTEXT = "context";
    public static final String BEAN_CHATMSG_CONTEXT_TYPE = "contextType";
    public static final String BEAN_CHATMSG_CONTEXT_STATE = "contextState";

    public static final int CHATMSG_METHOD_FIND_ALL = 1100;
    public static final int CHATMSG_METHOD_FIND_HISTORY_MSG = 1101;
    public static final int CHATMSG_METHOD_FIND_UNREAD_BY_ID = 1102;
    public static final int CHATMSG_METHOD_FIND_UNREAD_BY_NAME = 1103;
    public static final int CHATMSG_METHOD_FIND_BY_SEND_ID = 1104;
    public static final int CHATMSG_METHOD_FIND_BY_SEND_NAME = 1105;


}
