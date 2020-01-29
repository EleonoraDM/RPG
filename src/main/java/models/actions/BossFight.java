package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.Config;
import models.interfaces.Targetable;
import models.participants.Boss;
import models.participants.Necromancer;
import models.participants.Warrior;
import models.participants.Wizard;

import java.util.Comparator;
import java.util.List;

public class BossFight extends ActionImpl {

    public BossFight(String... participantNames) {
        super(participantNames);
    }

    @Override
    public String executeAction(List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();

        Targetable boss = participants.get(0);

        if (boss instanceof Boss) {
            participants.remove(boss);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BOSS);
        }

        if (participants.size() < 2) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_PARTICIPANTS);
        }

        while (boss.isAlive() && !areAllDead(participants)) {

            for (Targetable participant : participants) {

                if (participant.isAlive() && boss.isAlive()) {
                    if (participant instanceof Wizard) {
                        participant.triggerSpecial();
                    }
                    String partAttackResult = participant.attack(boss);
                    sb.append(partAttackResult);

                    if (participant instanceof Necromancer) {
                        participant.triggerSpecial();
                    }
                    if (boss.isAlive()) {
                        String bossAttackResult = boss.attack(participant);
                        sb.append(bossAttackResult);

                        if (participant instanceof Warrior) {
                            participant.triggerSpecial();
                        }
                    } else {
                        participants.forEach(Targetable::levelUp);
                        participants.forEach(targetable -> targetable.receiveReward(Config.BOSS_INDIVIDUAL_REWARD));
                        boss.giveReward(participant);
                        String battleReport = takeBattleReport(participants);
                        sb.append(battleReport);
                        break;
                    }
                }
                if (!boss.isAlive()) {
                    break;
                }
            }
        }
        participants.forEach(p -> p.deactivateSpecial(p.getSpecial()));

        if (areAllDead(participants)) {
            sb.append(OutputMessages.BOSS_WON);
        }
        return sb.toString();
    }

    private boolean areAllDead(List<Targetable> participants) {
        return participants.stream().noneMatch(Targetable::isAlive);
    }

    private String takeBattleReport(List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();

        participants.
                stream().
                filter(Targetable::isAlive).
                sorted(Comparator.comparing(Targetable::getName)).
                forEach(t -> sb.append(t.toString()).append(System.lineSeparator()));

        return sb.toString();
    }
}
