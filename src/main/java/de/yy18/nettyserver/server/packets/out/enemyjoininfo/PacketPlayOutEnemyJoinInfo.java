package de.yy18.nettyserver.server.packets.out.enemyjoininfo;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import lombok.NonNull;

public final class PacketPlayOutEnemyJoinInfo extends PacketPlayOut {

    public PacketPlayOutEnemyJoinInfo(@NonNull final PacketType packetType) {
        super(packetType);
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(ServerBase.getGameConfig().getGameState().ordinal());
        return super.getContent();
    }

}
