package de.yy18.nettyserver.server.user;

import de.yy18.nettyserver.server.exception.ServerException;
import de.yy18.nettyserver.server.thread.Listener;
import de.yy18.nettyserver.server.thread.ListenerHandler;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    public String getAllUserConsole() throws ParseException {
        StringBuilder string = new StringBuilder();
        final User[] users = this.userList.toArray(User[]::new);
        if(users.length == 0) return "["+ DateParser.parseTime(System.currentTimeMillis())
                +" ServerInfo] No user found";
        for (int i = 0; i < users.length; i++) {
            string.append("[").append(DateParser.parseTime(System.currentTimeMillis()))
                    .append(" ServerInfo] ").append(users[i].toConsole());
            if(!(i == users.length-1)) string.append("\n");
        }
        return string.toString();
    }

    public void updateUser(@NonNull final InetSocketAddress iNetSocketAddress, @NonNull final User user) {
        this.userList.remove(getUserByInetSocketAddress(iNetSocketAddress));
        this.userList.add(user);
    }

    public User getUserByInetSocketAddress(@NonNull final InetSocketAddress iNetSocketAddress) {
        for (User user: this.userList.stream().toList()) {
            if(user.getInetSocketAddress().equals(iNetSocketAddress)) {
                return user;
            }
        }
        throw new ServerException("Could find user");
    }

    public User getUserByUUID(@NonNull final UUID uuid) {
        for (User user: this.userList.stream().toList()) {
            if(user.getUuid().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public boolean contains(@NonNull final User user) {
        return userList.contains(user);
    }

    public <T> T[] toArray(T[] a) {
        return userList.toArray(a);
    }

    public void closeConnection(@NonNull final User user) throws IOException {
        final Iterator<Listener> iterator = ListenerHandler.getINSTANCE().iterator();
        if(iterator.hasNext()) {
            final Listener listener = iterator.next();
            if(listener.getUUID().equals(user.getUuid())) {
                listener.stop();
            }
        }
        user.getSocket().close();
    }

    public void closeAllConnection() {
        for (User user : userList) {
           try {
               user.getSocket().close();
           } catch (IOException exception) {
               exception.printStackTrace();
           }
        }
    }

}
