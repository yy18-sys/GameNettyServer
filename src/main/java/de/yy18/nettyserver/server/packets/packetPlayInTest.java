package de.yy18.nettyserver.server.packets;

import lombok.NonNull;

public class packetPlayInTest extends PacketPlayIn{

    packetPlayInTest(byte[] bytes) {
        super(bytes);
    }


    @Override
    public byte[] decodePacket() {
        return new byte[0];
    }

}
