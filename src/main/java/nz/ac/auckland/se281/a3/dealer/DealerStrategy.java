/**
 * 
 */
package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public interface DealerStrategy {

	public Action decideAction(Hand dealerHand, Hand playerHand);

	public Hand getTargetPlayer(List<Player> players);

}
