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

	private DealerStrategy strategy;
	private List<Player> players;

	public Dealer(String name, DealerStrategy strategy, List<Player> players) {
		super(name);
		this.strategy = strategy;
		this.players = players;
	}

	@Override
	public Action decideAction(Hand hand) {

		int bet = getHighestBet();

		System.out.println("Highest bet: " + bet);
		return strategy.decideAction(hand);
	}

	private int getHighestBet() {
		// TODO Auto-generated method stub
		int betHigh = 0;
		for (Player player : players) {
			if (player.getHand().getBet() > betHigh) {
				betHigh = player.getHand().getBet();
			}
		}
		return betHigh;
	}

}
