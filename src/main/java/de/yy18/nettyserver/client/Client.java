package de.yy18.nettyserver.client;

import java.net.Socket;

public final class Client {

    static final String HOST = "[2a02:908:375:cf00:614e:27bd:4dca:541b]";
    static final int PORT = 57777;

    public static void main(String[] args) throws Exception {

        try{
            Socket s=new Socket(HOST,PORT);
            Thread.sleep(100000);
            s.close();
        }catch(Exception e) {
            System.out.println(e);
        }

    }

}
