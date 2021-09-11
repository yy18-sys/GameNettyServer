package de.yy18.nettyserver.server.packets;

import lombok.NonNull;

public enum PacketType {

    USERINFO(PacketPlayInUserInfo.class);

    private final Class<?> aClass;

    PacketType(@NonNull final  Class<?> aClass) {
        this.aClass = aClass;
    }

    public Class<?> getAClass() {
        return aClass;
    }

}
