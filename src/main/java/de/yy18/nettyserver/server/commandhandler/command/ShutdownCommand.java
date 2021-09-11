package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;
import org.jetbrains.annotations.NotNull;

public final class ShutdownCommand implements Command{

    @Override
    public void executeCommand(@NotNull final String[] strings) {
        try {
            ServerBase.shutdownServer();
        } catch (InterruptedException ignored) {

        }
    }

}
