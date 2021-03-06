package models.participants;

import common.ExceptionMessages;
import common.OutputMessages;
import models.Config;
import models.interfaces.Hero;
import models.interfaces.Special;
import models.interfaces.Targetable;
import models.specials.Heal;
import models.specials.Swiftness;
import models.specials.Toughness;

public abstract class HeroImpl implements Hero {
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
    private Special special;


    protected HeroImpl
            (String name) {
        this.setName(name);
        this.level = LEVEL_ENTRY_POINT;
        this.setGold(Config.HERO_START_GOLD);
        this.isAlive = true;
        this.special = null;
    }

    public int getLevel() {
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
        this.health = health;
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
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_NAME);
        }
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
        if (this.getHealth() <= 0) {
            this.isAlive = false;
        }
        return this.isAlive;
    }

    @Override
    public String attack(Targetable target) {
        String result = "";

        if (!this.isAlive()) {
            return String.format(OutputMessages.DEAD_ATTACKER, this.getName());
        }
        if (!target.isAlive()) {
            return String.format(OutputMessages.DEAD_TARGET, target.getName());
        }
        target.takeDamage(this.getDamage());

        if (!target.isAlive()) {
            if (target instanceof Boss) {
                result = OutputMessages.BOSS_LOST + System.lineSeparator();
            } else {
                result = String.format(OutputMessages.HERO_SLAIN, target.getName(), this.getName())
                        + System.lineSeparator();
            }
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
        this.setHealth(this.getStrength() * Config.HERO_HEALTH_MULTIPLIER);
        this.setDamage(this.getDamage() * Config.LEVEL_UP_MULTIPLIER);
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
    public Special getSpecial() {
        return this.special;
    }

    @Override
    public void setSpecial(Special special) {
        this.special = special;
    }

    @Override
    public void deactivateSpecial() {
        if (this.special != null &&
                (this.special instanceof Toughness || this.special instanceof Swiftness)) {
            this.setSpecial(null);
        }
    }

    @Override
    public void triggerSpecial() {
        if (this.special != null &&
                this.special instanceof Heal &&
                this.getHealth() <= this.getDefaultHealth() * Config.SPECIALS_TRIGGER) {
            this.setHealth(this.getHealth() + this.getIntelligence());
        }
        if (this.special != null &&
                this.special instanceof Toughness &&
                this.getHealth() <= this.getDefaultHealth() * Config.SPECIALS_TRIGGER) {
            this.setStrength(this.getStrength() + this.getIntelligence());
            //TODO This effect is lasts only for the duration of the battle!!!
        }
        if (this.special != null &&
                this.special instanceof Swiftness &&
                this.getHealth() >= this.getDefaultHealth() * Config.SPECIALS_TRIGGER) {
            this.setDexterity(this.getDexterity() + this.getIntelligence());
            //TODO This effect is lasts only for the duration of the battle!!!
        }
    }

    @Override
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
