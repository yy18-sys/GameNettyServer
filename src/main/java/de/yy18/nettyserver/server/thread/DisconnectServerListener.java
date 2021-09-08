package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public final class DisconnectServerListener implements Runnable, Listener{

    private final Socket socket;
    private final Thread thread;
    private boolean isRunning;

    public DisconnectServerListener(@NonNull final Socket socket) {
        this.socket = socket;
        this.thread = new Thread(this);
        isRunning = false;
    }

    @Override
    public Listener start() {
        if(!isRunning) {
            this.thread.start();
            ListenerHandler.getINSTANCE().add(this);
            isRunning = true;
        }
        return this;
    }

    @Override
    public void stop() {
        if(isRunning) {
            isRunning = false;
            this.thread.stop();
            ListenerHandler.getINSTANCE().remove(this);
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        while (isRunning) {
            try {
                if(socket.getInputStream().read() == -1) {
                    System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                            +" ServerInfo] Client quit   - "+ UserManager.getINSTANCE().getUserConsoleBySocket(socket));
                    stop();
                }
            } catch (SocketException exception) {
                System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                        +" ServerInfo] Client quit - "+ UserManager.getINSTANCE().getUserConsoleBySocket(socket));
                stop();
            }
        }
    }

}
