package com.wlhse.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//Handler处理类
@Component
public class MyWebSocketHandler implements WebSocketHandler {
    private static final Map<Long, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<>();
    }

    //建立连接后
    public void afterConnectionEstablished(WebSocketSession session) {
        Object uidObj = session.getAttributes().get("uid");
        long uid = Long.valueOf(String.valueOf(uidObj));
        userSocketSessionMap.put(uid, session);
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
    }

    //消息传输错误处理
    public void handleTransportError(WebSocketSession session,
                                     Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
        // 移除Socket会话
        mMyWebSocketHandlerUtil(it, session);
    }


    //关闭连接后
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus closeStatus) {
        Iterator<Entry<Long, WebSocketSession>> it = userSocketSessionMap
                .entrySet().iterator();
        // 移除Socket会话
        mMyWebSocketHandlerUtil(it, session);
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    //给某个用户发送消息
    public void sendMessageToUser(Long uid, TextMessage message)
            throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

    //Socket会话已经移除
    private void mMyWebSocketHandlerUtil(Iterator<Entry<Long, WebSocketSession>> it, WebSocketSession session) {
        while (it.hasNext()) {
            Entry<Long, WebSocketSession> entry = it.next();
            if (entry.getValue().getId().equals(session.getId())) {
                userSocketSessionMap.remove(entry.getKey());
                break;
            }
        }
    }

}
