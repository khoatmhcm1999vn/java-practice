package com.mk.examples.example.nio;

import java.util.HashMap;
import java.util.Map;

public class BlockingIOExample {

    private final Database database;

    private final Map<String, Handler> userRequestHandlers;

    public BlockingIOExample() {
        database = new Database();
        userRequestHandlers = new HashMap<>();
        userRequestHandlers.put("userInfoGet", database::getUserInfo);
        userRequestHandlers.put("friendListGet", database::getFriendList);
        userRequestHandlers.put("messageListGet", database::getMessageList);
    }

    public void receivedUserRequest(String requestInput) {
        Request request = this.processRequestInput(requestInput);
        Object response = this.handlerRequest(request);
        this.responseToUser(request.getUserId(), response);
    }

    private Object handlerRequest(Request request) {
        Handler handler = userRequestHandlers.get(request.getApi());
        return handler.handle(request.getUserId());
    }

    private void responseToUser(String userId, Object responseData) {
        System.out.println("response to user: " + userId + " data: " + responseData);
    }

    private Request processRequestInput(String requestInput) {
        String[] apiData = requestInput.split(":");
        return new Request(apiData[0], apiData[1]);
    }

    public interface Handler {

        Object handle(String userId);

    }

    public static class Request {

        private final String api;

        private final String userId;

        public Request(String api, String userId) {
            this.api = api;
            this.userId = userId;
        }

        public String getApi() {
            return this.api;
        }

        public String getUserId() {
            return this.userId;
        }

    }

    public static class Database {

        public String getUserInfo(String userId) {
            delay(1000);
            return "id: " + userId + ", name: Monkey";
        }

        public String getFriendList(String userId) {
            delay(1000);
            return "fox, cat";
        }

        public String getMessageList(String userId) {
            delay(1000);
            return "hello, world";
        }

    }

    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) { }
    }

    public static void main(String[] args) {
        BlockingIOExample nio = new BlockingIOExample();
        nio.receivedUserRequest("userInfoGet:mk12");
        nio.receivedUserRequest("friendListGet:mk12");
        nio.receivedUserRequest("messageListGet:mk12");
    }

}
