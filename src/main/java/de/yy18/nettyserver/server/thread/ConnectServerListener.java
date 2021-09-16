package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.gamestatus.GameState;
import de.yy18.nettyserver.server.packets.PacketPlayOutHandler;
import de.yy18.nettyserver.server.packets.PacketType;
import de.yy18.nettyserver.server.packets.out.closeconnection.CloseConnectionType;
import de.yy18.nettyserver.server.packets.out.closeconnection.PacketPlayOutCloseConnection;
import de.yy18.nettyserver.server.user.User;
import de.yy18.nettyserver.server.util.ConsoleWriter;
import de.yy18.nettyserver.server.util.DateParser;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

public final class ConnectServerListener implements Runnable, Listener {

	private final UUID uuid = UUID.randomUUID();
	private final ServerSocket serverSocket;
	@Getter
	private final Thread thread;
	private boolean isRunning;

	public ConnectServerListener(@NonNull final ServerSocket serverSocket) throws IOException {
		this.serverSocket = serverSocket;
		this.thread = new Thread(this);
		isRunning = false;
	}

	@Override
	public Listener start() {
		if(!isRunning) {
			isRunning = true;
			this.thread.start();
			ListenerHandler.getINSTANCE().add(this);
		}
		return this;
	}

	@Override
	public void stop() {
		if(isRunning) {
			isRunning = false;
			this.thread.stop();
		}
	}

	@Override
	public UUID getUUID() {
		return this.uuid;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (isRunning) {
			try {
				final Socket socket = serverSocket.accept();
				if(ServerBase.getGameConfig().getGameState() == GameState.WAITING) {
					if(!ServerBase.getGameConfig().isMaxReached()) {
						final User user = new User(socket);
						ServerBase.getGameConfig().addPlayer();
						ConsoleWriter.write("[" + DateParser
								.parseTime(System.currentTimeMillis()) + " ServerInfo] Client joined - " + user.toConsole());
					} else {
						PacketPlayOutHandler.sendPacket(new PacketPlayOutCloseConnection(PacketType.OUTDENYUSER
								, CloseConnectionType.CLOSE_FULL), socket);
						socket.close();
					}
				} else {
					Thread.sleep(100);
					PacketPlayOutHandler.sendPacket(new PacketPlayOutCloseConnection(PacketType.OUTDENYUSER
							, CloseConnectionType.CLOSE_INGAME), socket);
					socket.close();
				}
			} catch (SocketException ignored) {

			}
		}
	}

}