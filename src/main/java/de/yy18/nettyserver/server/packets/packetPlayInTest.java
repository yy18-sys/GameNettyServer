package de.yy18.nettyserver.server.packets;

import lombok.NonNull;

public class packetPlayInTest extends PacketPlayIn{

    packetPlayInTest(@NonNull PacketType packetType, byte[] bytes) {
        super(packetType, bytes);
    }

    @Override
    byte[] decodePacket() {
        return new byte[0];
    }

}
