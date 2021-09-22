package de.yy18.nettyserver.server;

import de.yy18.nettyserver.server.commandhandler.CommandHandler;
import de.yy18.nettyserver.server.gamestatus.GameConfig;
import de.yy18.nettyserver.server.gamestatus.GameState;
import de.yy18.nettyserver.server.thread.ConnectServerListener;
import de.yy18.nettyserver.server.thread.ListenerHandler;
import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.ConsoleWriter;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.Getter;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public final class ServerBase {

    private static final int PORT = 7999;
    private static ServerSocket serverSocket;
    @Getter
    private static GameConfig gameConfig;
    @Getter
    private static Console console;

    public static void main(String[] args) throws IOException, InterruptedException {
        console = System.console();
        if (ConsoleWriter.DEVCONSOLE) startServer();
        if(console == null && !GraphicsEnvironment.isHeadless()) {
            final String filename = ServerBase.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            System.out.println(filename);
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "cmd", "/c", "java -jar \"" + filename + "\""});
        } else {
            startServer();
        }
    }

    private static void startServer() throws IOException, InterruptedException {
        gameConfig = new GameConfig(6);
        final Scanner scanner = new Scanner(System.in);
        serverSocket = new ServerSocket(PORT);
        new ConnectServerListener(serverSocket).start();
        ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis()) +" ServerInfo] Server started successfully!");
        ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis()) +" ServerInfo] Current gamestate: "
                + getGameConfig().getGameState().name());
        ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis()) +" ServerInfo] To connect properly " +
                "with a client, the server need to be in gamestate "+GameState.WAITING.name());
        while (scanner.hasNext()) {
            final String input = scanner.nextLine();
            CommandHandler.getINSTANCE().handleCommand(input);
        }
        shutdownServer();
    }

    public static void shutdownServer() throws InterruptedException {
        UserManager.getINSTANCE().closeAllConnection();
        try {
            serverSocket.close();
        } catch (IOException exception) {
            ConsoleWriter.write("Error could close ServerSocket");
        }
        ListenerHandler.getINSTANCE().stopAllHandler();
        Thread.sleep(100);
        ConsoleWriter.write("["+ DateParser.parseTime(System.currentTimeMillis())
                +" ServerInfo] Server successfully closed!");
        Thread.sleep(300);
        System.exit(-1);
    }

    public static int getPORT() {
        return PORT;
    }

}
