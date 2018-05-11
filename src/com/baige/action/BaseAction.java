package com.baige.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseAction extends ActionSupport {
    private Map<String ,Object> responseMsgMap;

    public Map<String, Object> getResponseMsgMap() {
        if(responseMsgMap == null){
            responseMsgMap = new LinkedHashMap<>();
        }
        return responseMsgMap;
    }

    public void setResponseMsgMap(Map<String, Object> responseMsgMap) {
        this.responseMsgMap = responseMsgMap;
    }
}
