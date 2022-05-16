package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public interface BotStrategy {

	/**
	 * this method takes input parameter of hand and returns the action either hit
	 * or hold depending on the strategy instance/implementation
	 * 
	 * @param hand the player's hand
	 * @return Action hit or hold
	 */
	Action decideAction(Hand hand);

	/**
	 * this method makes a bet which is returned as an integer and depends on the
	 * strategy's implementation
	 * 
	 * @return int bet amount of player
	 */
	public int makeABet();

}
