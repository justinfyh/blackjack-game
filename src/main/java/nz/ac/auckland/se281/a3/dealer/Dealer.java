package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;
import nz.ac.auckland.se281.a3.Player;

/**
 * 
 * You should change this class for Task 2
 *
 */

public class Dealer extends Participant {

	// declare instance fields
	private DealerStrategy strategy;
	private List<Player> players;
	private Hand playerHand;
	private Hand dealerHand;

	// constructor for the Dealer object
	public Dealer(String name, DealerStrategy strategy, List<Player> players) {
		super(name);
		this.strategy = strategy;
		this.players = players;
	}

	@Override
	public Action decideAction(Hand dealerHand) {

		// set the instance of the dealer's hand
		this.dealerHand = dealerHand;

		// set the instance of the player's hand
		playerHand = strategy.getTargetPlayer(players);

		// get the action using the strategy, and both hand instances
		return strategy.decideAction(this.dealerHand, playerHand);
	}

	// getter for the current dealer strategy
	public DealerStrategy getStrategy() {
		return strategy;
	}

	// getter for the dealer's hand
	public Hand getDealerHand() {
		return this.getHand();
	}

	// setter for the player's hand
	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}

	// setter for the dealer strategy
	public void setStrategy(DealerStrategy strategy) {
		this.strategy = strategy;
	}
}
