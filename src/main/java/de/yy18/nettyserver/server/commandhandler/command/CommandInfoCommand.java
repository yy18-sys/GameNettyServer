package de.yy18.nettyserver.server.commandhandler.command;

import de.yy18.nettyserver.server.util.DateParser;

public final class CommandInfoCommand implements Command {

    @Override
    public void executeCommand() {
        System.out.println("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] Command info:");
        System.out.println("                      exit - shutdown the server");
        System.out.println("                      gameconfig - outputs the gameconfig data");
    }

}
