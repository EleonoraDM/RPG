package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.Config;
import models.interfaces.Targetable;
import models.participants.Boss;

import java.util.List;

public class BossFight extends ActionImpl {

    public BossFight() {
    }

    //TODO to resolve some difficulties here!!!
    @Override
    public String executeAction(List<Targetable> participants) {
        String result = null;

        if (participants.size() < 2) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_ENOUGH_PARTICIPANTS);
        }
        Targetable boss = participants.stream().
                filter(p -> p instanceof Boss).
                findFirst().orElse(null);

        if (boss != null) {

            participants.remove(boss);

            for (Targetable participant : participants) {

                if (participant.isAlive() && boss.isAlive()) {

                    participant.attack(boss);

                    if (boss.isAlive()) {
                        boss.attack(participant);
                    } else {
                        participants.forEach(Targetable::levelUp);
                        participants.forEach(targetable -> targetable.receiveReward(Config.BOSS_INDIVIDUAL_REWARD));
                        boss.giveReward(participant);
                        result = takeBattleResult(boss, participants);
                        break;
                    }

                    if (!participant.isAlive()){

                    }
                }
            }
        }
        return result;
    }

    private String takeBattleResult(Targetable boss, List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();
        sb.append(OutputMessages.HERO_SLAIN).append(System.lineSeparator());
        participants.forEach(targetable -> sb.append(targetable.toString()));
        sb.append(String.format(OutputMessages.REMOVE_DEAD_PARTICIPANTS, boss.getName()));
        return sb.toString();
    }
}
