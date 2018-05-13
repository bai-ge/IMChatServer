package com.baige.DAO;


import com.baige.exception.SqlException;
import com.baige.models.User;

import java.util.List;

public interface UserDAO {

    String TAB_NAME = "users";
    String ID = "id";
    String NAME = "name";
    String PASSWORD = "password";
    String ALIAS = "alias";
    String DEVICE_ID = "deviceId";
    String LOGIN_TIME = "loginTime";
    String LOGIN_IP = "loginIp";
    String REGISTER_TIME = "registerTime";
    String VERIFICATION = "verification";
    String IMG_NAME = "imgName";
    String REMARK = "remark";

    List<User> getIdByName(String name) throws SqlException;
    List<User> searchUserByName(String name) throws SqlException;
    User updateAliasByIdAndVer(int id, String verification, String alias) throws SqlException;
    User searchUserByNameAndPassword(String name, String password) throws SqlException;
    User searchUserByIdAndVerification(int id, String verification) throws SqlException;
    boolean updateHeadImgById(int id, String headImgName) throws SqlException;
    List<User> searchUserBykeyword(String keyword) throws SqlException;
}
