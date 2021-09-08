package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.packets.IPacketPlayIn;
import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.lang.reflect.Constructor;
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
        System.out.println("Listening on client packets");
        while (isRunning) {
            final InputStream inputStream = socket.getInputStream();
            if(inputStream.read() != -1) {
                final byte[] input = inputStream.readAllBytes();
                for (byte b : input) {
                    System.out.println(b);
                }
                System.out.println(input.length);
                final int packetTypeNumber = input[0];
                System.out.println(packetTypeNumber);
                for (PacketType packetType : PacketType.values()) {
                    if(packetTypeNumber == packetType.ordinal()) {
                        System.out.println("test successful");
                        Constructor<?> constructor = packetType.getAClass().getConstructors()[0];
                        IPacketPlayIn packet = (IPacketPlayIn) constructor.newInstance(input);
                        packet.decodePacket(input);
                        System.out.println("test successful2");
                    }
                }
            }
        }
    }
}
