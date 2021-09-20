package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.gamestatus.GameConfig;
import de.yy18.nettyserver.server.util.ConsoleWriter;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.NonNull;

public final class GameConfigCommand implements Command {

    @Override
    public void executeCommand(@NonNull String[] strings) {
        final GameConfig gameConfig = ServerBase.getGameConfig();
        ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                +" ServerInfo] Gameconfig list:");
        ConsoleWriter.write("                      gameState: "+ gameConfig.getGameState());
        ConsoleWriter.write("                      players: "+ gameConfig.getPlayerAmount());
        ConsoleWriter.write("                      maxPlayers: "+ gameConfig.getMaxPlayer());
    }
}
