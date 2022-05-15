package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {

	// declare private instance field for strategy object
	private BotStrategy strategy;

	// constructor for the Bot object
	public Bot(String name, BotStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		// return the action depending on the strategy chosen
		return strategy.decideAction(hand);
	}

	@Override
	public int makeABet() {
		// return a bet depending on the strategy chosen
		return strategy.makeABet();
	}
}
