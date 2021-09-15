package de.yy18.nettyserver.server.gamestatus;

import lombok.Getter;

@Getter
public final class GameConfig {

    private GameState gameState;
    private int playerAmount;
    private final int maxPlayer;

    public GameConfig(final int maxPlayer) {
        this.gameState = GameState.STARTUP;
        this.playerAmount = 0;
        this.maxPlayer = maxPlayer;
    }

    public synchronized void addPlayer() {
        playerAmount++;
    }

    public synchronized void removePlayer() {
        playerAmount--;
    }

    public boolean isMaxReached() {
        return playerAmount >= maxPlayer;
    }

    public void nextGameState() {
        if(this.gameState.ordinal()+1 >= GameState.values().length) return;
        this.gameState = GameState.values()[this.gameState.ordinal()+1];
    }

}
