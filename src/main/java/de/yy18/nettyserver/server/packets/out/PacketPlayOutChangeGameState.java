package de.yy18.nettyserver.server.packets.out;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;

public final class PacketPlayOutChangeGameState extends PacketPlayOut{

    public PacketPlayOutChangeGameState(@NonNull final PacketType packetType) {
        super(packetType);
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(super.getPacketType().getType());
        super.writeValue(ServerBase.getGameConfig().getGameState().ordinal());
        return super.getContent();
    }

}
