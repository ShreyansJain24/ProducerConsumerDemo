package com.shreyans;

import java.util.UUID;

public class MessageObj {
    @Override
    public String toString() {
        return "com.shreyans.MessageObj{" +
                "message='" + message + '\'' +
                ", msgId='" + msgId + '\'' +
                '}';
    }

    private final String message;
    private final String msgId = UUID.randomUUID().toString();

    public String getMsgId() {
        return msgId;
    }

    public MessageObj(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
