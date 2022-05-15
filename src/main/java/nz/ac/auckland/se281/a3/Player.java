package nz.ac.auckland.se281.a3;

import java.util.List;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	private int netWins;
	private List<Player> players;

	public Player(String name) {
		super(name);
		netWins = 0;
	}

	public static Player getRoundWinner(List<Player> players) {
//		this.players = players;
		Player roundWinner = players.get(0);
		for (Player player : players) {
			if (player.getHand().getScore() > roundWinner.getHand().getScore() || player.getHand().getScore() <= 21) {
				roundWinner = player;
			}
		}

		roundWinner.netWins++;

		return roundWinner;

	}

	public abstract int makeABet();

}
