package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.packets.IPacketPlayIn;
import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.Arrays;

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
            if(socket.isConnected()) {
                try {
                    socket.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            this.thread.stop();
            ListenerHandler.getINSTANCE().remove(this);
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Listening on client packets");
        while (isRunning) {
            System.out.println(Arrays.toString(socket.getInputStream().readAllBytes()));
            final byte[] input = socket.getInputStream().readAllBytes();
            System.out.println("test successful");
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
