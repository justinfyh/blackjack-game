package nz.ac.auckland.se281.a3.bot;

public class StrategyFactory {

	/**
	 * creates an instance of the strategy that will be used by the bots depending
	 * on the user input parameter
	 * 
	 * @param botStrategyString user string input
	 * @return reference to the chosen strategy instance
	 */
	public static BotStrategy createStrategy(String botStrategyString) {
		// create an instance of a strategy depending on the user input
		switch (botStrategyString) {
		case "R":
			return new RandomStrategy();

		case "LR":
			return new LowRiskStrategy();

		case "HR":
			return new HighRiskStrategy();

		default:
			System.err.println("not a strategy type");
			System.exit(0);
		}
		return null;
	}
}
