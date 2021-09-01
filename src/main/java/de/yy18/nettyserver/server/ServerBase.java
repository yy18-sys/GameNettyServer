package de.yy18.nettyserver.server;

import de.yy18.nettyserver.server.thread.ConnectServerListener;

import java.io.*;
import java.util.Scanner;

public final class ServerBase {

    private static final int PORT = 8013;

    public static void main(String[] args) throws IOException {
        final Scanner scanner = new Scanner(System.in);
        new ConnectServerListener(8013).start();
        while (true) {

        }
    }

    public static int getPORT() {
        return PORT;
    }

}
