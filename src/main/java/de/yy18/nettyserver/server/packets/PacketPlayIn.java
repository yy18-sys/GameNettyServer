package de.yy18.nettyserver.server.packets;

import lombok.NonNull;

public abstract class PacketPlayIn extends Packet{

    PacketPlayIn(@NonNull PacketType packetType) {
        super(packetType);
    }

    abstract void decodePacket();

}
