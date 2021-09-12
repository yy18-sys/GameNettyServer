package de.yy18.nettyserver.server.packets;

import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import de.yy18.nettyserver.server.user.User;
import de.yy18.nettyserver.server.user.UserManager;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor
public final class PacketPlayOutHandler {

    public static synchronized void sendAllPacket(@NonNull final PacketPlayOut packet) throws IOException {
        sendPacket(packet, UserManager.getINSTANCE().toArray(new User[0]));
    }

    public static synchronized void sendPacket(@NonNull final PacketPlayOut packet, @NonNull final User user) throws IOException {
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(user.getSocket().getOutputStream());
        bufferedOutputStream.write(packet.encodePacket());
        bufferedOutputStream.flush();
    }

    public static synchronized void sendPacket(@NonNull final PacketPlayOut packet, @NonNull final User[] users) throws IOException {
        for (User user : users) {
            sendPacket(packet, user);
        }
    }

    public static synchronized void sendPacket(@NonNull final PacketPlayOut packet, @NonNull final UUID uuid) throws IOException {
        final User user = UserManager.getINSTANCE().getUserByUUID(uuid);
        assert user != null;
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(user.getSocket().getOutputStream());
        bufferedOutputStream.write(packet.encodePacket());
        bufferedOutputStream.flush();
    }

    public static synchronized void sendPacket(@NonNull final PacketPlayOut packet, @NonNull final UUID[] uuids) throws IOException {
        for (UUID uuid : uuids) {
            sendPacket(packet, uuid);
        }
    }

}
