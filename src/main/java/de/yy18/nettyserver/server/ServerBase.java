package de.yy18.nettyserver.server;

import de.yy18.nettyserver.server.commandhandler.CommandHandler;
import de.yy18.nettyserver.server.gamestatus.GameConfig;
import de.yy18.nettyserver.server.gamestatus.GameState;
import de.yy18.nettyserver.server.thread.ConnectServerListener;
import de.yy18.nettyserver.server.thread.ListenerHandler;
import de.yy18.nettyserver.server.user.UserManager;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.Getter;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public final class ServerBase {

    private static final int PORT = 7999;
    private static ServerSocket serverSocket;
    @Getter
    private static GameConfig gameConfig;

    public static void main(String[] args) throws IOException, InterruptedException {
        gameConfig = new GameConfig(6);
        final Scanner scanner = new Scanner(System.in);
        serverSocket = new ServerSocket(PORT);
        new ConnectServerListener(serverSocket).start();
        System.out.println("["+ DateParser.parseTime(System.currentTimeMillis()) +" ServerInfo] Server started successfully!");
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
            System.out.println("Error could close ServerSocket");
        }
        ListenerHandler.getINSTANCE().stopAllHandler();
        Thread.sleep(100);
        System.out.println("["+ DateParser.parseTime(System.currentTimeMillis())
                +" ServerInfo] Server successfully closed!");
    }

    public static int getPORT() {
        return PORT;
    }

}
