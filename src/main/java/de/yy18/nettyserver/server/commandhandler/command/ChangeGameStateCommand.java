package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChangeGameStateCommand implements Command{

    @Override
    public void executeCommand(@NotNull @NonNull String[] strings) {
        try {
            ServerBase.getGameConfig().nextGameState();
        } catch (IOException ignored) {

        }
    }

}
