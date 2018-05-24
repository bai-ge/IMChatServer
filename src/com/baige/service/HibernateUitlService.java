package com.baige.service;

import com.baige.DAO.UserDAO;
import com.baige.DAOImpl.ChatMessageDAOImpl;
import com.baige.DAOImpl.FileDAOImpl;
import com.baige.DAOImpl.FriendDAOImpl;
import com.baige.DAOImpl.UserDAOImpl;
import com.baige.commen.Parm;
import com.baige.connect.msg.PushHelper;
import com.baige.exception.SqlException;
import com.baige.models.*;
import com.baige.util.JsonTools;
import com.baige.util.Tools;
import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HibernateUitlService {

    /**
     * @param name
     * @return isClash
     */
    public static boolean checkNameClash(String name) {
        UserDAOImpl userDAO = new UserDAOImpl();
        List<User> list = null;
        try {
            list = userDAO.getIdByName(name);
            if (list != null && !list.isEmpty()) {
                return true;
            }
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void login(User user, Map<String, Object> responseMsgMap) {
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            User updateUser = userDAO.searchUserByNameAndPassword(user.getName(), user.getPassword());
            if (updateUser != null) {
                //通知被迫下线的设备
                if (!Tools.isEquals(updateUser.getDeviceId(), user.getDeviceId()) &&
                        !Tools.isEmpty(updateUser.getVerification())) {
                    //被迫下线
                    denyOfService(updateUser.getDeviceId(), updateUser.getVerification());
                }
                updateUser.setLoginTime(user.getLoginTime());
                updateUser.setDeviceId(user.getDeviceId());
                updateUser.setVerification(Tools.randomVerification());
                updateUser.setLoginIp(user.getLoginIp());
                userDAO.doUpdate(updateUser);
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "登录成功");
                responseMsgMap.put(Parm.USER, updateUser);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "用户不存在或密码错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }


    public static void register(User user, Map<String, Object> responseMsgMap) {
        if (!checkNameClash(user.getName())) {
            // 插入数据
            UserDAOImpl userDAO = new UserDAOImpl();
            try {
                userDAO.doSave(user);
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "注册成功");
                responseMsgMap.put(Parm.USER, user);
            } catch (SqlException e) {
                e.printStackTrace();
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, e.getMessage());
            }
        } else {
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, "用户已经存在");
        }
    }

    public static User checkUser(int id, String verification) {
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            User user = userDAO.searchUserByIdAndVerification(id, verification);
            return user;
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void updateAlias(User user, Map<String, Object> responseMsgMap) {
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            user = userDAO.updateAliasByIdAndVer(user.getId(), user.getVerification(), user.getAlias());
            if (user != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "更新成功");
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "更新失败");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, "更新失败");
        }

    }

    public static boolean updateHeadImgName(int id, String headImg) {
        UserDAOImpl userDAO = new UserDAOImpl();
        boolean res = false;
        try {
            res = userDAO.updateHeadImgById(id, headImg);
        } catch (SqlException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void searchUserBykeyword(String keyword, Map<String, Object> responseMsgMap) {
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            List<User> list = userDAO.searchUserBykeyword(keyword);
            if (list != null && list.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.USERS, list);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.USERS, new ArrayList<>());
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    /**
     * 取消设备deviceId 的验证码 verification, 即被迫下线
     *
     * @param deviceId
     * @param verification
     */
    public static void denyOfService(String deviceId, String verification) {
        //TODO
    }


    public static void searchFriends(int uid, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            List<FriendView> friendViews = friendDAO.searchFriend(uid);
            responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
            responseMsgMap.put(Parm.MEAN, "查询好友成功");
            responseMsgMap.put(Parm.FRIENDS, friendViews);
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void changFriendAlias(int id, int uid, String alias, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            boolean update = friendDAO.changFriendAlias(id, uid, alias);
            if (update) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "更改好友备注成功");
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "更改好友备注失败");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void relateUser(int uid, int friendId, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            Friend friend = friendDAO.relateUser(uid, friendId);
            if (friend != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "等待对方同意");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void agreeFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            Friend friend = friendDAO.agreeFriend(id, uid, friendId);
            if (friend != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "添加好友成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void rejectFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            Friend friend = friendDAO.rejectFriend(id, uid, friendId);
            if (friend != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void deleteFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            Friend friend = friendDAO.deleteFriend(id, uid, friendId);
            if (friend != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void deFriend(int id, int uid, int friendId, Map<String, Object> responseMsgMap) {
        FriendDAOImpl friendDAO = new FriendDAOImpl();
        try {
            Friend friend = friendDAO.deFriend(id, uid, friendId);
            if (friend != null) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "操作成功");
                responseMsgMap.put(Parm.DATA, friend);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
                responseMsgMap.put(Parm.MEAN, "未知错误");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void sendMessage(ChatMessage chatMessage, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        try {
            chatMessageDAO.doSave(chatMessage);
            //TODO 推送
            User from = userDAO.doGetById(chatMessage.getSenderId());
            User to = userDAO.doGetById(chatMessage.getReceiveId());
            PushHelper.push(from.getDeviceId(), to.getDeviceId(), JsonTools.getJSON(chatMessage), Parm.CHAT);

            responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
            responseMsgMap.put(Parm.MEAN, "发送成功");
            responseMsgMap.put(Parm.CHAT, chatMessage);
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsgRelate(int uid, int friendId, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsgRelate(uid, friendId);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsgRelateAfterTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsgRelateAfterTime(uid, friendId, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsgRelateBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsgRelateBeforeTime(uid, friendId, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsg(int uid, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsg(uid);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsgAfterTime(int uid, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsgAfterTime(uid, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void findMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> chatMessages = chatMessageDAO.findMsgBeforeTime(uid, time);
            //TODO 推送
            if (chatMessages != null && chatMessages.size() > 0) {
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
                responseMsgMap.put(Parm.CHAT_LIST, chatMessages);
            } else {
                responseMsgMap.put(Parm.CODE, Parm.BLANK_CODE);
                responseMsgMap.put(Parm.MEAN, "查找成功");
            }
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void readMsgBeforeTime(int uid, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            boolean success = chatMessageDAO.readBeforeTime(uid, time);
            responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
            responseMsgMap.put(Parm.MEAN, "更新成功");
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }
    public static void readMsgBeforeTime(int uid, int friendId, long time, Map<String, Object> responseMsgMap) {
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            boolean success = chatMessageDAO.readBeforeTime(uid, friendId, time);
            responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
            responseMsgMap.put(Parm.MEAN, "更新成功");
        } catch (SqlException e) {
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }


    public static void saveFile(FileEntity fileEntity, Map<String, Object> responseMsgMap){
        FileDAOImpl fileDAO = new FileDAOImpl();
        try{
            fileDAO.doSave(fileEntity);
            responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
            responseMsgMap.put(Parm.MEAN, "文件上传成功");
        }catch (SqlException e){
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

    public static void searchAllFile(Map<String, Object> responseMsgMap){
        FileDAOImpl fileDAO = new FileDAOImpl();
        try{
            List<FileEntity> fileEntities = fileDAO.doFindAll();
            if(fileEntities != null && fileEntities.size() > 0){
                responseMsgMap.put(Parm.CODE, Parm.SUCCESS_CODE);
                responseMsgMap.put(Parm.MEAN, "查询成功");
                responseMsgMap.put(Parm.FILES, fileEntities);
            }else{
                responseMsgMap.put(Parm.CODE, Parm.NOTFIND_CODE);
                responseMsgMap.put(Parm.MEAN, "未找到");
            }
        }catch (SqlException e){
            e.printStackTrace();
            responseMsgMap.put(Parm.CODE, Parm.FAIL_CODE);
            responseMsgMap.put(Parm.MEAN, e.getMessage());
        }
    }

}
