package com.baige.connect;


/**
 * Created by baige on 2018/3/21.
 */

public interface OnConnectedListener {
    void onConnected(BaseConnector connector);
    void onDisconnected(BaseConnector connector);
    void onResponse(BaseConnector connector, SocketPacket responsePacket);

    class SimpleOnConnectedListener implements OnConnectedListener{
        @Override
        public void onConnected(BaseConnector connector) {

        }

        @Override
        public void onDisconnected(BaseConnector connector) {

        }

        @Override
        public void onResponse(BaseConnector connector, SocketPacket responsePacket) {

        }
    }
}
