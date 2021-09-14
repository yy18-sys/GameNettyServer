package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.packets.in.IPacketPlayIn;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.user.UserManager;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.UUID;

public final class InputStreamListener implements Runnable, Listener{

    private final Socket socket;
    private final UUID uuid;
    private final Thread thread;
    private byte[] content;
    private int readPos = 0;

    public InputStreamListener(@NonNull final Socket socket, @NonNull final UUID uuid) throws IOException {
        this.socket = socket;
        this.uuid = uuid;
        this.thread = new Thread(this);
    }

    @Override
    public Listener start() {
        this.thread.start();
        ListenerHandler.getINSTANCE().add(this);
        return this;
    }

    @Override
    public void stop() {
        this.thread.stop();
        ListenerHandler.getINSTANCE().remove(this);
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @SneakyThrows
    @Override
    public synchronized void run() {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        this.content = new byte[30];
        try {
            while(bufferedInputStream.read(content) != -1) {
                final short packetTypeNumber = readShort();
                for (PacketType packetType : PacketType.values()) {
                    if(packetTypeNumber == packetType.getType()) {
                        final Constructor<?> constructor = packetType.getAClass().getConstructors()[0];
                        final IPacketPlayIn packet = (IPacketPlayIn) constructor.newInstance(content
                                , socket.getRemoteSocketAddress());
                        packet.decodePacket();
                    }
                }
                this.content = new byte[30];
            }
            UserManager.getINSTANCE().closeConnection(Objects
                    .requireNonNull(UserManager.getINSTANCE().getUserByUUID(this.uuid)));
        } catch (SocketException ignored) {

        }
        stop();
    }

    public short readShort() {
        final byte[] clone = content.clone();
        final byte[] value = {clone[readPos], clone[readPos+1]};
        increase(2);
        return (short)((value[0] & 0xFF | (value[1] & 0xFF) << 8));
    }

    private void increase(final int i) {
        this.readPos += i;
    }

}
