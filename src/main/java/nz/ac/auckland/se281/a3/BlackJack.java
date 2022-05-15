package nz.ac.auckland.se281.a3;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.BotStrategy;
import nz.ac.auckland.se281.a3.bot.StrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.DealerStrategy;
import nz.ac.auckland.se281.a3.dealer.HighestBidderStrategy;
import nz.ac.auckland.se281.a3.dealer.TopWinnerStrategy;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * 
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;

	public BlackJack(Deck deck) {
		this.deck = deck;
		players = new ArrayList<>();
		players.add(new Human("Player1")); // add the Human player first.
	}

	/**
	 * Thi constructor is for testing reasons
	 * 
	 * @param cards
	 */
	protected BlackJack(Card... cards) {
		this(new Deck(cards));

	}

	public BlackJack() {
		this(new Deck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	private String getBotStrategy() {
		System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
		String result = Main.scanner.next();
		while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
			System.out.println("please type \"R\", \"LR\",  \"HR\"");
			result = Main.scanner.next();
		}
		return result;
	}

	// do not change this method
	public void start() {
		initBots();
		initDealer();
		String res;
		int round = 0;
		do {
			round++;
			for (Participant p : players) {
				p.play(deck, round);
			}
			dealer.play(deck, round);
			printAndUpdateResults(round); // after each game print result and update scoreboard
			System.out.println("Do you want to play again?");
			res = Main.scanner.next();
			while (!res.equals("yes") && !res.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				res = Main.scanner.next();
			}
		} while (res.equals("yes"));
		printGameStatistics(); // when the user terminates the game print the statistics
	}

	/**
	 * TODO This method initializes the Bots, you should change this method for
	 * Task1
	 */
	protected void initBots() {
		String botStrategyString = getBotStrategy(); // UNCOMMENT THIS

		BotStrategy strategy = StrategyFactory.createStrategy(botStrategyString);

		System.out.println(botStrategyString);

		Bot bot1 = new Bot("Bot1", strategy);
		Bot bot2 = new Bot("Bot2", strategy);

		// create and set Bots strategy here
		players.add(bot1);
		players.add(bot2);
	}

	/**
	 * TODO This method initializes the Dealer, you should change this method for
	 * Task2
	 */
	protected void initDealer() {
		// set the initial strategy using the Strategy pattern
		DealerStrategy strategy = new HighestBidderStrategy();
		dealer = new Dealer("Dealer", strategy, players);
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	protected void printAndUpdateResults(int round) {

		getRoundWinner(players);

		// update strategy here?
		int bestNetWins = 0;
		Hand topWinner = null;

		// find the player with the highest net wins
		for (Player player : players) {
			if (player.getNetWins() > bestNetWins) {
				bestNetWins = player.getNetWins();
				topWinner = player.getHand();
			}
		}

		// if the highest net wins is 2 or greater, set the strategy to target the top
		// winner
		if (bestNetWins >= 2) {
			DealerStrategy strategy = new TopWinnerStrategy();
			dealer.setPlayerHand(topWinner);
			dealer.setStrategy(strategy);
//			System.out.println("Topwinner: " + topWinner);
//			System.out.println("TWS");
		}
		// otherwise set the strategy to target the highest bidder
		else {
			DealerStrategy strategy = new HighestBidderStrategy();
			dealer.setStrategy(strategy);
//			System.out.println("HBS");
		}

	}

	// function to get the winner of the round
	public void getRoundWinner(List<Player> players) {
//		this.players = players;

		// this does not account for if two players win so both will need a score of +1
//		List<Player> roundWinners;
		int highestScore = 0;

		// loop through the players to get the highest score
		for (Player player : players) {
			if ((player.getHand().getScore() > highestScore) && (player.getHand().getScore() <= 21)) {
				highestScore = player.getHand().getScore();
			}
			// assume that all players lost so -1 from all net wins
			player.setNetWins(player.getNetWins() - 1);
//			System.out.println("rwWins: " + player.getNetWins());
		}

		if (dealer.getDealerHand().getScore() == 21 && highestScore == 21
				&& dealer.getDealerHand().getCards().size() > 2) {
			return;
		}

		// conditionals to adjust net wins
		if ((dealer.getDealerHand().getScore() == 21) || (highestScore == 0)
				|| (dealer.getDealerHand().getScore() >= highestScore && dealer.getDealerHand().getScore() < 21)) {
			return;
		} else if (dealer.getDealerHand().getScore() > 21) {
			for (Player player : players) {
				player.setNetWins(player.getNetWins() + 2);
			}
			return;
		} else {
			// if the dealer busts, all wins
			// adjust for all losing
			for (Player player : players) {
				if ((player.getHand().getScore() == highestScore)) {
					player.setNetWins(player.getNetWins() + 2);
					System.out.println("rwWins: " + player.getNetWins());
				}
			}
		}

		return;

	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {

	}

}
