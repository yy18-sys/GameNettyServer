package de.yy18.nettyserver.server.packets;

import de.yy18.nettyserver.server.packets.in.PacketPlayInUserInfo;
import lombok.NonNull;

public enum PacketType {

    INUSERINFO(PacketPlayInUserInfo.class,1),
    OUTACCEPTUSER(2),
    OUTCHANGEGAMESTATE(PacketPlayInUserInfo.class,3);

    private final Class<?> aClass;
    private final short type;

    PacketType(int type) {
        this.aClass = null;
        this.type = (short) type;
    }

    PacketType(@NonNull final Class<?> aClass, int type) {
        this.aClass = aClass;
        this.type = (short) type;
    }

    public Class<?> getAClass() {
        return aClass;
    }

    public short getType() {
        return type;
    }

}
