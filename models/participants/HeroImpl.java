package models.participants;

import models.Config;

public abstract class HeroImpl implements Hero {
    private static final int LEVEL_ENTRY_POINT = 1;

    private String name;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int level; //Starting from 1
    private double health; //Strength * 10
    private int damage; //Different for each type of hero!!!
    private double gold; //HERO_START_GOLD = 200.0;
    private boolean isAlive;


    protected HeroImpl
            (String name) {
        this.name = name;//FIXME Should I use the setter here for verification???
        this.level = LEVEL_ENTRY_POINT;
        this.setHealth(Config.HERO_HEALTH_MULTIPLIER);
        this.setGold(Config.HERO_START_GOLD);
        this.isAlive = true;
    }

    protected void setGold(double gold) {
        this.gold = gold;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void setHealth(double health) {
        this.health = this.getStrength() * health;

    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getDexterity() {
        return this.dexterity;
    }

    @Override
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public String attack(Targetable target) {
        //TODO Implementation
        return null;
    }

    @Override
    public void takeDamage(double damage) {
    //TODO Implementation
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public double getHealth() {
        return this.health;
    }

    @Override
    public double getGold() {
        return this.gold;
    }

    @Override
    public void giveReward(Targetable targetable) {
    //TODO Implementation
    }

    @Override
    public void receiveReward(double reward) {
    //TODO Implementation
    }

    @Override
    public void levelUp() {
    //TODO Implementation
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }
}
