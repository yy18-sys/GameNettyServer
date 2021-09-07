package de.yy18.nettyserver.server.thread;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.net.Socket;

public class InputStreamListener implements Runnable, Listener{

    private final Socket socket;
    private final Thread thread;
    private boolean isRunning;

    public InputStreamListener(@NonNull final Socket socket) {
        this.socket = socket;
        this.thread = new Thread(this);
        isRunning = false;
    }

    @Override
    public Listener start() {
        if(!isRunning) {
            isRunning = true;
            this.thread.start();
            ListenerHandler.getINSTANCE().add(this);
        }
        return this;
    }

    @Override
    public Listener stop() {
        if(isRunning) {
            isRunning = false;
            this.thread.stop();
            ListenerHandler.getINSTANCE().remove(this);
        }
        return this;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (isRunning) {
            final InputStream inputStream = socket.getInputStream();
            if(inputStream.read() != -1) {
                final byte[] input = inputStream.readAllBytes();
            }
        }
    }
}
