package models.participants;

import models.Config;

public class Wizard extends HeroImpl {
    //DAMAGE = (Intelligence * 5) + Dexterity
    private static final int WIZARD_DAMAGE_MODIFIER = 5;


    public Wizard(String name) {
        super(name);
        this.setStrength(Config.WIZARD_BASE_STRENGTH);
        this.setDexterity(Config.WIZARD_BASE_DEXTERITY);
        this.setIntelligence(Config.WIZARD_BASE_INTELLIGENCE);
        this.setDamage((Config.WARRIOR_BASE_INTELLIGENCE * WIZARD_DAMAGE_MODIFIER) + Config.WIZARD_BASE_DEXTERITY);
    }
}
