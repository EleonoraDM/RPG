package models.participants;

import common.OutputMessages;
import models.Config;
import models.interfaces.Targetable;

public class Boss extends HeroImpl {

    public Boss(String name) {
        super(name);
        this.setHealth(Config.BOSS_HEALTH);
        this.setDamage((int) Config.BOSS_DAMAGE);
        this.setGold(Config.BOSS_GOLD);
    }

    @Override
    public String attack(Targetable target) {
        String result = "";

        if (!this.isAlive()) {
            result = String.format(OutputMessages.DEAD_ATTACKER, this.getName());
        }
        if (!target.isAlive()) {
            result = String.format(OutputMessages.DEAD_TARGET, target.getName());
        }
        target.takeDamage(this.getDamage());

        return result;
    }

    @Override
    public void levelUp() {
        this.setHealth(Config.BOSS_HEALTH);
        this.setLevel(this.getLevel() + 1);
    }

    @Override
    public void receiveReward(double reward) {
        super.receiveReward(reward * 0.10);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("  Name: %s | Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("Health: %.2f | Damage: %.2f | %.2f Gold ",
                        this.getHealth(), this.getDamage(), this.getGold() ));

        return sb.toString();
    }

    @Override
    public double getDefaultHealth() {
        return Config.BOSS_HEALTH;
    }
}
