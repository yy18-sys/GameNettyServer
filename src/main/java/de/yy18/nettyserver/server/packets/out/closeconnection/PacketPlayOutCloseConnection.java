package de.yy18.nettyserver.server.packets.out.closeconnection;

import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import lombok.NonNull;

public final class PacketPlayOutCloseConnection extends PacketPlayOut {

    private final CloseConnectionType closeConnectionType;

    public PacketPlayOutCloseConnection(@NonNull final PacketType packetType, @NonNull final CloseConnectionType closeConnectionType) {
        super(packetType);
        this.closeConnectionType = closeConnectionType;
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(this.closeConnectionType.ordinal());
        return getContent();
    }

}
