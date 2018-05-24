package com.baige.connect.msg;

import com.baige.connect.SocketPacket;
import com.baige.util.Tools;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class PushHelper {

    public final static String localhost = "127.0.0.1";
    public final static int port = 12056;

    public static boolean push(String json) {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(localhost, port);
        try {
            socket.connect(socketAddress, 1000);
            SocketPacket socketPacket = new SocketPacket(Tools.stringToData(json, Tools.DEFAULT_ENCODE), false);
            socketPacket.packet();
            socket.getOutputStream().write(socketPacket.getAllBuf());
            System.out.println("发送信息："+json);
            socket.getOutputStream().flush();
            byte[] buf = new byte[4];
            socket.getInputStream().read(buf);
            long res = Tools.toLong(buf);
            System.out.println("res ="+ res);
//            System.out.println(Tools.toHex(buf));
            socket.close();
            return res == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean push(String from, String to, Object msg, String key){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Parm.FROM, from);
            jsonObject.put(Parm.TO, to);
            jsonObject.put(key, msg);
            return push(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
