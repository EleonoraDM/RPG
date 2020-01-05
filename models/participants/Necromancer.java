package models.participants;

import models.Config;

public class Necromancer extends HeroImpl {
    private static final int NECROMANCER_DAMAGE_MODIFIER = 2;
    //DAMAGE = (Intelligence * 2) + (Dexterity * 2) + (Strength * 2)


    public Necromancer(String name) {
        super(name);
        this.setStrength(Config.WARRIOR_BASE_STRENGTH);
        this.setDexterity(Config.NECROMANCER_BASE_DEXTERITY);
        this.setIntelligence(Config.NECROMANCER_BASE_INTELLIGENCE);
        this.setDamage(
                (Config.NECROMANCER_BASE_INTELLIGENCE * NECROMANCER_DAMAGE_MODIFIER)
                        + (Config.NECROMANCER_BASE_DEXTERITY * NECROMANCER_DAMAGE_MODIFIER)
                        + (Config.NECROMANCER_BASE_STRENGTH * NECROMANCER_DAMAGE_MODIFIER));
    }
}
