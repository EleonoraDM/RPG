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
            if (firstHero instanceof Wizard) {
                firstHero.triggerSpecial();
            }
            String firstVsSecond = performAttack(firstHero, secondHero);
            sb.append(firstVsSecond);

            if (firstHero instanceof Necromancer) {
                firstHero.triggerSpecial();
            }
            if (secondHero instanceof Warrior) {
                secondHero.triggerSpecial();
            }
            if (secondHero.isAlive()) {
                String secondVsFirst = performAttack(secondHero, firstHero);
                sb.append(secondVsFirst);
            }
        }
        firstHero.deactivateSpecial();
        secondHero.deactivateSpecial();

        Targetable winner = firstHero.isAlive() ? firstHero : secondHero;

        if (winner.getName().equals(firstHero.getName())) {
            secondHero.giveReward(firstHero);
            firstHero.levelUp();
        } else {
            firstHero.giveReward(secondHero);
            secondHero.levelUp();
        }
        sb.append(String.format(OutputMessages.FIGHT_END, winner.getName()))
                .append(System.lineSeparator())
                .append(winner.toString())
                .append(System.lineSeparator());

        return sb.toString();
    }

    private String performAttack(Targetable attacker, Targetable defender) {
        StringBuilder sb = new StringBuilder();
        String attackResult = attacker.attack(defender);
        sb.
                append(String.format(OutputMessages.ATTACK_SUCCESSFUL, attacker.getName())).
                append(attackResult).
                append(System.lineSeparator());
        return sb.toString();
    }
}
