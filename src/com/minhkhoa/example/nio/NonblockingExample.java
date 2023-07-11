package com.minhkhoa.example.nio;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NonblockingExample {

    private final Database database;
    private final ExecutorService executorService;
    private final Map<String, Handler> userRequestHandlers;

    public NonblockingExample() {
        database = new Database();
        executorService = Executors.newFixedThreadPool(10);
        userRequestHandlers = new HashMap<>();
        userRequestHandlers.put("userInfoGet", database::getUserInfo);
        userRequestHandlers.put("friendListGet", database::getFriendList);
        userRequestHandlers.put("messageListGet", database::getMessageList);
    }

    public void receivedUserRequest(String requestInput) {
        Request request = processRequestInput(requestInput);
        executorService.execute(() -> {
            Object response = handlerRequest(request);
            executorService.execute(() ->
                    responseToUser(request.getUserId(), response)
            );
        });
    }

    private Object handlerRequest(Request request) {
        Handler handler = userRequestHandlers.get(request.getApi());
        Object result = handler.handle(request.getUserId());
        return result;
    }

    private void responseToUser(String userId, Object responseData) {
        System.out.println("response to user: " + userId + " data: " + responseData);
    }

    private Request processRequestInput(String requestInput) {
        String[] apiData = requestInput.split(":");
        return new Request(apiData[0], apiData[1]);
    }

    private static void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) { }
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
            return api;
        }

        public String getUserId() {
            return userId;
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

    public static void main(String[] args) {
        NonblockingExample nio = new NonblockingExample();
        String[] users = new String[] {"mk12", "meta"};
        for (String user : users) {
            nio.receivedUserRequest("userInfoGet:" + user);
            nio.receivedUserRequest("friendListGet:" + user);
            nio.receivedUserRequest("messageListGet:" + user);
        }
    }
}
