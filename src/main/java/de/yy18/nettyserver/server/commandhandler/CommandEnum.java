package de.yy18.nettyserver.server.commandhandler;

import de.yy18.nettyserver.server.commandhandler.command.Command;
import de.yy18.nettyserver.server.commandhandler.command.CommandInfoCommand;
import de.yy18.nettyserver.server.commandhandler.command.ShutdownCommand;
import de.yy18.nettyserver.server.commandhandler.command.UserManagerCommand;
import lombok.NonNull;

public enum CommandEnum {

    EXIT("exit", new ShutdownCommand()),
    INFO("info", new CommandInfoCommand()),
    UM("um", new UserManagerCommand()),
    USERMANAGER("usermanager", new UserManagerCommand());

    private final String commandName;
    private final Command command;

    CommandEnum(@NonNull final String commandName, @NonNull final Command command) {
        this.commandName = commandName;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandName() {
        return this.commandName;
    }

}
