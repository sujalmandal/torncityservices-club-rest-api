package sujalmandal.torncityservicesclub.web.filter;

import sujalmandal.torncityservicesclub.models.Player;

public class AuthenticatedUser {
    private static ThreadLocal<Player> currentPlayer = new ThreadLocal<>();

    public static Player getPlayer() {
	return currentPlayer.get();
    }

    public static void setPlayer(Player player) {
	currentPlayer.set(player);
    }

}