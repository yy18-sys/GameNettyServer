package de.yy18.nettyserver.server.packets;

import de.yy18.nettyserver.server.packets.in.PacketPlayInUserInfo;
import lombok.NonNull;

public enum PacketType {

    INUSERINFO(PacketPlayInUserInfo.class,1),
    OUTACCEPTUSER(2),
    OUTDENYUSER(4),
    OUTENEMYINFO(PacketPlayInUserInfo.class,6);

    private final Class<?> aClass;
    private final short type;

    PacketType(final int type) {
        this.aClass = null;
        this.type = (short) type;
    }

    PacketType(@NonNull final Class<?> aClass, final int type) {
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
