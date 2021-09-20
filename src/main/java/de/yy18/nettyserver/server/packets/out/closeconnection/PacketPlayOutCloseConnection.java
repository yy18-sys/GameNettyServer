package de.yy18.nettyserver.server.packets.out.closeconnection;

import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import lombok.NonNull;

public final class PacketPlayOutCloseConnection extends PacketPlayOut {

    private final CloseConnectionType closeConnectionType;

    public PacketPlayOutCloseConnection(final short packetNumber, @NonNull final CloseConnectionType closeConnectionType) {
        super(packetNumber);
        this.closeConnectionType = closeConnectionType;
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(this.closeConnectionType.ordinal());
        return getContent();
    }

}
