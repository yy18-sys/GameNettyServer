package de.yy18.nettyserver.server.commandhandler.command;

import lombok.NonNull;

public interface Command {

    void executeCommand(@NonNull final String[] strings);

}
