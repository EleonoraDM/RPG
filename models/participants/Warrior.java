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
    }

    //TODO Should refactor all this and move the whole implementation into the super class
    /*public String attack(Targetable target) {
        if (!this.isAlive()) {
            return this.getName() + " is dead! Cannot attack.";
        }
        if (!target.isAlive()) {
            return target.getName() + " is dead! Cannot be attacked.";
        }
        target.takeDamage(this.getDamage());

        String result = this.getName() + " attacked!";
        if (!target.isAlive()) {
            this.levelUp();
            target.giveReward(this);
            result += String.format(" %s has been slain by %s.", target.getName(), this.getName());
        }
        return result;
    }

    public double getReward() {
        //TODO Implementation
        *//*this.gold = 0;
        return this.gold;*//*
        return 0;
    }

    public void receiveReward(double reward) {
        //TODO Implementation
        *//*this.gold = reward;*//*
    }

    public void takeDamage(double damage) {
        //TODO Implementation
        *//*this.setHealth(damage);*//*
    }

    @Override
    public void giveReward(Targetable targetable) {
        //TODO Implementation
        *//*this.gold = 0;
        targetable.receiveReward(this.gold);*//*
    }

    public void levelUp() {
        //TODO Implementation
        *//*this.setHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER);
        this.setDexterity(this.getDexterity() * 2);
        this.setIntelligence(this.getIntelligence() * 2);
        this.level += 1;*//*
    }

    @Override
    //TODO to check implementation!!!
    public String toString() {
        *//*StringBuilder sb = new StringBuilder();

        sb.append(String.format("  Name: %s | Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("  Health: %.2f | Damage: %.2f", this.getHealth(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("  %d STR | %d DEX | %d INT | %.2f Gold",
                        this.getStrength(), this.getDexterity(), this.getIntelligence(), this.gold));
*//*
        return null;
    }*/
}
