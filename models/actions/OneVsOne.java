package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.interfaces.Targetable;

import java.util.List;

public class OneVsOne extends ActionImpl {

    @Override
    public String executeAction(List<Targetable> participants) {

        if (participants.size() != 2) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PARTICIPANTS);
        }
        Targetable firstHero = participants.get(0);
        Targetable secondHero = participants.get(1);

        while (!firstHero.isAlive() || !secondHero.isAlive()) {
            firstHero.attack(secondHero);

            if (secondHero.isAlive()) {
                secondHero.attack(firstHero);
            }
        }
        String winnerName = firstHero.isAlive() ? firstHero.getName() : secondHero.getName();

        return String.format(OutputMessages.FIGHT_END, winnerName);
    }
}
