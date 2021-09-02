package de.yy18.nettyserver.server;

import de.yy18.nettyserver.server.commandhandler.CommandHandler;
import de.yy18.nettyserver.server.thread.ConnectServerListener;
import de.yy18.nettyserver.server.thread.ListenerHandler;
import de.yy18.nettyserver.server.util.DateParser;

import java.io.*;
import java.util.Scanner;

public final class ServerBase {

    private static final int PORT = 7999;

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        new ConnectServerListener(PORT).start();
        System.out.println("["+ DateParser.parseTime(System.currentTimeMillis()) +" ServerInfo] Server started successfully!");
        while (scanner.hasNext()) {
            final String input = scanner.next();
            CommandHandler.getINSTANCE().handleCommand(input);
        }
        shutdownServer();
    }

    public static void shutdownServer() {
        ListenerHandler.getINSTANCE().stopAllHandler();
    }

    public static int getPORT() {
        return PORT;
    }

}
