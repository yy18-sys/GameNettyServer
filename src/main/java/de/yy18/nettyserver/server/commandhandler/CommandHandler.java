package de.yy18.nettyserver.server.commandhandler;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public final class CommandHandler {

    private static final CommandHandler INSTANCE = new CommandHandler();

    public static CommandHandler getINSTANCE() {
        return INSTANCE;
    }

    public void handleCommand(@NonNull final String command) {
        final String commandLC = command.toLowerCase();
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if(commandEnum.getCommandName().compareTo(commandLC) == 0) {
                commandEnum.getCommand().executeCommand();
                return;
            }
        }
        System.out.println("ERROR - Command not found!");
    }

}
