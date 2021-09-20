package de.yy18.nettyserver.server.packets.out.userinfo;

import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOut;
import lombok.NonNull;

public final class PacketPlayOutUserInfoResponse extends PacketPlayOut {

    private final boolean acceptUser;

    public PacketPlayOutUserInfoResponse(final short packetNumber, final boolean acceptUser) {
        super(packetNumber);
        this.acceptUser = acceptUser;
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue(acceptUser);
        return super.getContent();
    }

}
