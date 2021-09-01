package de.yy18.nettyserver.server.gamestatus;

import lombok.Getter;

public final class GameConfig {

    @Getter
    private GameState gameState;

    public GameConfig() {
        this.gameState = GameState.STARTUP;
    }

    public void nextGameState() {
        if(this.gameState.ordinal()+1 < GameState.values().length) return;
        this.gameState = GameState.values()[this.gameState.ordinal()+1];
    }

}
