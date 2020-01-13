package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.interfaces.Targetable;
import models.participants.Necromancer;
import models.participants.Warrior;
import models.participants.Wizard;

import java.util.List;

public class OneVsOne extends ActionImpl {

    public OneVsOne(String... participantNames) {
        super(participantNames);
    }

    @Override
    public String executeAction(List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();

        if (participants.size() != 2) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PARTICIPANTS);
        }
        Targetable firstHero = participants.get(0);
        Targetable secondHero = participants.get(1);

        while (firstHero.isAlive() && secondHero.isAlive()) {
            if (firstHero instanceof Wizard){
                firstHero.triggerSpecial();
            }
            firstHero.attack(secondHero);

            if (firstHero instanceof Necromancer){
                firstHero.triggerSpecial();
            }
            if (secondHero instanceof Warrior){
                secondHero.triggerSpecial();
            }
            if (secondHero.isAlive()) {
                secondHero.attack(firstHero);
            }
        }
        Targetable winner = firstHero.isAlive() ? firstHero : secondHero;

        sb.append(String.format(OutputMessages.FIGHT_END, winner.getName()))
                .append(winner.toString())
                .append(System.lineSeparator());

        return sb.toString();
    }
}
