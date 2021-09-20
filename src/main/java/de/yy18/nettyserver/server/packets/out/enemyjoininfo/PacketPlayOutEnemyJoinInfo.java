package de.yy18.nettyserver.server.packets.out.enemyjoininfo;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;

public final class PacketPlayOutEnemyJoinInfo extends PacketPlayOut {

    public PacketPlayOutEnemyJoinInfo(final short packetNumber) {
        super(packetNumber);
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(ServerBase.getGameConfig().getGameState().ordinal());
        return super.getContent();
    }

}
