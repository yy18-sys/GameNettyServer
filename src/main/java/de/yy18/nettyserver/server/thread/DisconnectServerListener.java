package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.user.UserManager;
import lombok.NonNull;
import lombok.SneakyThrows;
import java.net.Socket;

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
    public Listener stop() {
        if(isRunning) {
            this.thread.stop();
            ListenerHandler.getINSTANCE().remove(this);
            isRunning = false;
        }
        return this;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (isRunning) {
            if(!socket.isConnected()) {
                socket.close();
                System.out.println("[Server] Client quit - "+ UserManager.getINSTANCE().getUserConsoleBySocket(socket));
                stop();
            }
        }
    }

}
