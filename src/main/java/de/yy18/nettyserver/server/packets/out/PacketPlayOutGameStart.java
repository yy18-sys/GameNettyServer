package de.yy18.nettyserver.server.packets.out;

import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;

public class PacketPlayOutGameStart extends PacketPlayOut{

    public PacketPlayOutGameStart(@NonNull PacketType packetType) {
        super(packetType);
    }

    @Override
    public byte[] encodePacket() {
        return new byte[0];
    }

}
