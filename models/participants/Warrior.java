package models.participants;

import models.Config;

public class Warrior extends HeroImpl {
    //DAMAGE = (Strength * 2) + Dexterity
    private static final int WARRIOR_DAMAGE_MODIFIER = 2;

    public Warrior(String name) {
        super(name);
        this.setStrength(Config.WARRIOR_BASE_STRENGTH);
        this.setDexterity(Config.WARRIOR_BASE_DEXTERITY);
        this.setIntelligence(Config.WARRIOR_BASE_INTELLIGENCE);
        this.setDamage((Config.WARRIOR_BASE_STRENGTH * WARRIOR_DAMAGE_MODIFIER) + Config.WARRIOR_BASE_DEXTERITY);
        super.setHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER);
    }
}
