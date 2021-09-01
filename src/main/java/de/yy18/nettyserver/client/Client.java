package de.yy18.nettyserver.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public final class Client {

    static final String HOST = "[2a02:908:375:cf00:614e:27bd:4dca:541b]";
    static final int PORT = 8013;
    static String clientName;

    public static void main(String[] args) throws Exception {

        try{
            Socket s=new Socket(HOST,PORT);
            DataInputStream din=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

            String str="",str2="";
            while(!str.equals("stop")){
                str=br.readLine();
                dout.writeUTF(str);
                dout.flush();
                str2=din.readUTF();
                System.out.println("Server says: "+str2);
            }

            dout.close();
            s.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

}
