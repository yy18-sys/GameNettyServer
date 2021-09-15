package de.yy18.nettyserver.server.packets.out.gamestart;

import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import lombok.NonNull;

public final class PacketPlayOutGameStart extends PacketPlayOut {

    public PacketPlayOutGameStart(@NonNull PacketType packetType) {
        super(packetType);
    }

    @Override
    public byte[] encodePacket() {
        return getContent();
    }

}
