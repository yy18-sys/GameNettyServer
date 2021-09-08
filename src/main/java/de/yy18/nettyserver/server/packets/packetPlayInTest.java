package de.yy18.nettyserver.server.packets;

public class packetPlayInTest extends PacketPlayIn{

    packetPlayInTest(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void decodePacket(byte[] bytes) {
        System.out.println("bester programmierer!");
    }
}
