package de.yy18.nettyserver.server.gamestatus;

import lombok.Getter;

@Getter
public final class GameConfig {

    private GameState gameState;
    private final int maxPlayer;

    public GameConfig(final int maxPlayer) {
        this.gameState = GameState.STARTUP;
        this.maxPlayer = maxPlayer;
    }

    public void nextGameState() {
        if(this.gameState.ordinal()+1 >= GameState.values().length) return;
        this.gameState = GameState.values()[this.gameState.ordinal()+1];
    }

}
