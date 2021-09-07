package de.yy18.nettyserver.server.packets;

import lombok.NonNull;

public abstract class PacketPlayOut extends Packet{

    PacketPlayOut(@NonNull PacketType packetType) {
        super(packetType);
    }

    abstract void encodePacket();

}
