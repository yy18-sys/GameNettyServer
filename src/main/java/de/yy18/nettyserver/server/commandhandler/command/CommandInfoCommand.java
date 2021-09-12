package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.util.DateParser;
import org.jetbrains.annotations.NotNull;

public final class CommandInfoCommand implements Command {

    @Override
    public void executeCommand(@NotNull final String[] strings) {
        System.out.println("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] Command info:");
        System.out.println("                      exit - shutdown the server");
        System.out.println("                      um / usermanager - open usermanager options");
        System.out.println("                      ns  / nextstate - shutdown the server");
        System.out.println("                      gameconfig - outputs the gameconfig data");
    }

}
