package de.yy18.nettyserver.server.packets.out.gamestart;

import de.yy18.nettyserver.server.packets.out.PacketPlayOut;

public final class PacketPlayOutGameStart extends PacketPlayOut {

    public PacketPlayOutGameStart(final short packetNumber) {
        super(packetNumber);
    }

    @Override
    public byte[] encodePacket() {
        return getContent();
    }

}
