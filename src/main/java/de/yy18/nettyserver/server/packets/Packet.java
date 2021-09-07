package de.yy18.nettyserver.server.packets;

import lombok.Getter;
import lombok.NonNull;

public abstract class Packet {

    @Getter
    private final PacketType packetType;

    Packet(@NonNull final PacketType packetType) {
        this.packetType = packetType;
    }

}
