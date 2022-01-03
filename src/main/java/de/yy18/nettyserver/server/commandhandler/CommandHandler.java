package de.yy18.nettyserver.server.commandhandler;

import de.yy18.nettyserver.server.util.ConsoleWriter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Locale;

@NoArgsConstructor
public final class CommandHandler {

    private static final CommandHandler INSTANCE = new CommandHandler();

    public static CommandHandler getINSTANCE() {
        return INSTANCE;
    }

    public void handleCommand(@NonNull final String command) {
        final String commandLC = command.toLowerCase();
        final String[] commandArray = commandLC.split(" ");
        if(commandArray.length == 0) return;
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if(commandEnum.getCommandName().toLowerCase(Locale.ROOT).compareTo(commandArray[0].toLowerCase(Locale.ROOT)) == 0) {
                commandEnum.getCommand().executeCommand(commandArray);
                return;
            }
        }
        ConsoleWriter.write("ERROR - Command not found!");
    }

}
