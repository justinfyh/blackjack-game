package nz.ac.auckland.se281.a3.bot;

public class StrategyFactory {

	public static BotStrategy createStrategy(String botStrategyString) {
		switch (botStrategyString) {
		case "R":
			return new RandomStrategy();

		case "LR":
			return new LowRiskStrategy();

		case "HR":
			return new HighRiskStrategy();

		default:
			System.err.println("wrong strategy type");
			System.exit(0);
		}

		return null;

	}

}
