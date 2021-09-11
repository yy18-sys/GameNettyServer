package de.yy18.nettyserver.server.thread;

import java.util.UUID;

public interface Listener {

    Listener start();

    void stop();

    UUID getUUID();

}
