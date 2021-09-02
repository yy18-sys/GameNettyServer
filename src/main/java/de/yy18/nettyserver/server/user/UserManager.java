package de.yy18.nettyserver.server.user;

import lombok.NonNull;

import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class UserManager {

    private static final UserManager INSTANCE = new UserManager();

    public static UserManager getINSTANCE() {
        return INSTANCE;
    }

    private final List<User> userList = new ArrayList<>();

    public void add(@NonNull final User user) {
        userList.add(user);
    }

    public boolean remove(@NonNull final User user) {
        return userList.remove(user);
    }

    public boolean isEmpty() {
        return userList.isEmpty();
    }

    public String getUserConsoleBySocket(@NonNull final Socket socket) throws ParseException {
        for (User user : userList) {
            if(user.getInetSocketAddress().equals(socket.getRemoteSocketAddress())) return user.toConsole();
        }
        return "not found";
    }

    public boolean contains(@NonNull final User user) {
        return userList.contains(user);
    }

    public <T> T[] toArray(T[] a) {
        return userList.toArray(a);
    }

}
