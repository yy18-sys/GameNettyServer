package de.yy18.nettyserver.server.packets.out;

import de.yy18.nettyserver.server.packets.PacketType;
import lombok.NonNull;

public final class PacketPlayOutUserInfoResponse extends PacketPlayOut {

    private final boolean acceptUser;

    public PacketPlayOutUserInfoResponse(@NonNull final PacketType packetType, final boolean acceptUser) {
        super(packetType);
        this.acceptUser = acceptUser;
    }

    @Override
    public byte[] encodePacket() {
        super.writeValue((int)getPacketType().getType());
        super.writeValue(acceptUser);
        return super.getContent();
    }

}
