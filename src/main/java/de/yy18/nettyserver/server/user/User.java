package de.yy18.nettyserver.server.user;

import de.yy18.nettyserver.server.thread.DisconnectServerListener;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.UUID;

public final class User {

    private final UUID uuid = UUID.randomUUID();
    @Getter
    @Setter
    private String userName = "Unknown_User";
    private final long timeConnected;
    private final Socket socket;
    private final InetSocketAddress inetSocketAddress;
    private final DisconnectServerListener communicationClientListener;

    public User(@NonNull final Socket socket) {
        this.socket = socket;
        this.timeConnected = System.currentTimeMillis();
        this.inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
        communicationClientListener = (DisconnectServerListener) new DisconnectServerListener(socket).start();
        UserManager.getINSTANCE().add(this);
    }

    public String toConsole() throws ParseException {

        return "{[uuid]: "+uuid+", [ipv6address]: "+inetSocketAddress.getAddress()+", [port]: "
                +inetSocketAddress.getPort()+", [connected]: "+ DateParser.parseDateTime(timeConnected) +"}";
    }

    public Socket getSocket() {
        return socket;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public DisconnectServerListener getCommunicationClientListener() {
        return communicationClientListener;
    }

}
