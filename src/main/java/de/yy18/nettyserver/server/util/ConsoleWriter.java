package de.yy18.nettyserver.server.util;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public final class ConsoleWriter {

    public static final boolean DEVCONSOLE = false;

    public static void write(@NonNull final String message) {
        if (DEVCONSOLE) {
            writeSystem(message);
        } else {
            writeConsole(message);
        }
    }

    private static void writeSystem(@NonNull final String message) {
        System.out.println(message);
    }

    private static void writeConsole(@NonNull final String message) {
        System.console().writer().println(message);
    }

}
