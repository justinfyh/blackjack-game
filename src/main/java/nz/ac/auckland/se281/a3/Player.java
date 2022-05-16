package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	// declare instance fields
	private int numWins;
	private int numLosses;
	private boolean winStatus;

	// constructor for the Player object
	public Player(String name) {
		super(name);
		numWins = 0;
		numLosses = 0;
	}

	// getter for the number of wins
	public int getNumWins() {
		return numWins;
	}

	// setter for the number of wins
	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	// getter for the number of losses
	public int getNumLosses() {
		return numLosses;
	}

	// setter for the number of losses
	public void setNumLosses(int numLosses) {
		this.numLosses = numLosses;
	}

	// getter for the win status
	public boolean isWinStatus() {
		return winStatus;
	}

	// setter for the win status
	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}

	/**
	 * this method makes a bet using the strategy instance which is returned as an
	 * integer and depends on the strategy's implementation
	 * 
	 * @return int bet amount of player
	 */
	public abstract int makeABet();
}
