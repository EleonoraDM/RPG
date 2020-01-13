package models.actions;

import common.ExceptionMessages;
import common.OutputMessages;
import models.Config;
import models.interfaces.Targetable;
import models.participants.Boss;
import models.participants.Necromancer;
import models.participants.Warrior;
import models.participants.Wizard;

import java.util.List;

public class BossFight extends ActionImpl {

    public BossFight(String... participantNames) {
        super(participantNames);
    }

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
                    if (participant instanceof Wizard){
                        participant.triggerSpecial();
                    }
                    participant.attack(boss);

                    if (participant instanceof Necromancer){
                        participant.triggerSpecial();
                    }
                    if (boss.isAlive()) {
                        boss.attack(participant);
                        if (participant instanceof Warrior){
                            participant.triggerSpecial();
                        }
                    } else {
                        participants.forEach(Targetable::levelUp);
                        participants.forEach(targetable -> targetable.receiveReward(Config.BOSS_INDIVIDUAL_REWARD));
                        boss.giveReward(participant);
                        result = takeBattleReport(boss, participants);
                        break;
                    }
                }
                if (!boss.isAlive()){
                    break;
                }
            }
            if (participants.stream().noneMatch(Targetable::isAlive)){
                result = OutputMessages.BOSS_WON;
            }
        }
        return result;
    }

    private String takeBattleReport(Targetable boss, List<Targetable> participants) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(OutputMessages.BOSS_LOST, boss.getName())).
                append(System.lineSeparator());

        participants.stream().filter(Targetable::isAlive).forEach(t -> sb.append(t.toString()));

        return sb.toString();
    }
}
