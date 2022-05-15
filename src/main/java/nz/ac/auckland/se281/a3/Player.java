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
	private boolean winStatus;

	public boolean isWinStatus() {
		return winStatus;
	}

	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}

	public Player(String name) {
		super(name);
		netWins = 0;
	}

	public int getNetWins() {
		return netWins;
	}

	public void setNetWins(int netWins) {
		this.netWins = netWins;
	}

	public abstract int makeABet();

}
