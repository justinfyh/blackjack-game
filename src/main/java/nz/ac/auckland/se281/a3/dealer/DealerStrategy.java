/**
 * 
 */
package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public interface DealerStrategy {

	/**
	 * this method decides and returns the action of the dealer, either hit or hold,
	 * depending on the strategy implementation and the two input parameters
	 * 
	 * @param dealerHand hand of the dealer
	 * @param playerHand hand of the player that the dealer is targeting
	 * @return Action either hit or hold
	 */
	public Action decideAction(Hand dealerHand, Hand playerHand);

	/**
	 * this method gets the target player for the dealer to win against using the
	 * list of players and returns the hand of the targeted player based on the
	 * strategy implementation
	 * 
	 * @param players list containing all the players in the game
	 * @return Hand the hand of the player that the dealer is targeting
	 */
	public Hand getTargetPlayer(List<Player> players);

}
