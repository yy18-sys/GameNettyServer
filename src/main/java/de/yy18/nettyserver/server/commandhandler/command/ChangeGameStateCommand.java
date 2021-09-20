package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.packets.PacketPlayOutHandler;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.enemyjoininfo.PacketPlayOutEnemyJoinInfo;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ChangeGameStateCommand implements Command{

    @Override
    public void executeCommand(@NotNull @NonNull String[] strings) {
        try {
            ServerBase.getGameConfig().nextGameState();
            PacketPlayOutHandler.sendAllPacket(new PacketPlayOutEnemyJoinInfo(PacketType.OUTENEMYINFO.getType()));
            System.out.println("[" + DateParser.parseTime(System.currentTimeMillis())
                    + " ServerInfo] Next gamestate initialized {"+ServerBase.getGameConfig().getGameState().name()+"}");
        } catch (IOException ignored) {

        }
    }

}
