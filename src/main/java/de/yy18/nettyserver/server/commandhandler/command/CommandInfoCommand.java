package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.util.ConsoleWriter;
import de.yy18.nettyserver.server.util.DateParser;
import org.jetbrains.annotations.NotNull;

public final class CommandInfoCommand implements Command {

    @Override
    public void executeCommand(@NotNull final String[] strings) {
        ConsoleWriter.write("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] Command info:");
        ConsoleWriter.write("                      exit - shutdown the server");
        ConsoleWriter.write("                      um / usermanager - open usermanager options");
        ConsoleWriter.write("                      ns  / nextstate - shutdown the server");
        ConsoleWriter.write("                      gameconfig - outputs the gameconfig data");
    }

}
