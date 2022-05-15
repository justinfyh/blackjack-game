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
	private Hand playerHand;
	private Hand dealerHand;

	public Dealer(String name, DealerStrategy strategy, List<Player> players) {
		super(name);
		this.strategy = strategy;
		this.players = players;
	}

	@Override
	public Action decideAction(Hand dealerHand) {

		this.dealerHand = dealerHand;

		if (strategy instanceof HighestBidderStrategy) {
			playerHand = getHighestBet();
		} else {
			playerHand = getTopWinner();
		}

//		System.out.println("Highest bet: " + hand);

		return strategy.decideAction(dealerHand, playerHand);
	}

	public void setPlayerHand(Hand playerHand) {
		this.playerHand = playerHand;
	}

	public void setStrategy(DealerStrategy strategy) {
		this.strategy = strategy;
	}

	public DealerStrategy getStrategy() {
		return strategy;
	}

	private Hand getHighestBet() {
		// TODO Auto-generated method stub
		int betHigh = 0;
		Hand hand = null;
		for (Player player : players) {
			if (player.getHand().getBet() > betHigh) {
				betHigh = player.getHand().getBet();
				hand = player.getHand();
			}
		}
		return hand;
	}

	private Hand getTopWinner() {
		int bestNetWins = 0;
		Hand topWinner = null;
		for (Player player : players) {
			if (player.getNetWins() > bestNetWins) {
				bestNetWins = player.getNetWins();
				topWinner = player.getHand();
			}
		}
		return topWinner;
	}

	public Hand getDealerHand() {
		// TODO Auto-generated method stub
		return this.getHand();
	}

}
