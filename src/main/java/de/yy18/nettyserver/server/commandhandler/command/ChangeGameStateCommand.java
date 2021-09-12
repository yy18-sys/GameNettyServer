package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.packets.PacketPlayOutHandler;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.PacketPlayOutChangeGameState;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChangeGameStateCommand implements Command{

    @Override
    public void executeCommand(@NotNull @NonNull String[] strings) {
        try {
            ServerBase.getGameConfig().nextGameState();
            PacketPlayOutHandler.sendAllPacket(new PacketPlayOutChangeGameState(PacketType.OUTCHANGEGAMESTATE));
        } catch (IOException ignored) {

        }
    }

}
