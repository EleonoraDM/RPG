package models.participants;

import models.Config;

public class Boss extends HeroImpl {
    //public static final double BOSS_INDIVIDUAL_REWARD = 50;


    public Boss(String name) {
        super(name);
        this.setHealth(Config.BOSS_HEALTH);
        this.setDamage((int) Config.BOSS_DAMAGE);
        this.setGold(Config.BOSS_GOLD);
    }

}
