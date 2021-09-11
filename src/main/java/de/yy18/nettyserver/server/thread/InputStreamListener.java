package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.packets.IPacketPlayIn;
import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.UUID;

public class InputStreamListener implements Runnable, Listener{

    private final Socket socket;
    private final UUID uuid;
    private final Thread thread;
    private boolean isRunning;
    private byte[] content;
    private int readPos = 0;

    public InputStreamListener(@NonNull final Socket socket, @NonNull final UUID uuid) throws IOException {
        this.socket = socket;
        this.uuid = uuid;
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

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @SneakyThrows
    @Override
    public synchronized void run() {
        while (isRunning) {
            final BufferedInputStream inputS = new BufferedInputStream(socket.getInputStream());
            this.content = new byte[30];
            while(inputS.read(content) != -1) {
                final int packetTypeNumber = readShort();
                for (PacketType packetType : PacketType.values()) {
                    if(packetTypeNumber == packetType.ordinal()) {
                        Constructor<?> constructor = packetType.getAClass().getConstructors()[0];
                        IPacketPlayIn packet = (IPacketPlayIn) constructor.newInstance(content
                                , socket.getRemoteSocketAddress());
                        packet.decodePacket();
                    }
                }
                content = new byte[30];
            }
        }
    }

    public short readShort() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1]};
        increase(2);
        return (short)(((value[0] & 0xFF) << 8) | (value[1] & 0xFF));
    }

    public int readInt() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1], clone[readPos+2], clone[readPos+3]};
        increase(4);
        return value[0] << 24 | (value[1] & 0xFF) << 16 | (value[2] & 0xFF) << 8 | (value[3] & 0xFF);
    }

    private void increase(int i) {
        this.readPos += i;
    }

}
