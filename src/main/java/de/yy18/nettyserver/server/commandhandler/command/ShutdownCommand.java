package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;

public final class ShutdownCommand implements Command{

    @Override
    public void executeCommand() {
        ServerBase.shutdownServer();
    }

}
