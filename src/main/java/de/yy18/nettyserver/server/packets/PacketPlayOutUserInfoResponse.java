package de.yy18.nettyserver.server.packets;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class PacketPlayOutUserInfoResponse extends PacketPlayOut {

    private final boolean acceptUser;

    @Override
    public byte[] encodePacket() {
        super.writeValue(acceptUser);
        return super.getContent();
    }

}
