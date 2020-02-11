package models.participants;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BossTest {
    private HeroImpl hero;
    private HeroImpl target;

    @Before
    public void setUp() {
        this.hero = new Boss("Bossy");
        this.target = new Warrior("Warr");
    }

    @Test
    public void attack_ShouldReturnProperMessageIfBossIsDeadBeforeTheAttack() {
        String expected = String.format("%s is dead! Cannot attack.", this.hero.getName());
        this.hero.setHealth(0);

        String actual = this.hero.attack(this.target);

        assertEquals(expected, actual);
    }

    @Test
    public void attack_ShouldReturnProperMessageIfHeroIsDeadBeforeTheAttack() {
        String expected = String.format("%s is dead! Cannot be attacked.", this.target.getName());
        this.target.setHealth(0);

        String actual = this.hero.attack(this.target);

        assertEquals(expected, actual);
    }

    @Test
    public void attack_ReducesTargetsHealthBySuccessfulAttack() {
        double damage = this.hero.getDamage();
        double health = this.target.getHealth();

        this.hero.attack(target);

        assertEquals((health - damage), this.target.getHealth(), 0.001);
    }

    @Test
    public void levelsUp_ShouldRefillBossesHealth() {
        this.hero.setLevel(5);
        this.target.attack(hero);

        this.hero.levelUp();

        assertEquals(6, this.hero.getLevel());
        assertEquals(this.hero.getDefaultHealth(), this.hero.getHealth(), 0.001);
    }

    @Test
    public void receiveReward_ShouldIncreaseBossesGoldOnlyWithTenPercentOfTheTargetsGold() {
        double reward = this.target.getGold() * 0.10;
        double expectedGold = this.hero.getGold() + reward;

        this.hero.receiveReward(this.target.getGold());

        assertEquals(expectedGold, this.hero.getGold(), 0.001);
    }

    @Test
    public void toString_ReturnsTheExpectedFormat() {
        String expected = String.format(
                "  Name: %s | Class: Boss%n" +
                        "Health: %.2f | Damage: %.2f | %.2f Gold ",
                this.hero.getName(), this.hero.getHealth(), this.hero.getDamage(), this.hero.getGold());

        assertEquals(expected, this.hero.toString());
    }

}