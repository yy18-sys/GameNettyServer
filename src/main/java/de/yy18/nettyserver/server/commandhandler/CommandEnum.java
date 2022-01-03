package de.yy18.nettyserver.server.commandhandler;

import de.yy18.nettyserver.server.commandhandler.command.*;
import lombok.NonNull;

public enum CommandEnum {

    EXIT("exit", new ShutdownCommand()),
    INFO("info", new CommandInfoCommand()),
    UM("um", new UserManagerCommand()),
    USERMANAGER("usermanager", new UserManagerCommand()),
    NS("ns", new ChangeGameStateCommand()),
    NEXTSTATE("nextstate", new ChangeGameStateCommand()),
    SEND("send", new PacketSendCommand());

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
