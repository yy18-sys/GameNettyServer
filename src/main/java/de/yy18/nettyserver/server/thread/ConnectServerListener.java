package de.yy18.nettyserver.server.thread;

import de.yy18.nettyserver.server.user.User;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class ConnectServerListener implements Runnable, Listener {

	private final ServerSocket serverSocket;
	private final Thread thread;
	private boolean isRunning;

	public ConnectServerListener(@NonNull final int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.thread = new Thread(this);
		isRunning = false;
	}

	@Override
	public Listener start() {
		if(!isRunning) {
			this.thread.start();
			ListenerHandler.getINSTANCE().add(this);
			isRunning = true;
		}
		return this;
	}

	@Override
	public Listener stop() {
		try {
			if(isRunning) {
				this.thread.stop();
				serverSocket.close();
				isRunning = false;
			}
		} catch (IOException exception) {
			exception.fillInStackTrace();
		}
		return this;
	}

	@SneakyThrows
	@Override
	public void run() {
		while (isRunning) {
			final Socket socket = serverSocket.accept();
			final User user = new User(socket);
			System.out.println("[Server] Client joined - " + user.toConsole());
		}
	}

}