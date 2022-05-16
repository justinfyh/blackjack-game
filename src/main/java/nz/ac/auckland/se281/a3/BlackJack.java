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
	/**
	 * gets the specified bot strategy from the user and creates a strategy instance
	 * of the chosen strategy, then creates instances of the bots and adds them onto
	 * the list array
	 */
	protected void initBots() {
		// get the user input for the bot strategy choice
		String botStrategyString = getBotStrategy();

		// create new strategy instance using the factory
		BotStrategy strategy = StrategyFactory.createStrategy(botStrategyString);

		// create new instances of bots
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
	/**
	 * creates instance of the initial dealer strategy and creates the dealer
	 * instance
	 */
	protected void initDealer() {
		// set the initial strategy
		DealerStrategy strategy = new HighestBidderStrategy();

		// create instance of the dealer
		dealer = new Dealer("Dealer", strategy, players);
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	/**
	 * updates the wins and losses for each player, then updates the dealer strategy
	 * using the highest net wins. prints the stats for the round
	 * 
	 * @param round current round number
	 */
	protected void printAndUpdateResults(int round) {

		// update the wins and losses of the players
		updateWinLoss(players);

		// initialise instance fields
		int bestNetWins = 0;
		Hand topWinner = null;

		// find the player with the highest net wins
		for (Player player : players) {
			if (player.getNumWins() - player.getNumLosses() > bestNetWins) {
				bestNetWins = player.getNumWins() - player.getNumLosses();
				topWinner = player.getHand();
			}
		}

		// if the highest net wins is 2 or greater, set the strategy to target the top
		// winner
		if (bestNetWins >= 2) {
			DealerStrategy strategy = new TopWinnerStrategy();
			dealer.setPlayerHand(topWinner);
			dealer.setStrategy(strategy);
		}
		// otherwise set the strategy to target the highest bidder
		else {
			DealerStrategy strategy = new HighestBidderStrategy();
			dealer.setStrategy(strategy);
		}

		// PRINT ROUND STATS
		for (Player player : players) {
			String winLose = "lost";
			if (player.isWinStatus()) {
				winLose = "won";
			}
			System.out.println("Round " + round + ": " + player.getName() + " " + winLose + " "
					+ player.getHand().getBet() + " chips");
		}
	}

	// function to get the winner of the round
	/**
	 * using the players list, updates each player's wins and losses based on their
	 * scores and the dealer's score
	 * 
	 * @param players list containing all instances of the players
	 */
	public void updateWinLoss(List<Player> players) {

		// initialise instance fields
		int highestScore = 0;

		// loop through the players to get the highest score
		for (Player player : players) {
			if ((player.getHand().getScore() > highestScore) && (player.getHand().getScore() <= 21)) {
				highestScore = player.getHand().getScore();
			}
			// assume that all players lost so +1 to all losses and set win status to false
			player.setNumLosses(player.getNumLosses() + 1);
			player.setWinStatus(false);
		}

		// conditionals to adjust wins and losses
		// any cases where the dealer wins/players get busted, exit the update
		if ((dealer.getDealerHand().getScore() == 21 && highestScore != 21) || (highestScore == 0)
				|| (dealer.getDealerHand().getScore() >= highestScore && dealer.getDealerHand().getScore() < 21)
				|| (dealer.getDealerHand().getScore() == 21 && highestScore == 21
						&& dealer.getDealerHand().getCards().size() > 2)) {
			return;
		} else if (dealer.getDealerHand().getScore() > 21) {
			// if the dealer is busted, then all players win
			for (Player player : players) {
				// except for players who are busted
				if (player.getHand().getScore() <= 21) {
					player.setNumLosses(player.getNumLosses() - 1);
					player.setNumWins(player.getNumWins() + 1);
					player.setWinStatus(true);
				}
			}
			return;
		} else {
			// otherwise, player(s) with highest score wins
			for (Player player : players) {
				if ((player.getHand().getScore() == highestScore) && (dealer.getDealerHand().getScore() != 21)) {
					player.setNumLosses(player.getNumLosses() - 1);
					player.setNumWins(player.getNumWins() + 1);
					player.setWinStatus(true);
				}
			}
		}

		return;

	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	/**
	 * prints the end of game statistics once the game has been terminated
	 */
	protected void printGameStatistics() {
		// for all players, print their end of game statistics
		for (Player player : players) {
			System.out.println(player.getName() + " won " + player.getNumWins() + " times and lost "
					+ player.getNumLosses() + " times");
		}
	}

}
