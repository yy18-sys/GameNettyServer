package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.ServerBase;
import de.yy18.nettyserver.server.gamestatus.GameState;
import de.yy18.nettyserver.server.user.User;
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
				if(ServerBase.getGameConfig().getGameState() == GameState.WAITING) {
					final Socket socket = serverSocket.accept();
					final User user = new User(socket);
					System.out.println("[" + DateParser.parseTime(System.currentTimeMillis()) + " ServerInfo] Client joined - " + user.toConsole());
				}
			} catch (SocketException ignored) {

			}
		}
	}

}