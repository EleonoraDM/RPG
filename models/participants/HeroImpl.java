package models.participants;

import common.OutputMessages;
import models.Config;
import models.interfaces.Hero;
import models.interfaces.Special;
import models.interfaces.Targetable;

import java.util.ArrayList;
import java.util.List;

public class HeroImpl implements Hero {
    private static final int LEVEL_ENTRY_POINT = 1;

    private String name;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int level; //Starting from 1
    private double health; //Strength * 10
    private double damage; //Different for each type of hero!!!
    private double gold; //HERO_START_GOLD = 200.0;
    private boolean isAlive;
    private List<Special> specials;


    protected HeroImpl
            (String name) {
        this.name = name;//FIXME Should I use the setter here for verification???
        this.level = LEVEL_ENTRY_POINT;
        this.setHealth(Config.HERO_HEALTH_MULTIPLIER);
        this.setGold(Config.HERO_START_GOLD);
        this.isAlive = true;
        this.specials = new ArrayList<>();
    }

    protected int getLevel() {
        return this.level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected void setGold(double gold) {
        this.gold = gold;
    }

    protected void setDamage(double damage) {
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
    public boolean isAlive() {
        return this.isAlive;
    }

    public void setAlive() {
        this.isAlive = false;
    }

    @Override
    public String attack(Targetable target) {

        if (!this.isAlive()) {
            return String.format(OutputMessages.DEAD_ATTACKER, this.getName());
        }
        if (!target.isAlive()) {
            return String.format(OutputMessages.DEAD_TARGET, target.getName());
        }
        target.takeDamage(this.getDamage());
        String result = String.format(OutputMessages.ATTACK_SUCCESSFUL, this.getName());

        if (!target.isAlive()) {
            target.giveReward(this);
            this.levelUp();
            result += String.format(OutputMessages.TARGET_DEFEATED, target.getName(), this.getName());
        }
        return result;
    }

    @Override
    public void takeDamage(double damage) {
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public void levelUp() {
        this.setStrength(this.getStrength() * Config.LEVEL_UP_MULTIPLIER);
        this.setDexterity(this.getDexterity() * Config.LEVEL_UP_MULTIPLIER);
        this.setIntelligence(this.getIntelligence() * Config.LEVEL_UP_MULTIPLIER);
        this.setHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER);//FIXME not sure about this!
        this.level++;
    }

    @Override
    public void giveReward(Targetable targetable) {
        targetable.receiveReward(this.getGold());
        this.setGold(0);
    }

    @Override
    public void receiveReward(double reward) {
        this.setGold(this.getGold() + reward);
    }

    @Override
    public void addSpecial(Special special) {
        this.specials.add(special);
    }

    @Override
    public boolean checkForSpecialAbility() {
        return this.specials.size() > 0;
    }

    @Override
    public void triggerHeal() {
        if (this.getHealth() == this.getHealth() * Config.SPECIALS_TRIGGER) {
            this.setHealth(this.getIntelligence());
        }
    }

    @Override
    public void triggerToughness() {
        if (this.getHealth() == this.getHealth() * Config.SPECIALS_TRIGGER) {
            this.setStrength(this.getIntelligence());
        }
        //FIXME This effect is lasts only for the duration of the battle!!!WTF
    }

    @Override
    public void triggerSwiftness() {
        if (this.getHealth() == this.getHealth() * Config.SPECIALS_TRIGGER) {
            this.setDexterity(this.getIntelligence());
        }
        //FIXME This effect is lasts only for the duration of the battle!!!WTF
    }


    @Override
    //TODO to check the implementation!!!
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("  Name: %s | Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("  Health: %.2f | Damage: %.2f", this.getHealth(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("  %d STR | %d DEX | %d INT | %.2f Gold",
                        this.getStrength(), this.getDexterity(), this.getIntelligence(), this.getGold()));
        return sb.toString();
    }
}
