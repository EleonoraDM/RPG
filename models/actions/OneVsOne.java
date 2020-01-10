package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.interfaces.Targetable;

import java.util.List;

public class OneVsOne extends ActionImpl {

    public OneVsOne() {
    }

    //TODO to resolve some difficulties here!!!
    @Override
    public String executeAction(List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();

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
        Targetable winner = firstHero.isAlive() ? firstHero : secondHero;

        sb.append(String.format(OutputMessages.FIGHT_END, winner.getName()))
                .append(winner.toString());

        return sb.toString();
    }
}
