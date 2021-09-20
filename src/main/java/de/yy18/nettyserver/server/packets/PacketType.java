package de.yy18.nettyserver.server.packets;

import de.yy18.nettyserver.server.packets.in.PacketPlayInUserInfo;
import de.yy18.nettyserver.server.packets.out.closeconnection.PacketPlayOutCloseConnection;
import de.yy18.nettyserver.server.packets.out.gamestart.PacketPlayOutGameStart;
import de.yy18.nettyserver.server.packets.out.userinfo.PacketPlayOutUserInfoResponse;
import lombok.NonNull;

public enum PacketType {

    INUSERINFO(PacketPlayInUserInfo.class,1),
    OUTACCEPTUSER(PacketPlayOutUserInfoResponse.class,2),
    OUTDENYUSER(PacketPlayOutCloseConnection.class, 4),
    OUTENEMYINFO(PacketPlayInUserInfo.class,6),
    OUTGAMESTART(PacketPlayOutGameStart.class,8);

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
