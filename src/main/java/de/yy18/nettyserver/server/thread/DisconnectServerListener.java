package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.net.Socket;
import java.util.UUID;

public final class DisconnectServerListener implements Runnable, Listener{

    private final Socket socket;
    private final UUID uuid;
    private final Thread thread;
    private boolean isRunning;

    public DisconnectServerListener(@NonNull final Socket socket, @NonNull final UUID uuid) {
        this.socket = socket;
        this.uuid = uuid;
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

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (isRunning) {
            if(socket.isClosed()) {
                System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                        +" ServerInfo] Client quit   - "+ UserManager.getINSTANCE().getUserConsoleBySocket(socket));
                stop();
            }
        }
    }

}
