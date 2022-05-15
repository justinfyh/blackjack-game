package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	private int netWins;
	private int numLosses;
	private boolean winStatus;
	private int numWins;

	// constructor
	public Player(String name) {
		super(name);
		netWins = 0;
		numWins = 0;
	}

	public int getNumWins() {
		return numWins;
	}

	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	public int getNumLosses() {
		return numLosses;
	}

	public void setNumLosses(int numLosses) {
		this.numLosses = numLosses;
	}

	public boolean isWinStatus() {
		return winStatus;
	}

	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}

	public int getNetWins() {
		return netWins;
	}

	public void setNetWins(int netWins) {
		this.netWins = netWins;
	}

	public abstract int makeABet();

}
