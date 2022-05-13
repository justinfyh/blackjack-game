package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {

	@Override
	public Action play() {
		float randomNumber = new Random().nextFloat();
		if (randomNumber <= 0.5) {
			return Action.HIT;
		}
		return Action.HOLD;
	}

}
