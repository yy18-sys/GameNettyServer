package de.yy18.nettyserver.client;

import java.net.*;

public final class Client {

    static final String HOST = "[2a02:908:375:cf00:614e:27bd:4dca:541b]";
    static final int PORT = 7999;

    public static void main(String[] args) throws Exception {

        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(Inet6Address.getByName(HOST), PORT));
            Thread.sleep(100000);
            socket.close();
        }catch(Exception e) {
            System.out.println(e);
        }

    }

}
