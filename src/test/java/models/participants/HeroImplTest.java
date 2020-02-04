package models.participants;

import models.interfaces.Hero;
import models.interfaces.Special;
import models.specials.Heal;
import models.specials.Toughness;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class HeroImplTest {
    private HeroImpl hero;
    private HeroImpl target;

    @Rule
    public ExpectedException exc = ExpectedException.none();

    @Before
    public void setUp() {
        this.hero = new Warrior("Thorn");
        this.target = new Wizard("Rosemary");
    }

    @Test
    public void createHeroWithNullName_ShouldThrowNullPointerException() {
        exc.expect(NullPointerException.class);
        exc.expectMessage("Heroes name can not be null or empty!");
        Hero hero = new Necromancer(null);
    }

    @Test
    public void createHeroWithEmptyName_ShouldThrowNullPointerException() {
        exc.expect(NullPointerException.class);
        exc.expectMessage("Heroes name can not be null or empty!");
        Hero hero = new Necromancer("");
    }

    @Test
    public void newlyCreatedHeroShouldStartFromFirstLevel() {
        assertEquals(1, this.hero.getLevel());
    }

    @Test
    public void newlyCreatedHeroShouldHavePreciselyAmountOfGold() {
        assertEquals(200, this.hero.getGold(), 0.001);
    }

    @Test
    public void newlyCreatedHeroShouldBeAlive() {
        assertTrue(this.hero.isAlive());
    }

    @Test
    public void newlyCreatedHeroShouldNotHaveSpecial() {
        assertNull(this.hero.getSpecial());
    }

    @Test
    public void isAlive_ReturnsTrueIfHeroesHealthIsPositiveValue() {
        this.hero.setHealth(14.00);
        assertTrue(this.hero.isAlive());
    }

    @Test
    public void isAlive_ReturnsFalseIfHeroesHealthIsBelowZero() {
        this.hero.setHealth(-14.00);
        assertFalse(this.hero.isAlive());
    }

    @Test
    public void isAlive_ReturnsFalseIfHeroesHealthIsEqualToZero() {
        this.hero.setHealth(0.00);
        assertFalse(this.hero.isAlive());
    }

    @Test
    public void attack_ReturnsProperMessageIfAttackerIsDeadBeforeAttack() {
        this.hero.setHealth(0.00);
        String result = this.hero.attack(this.target);
        Assert.assertEquals(String.format("%s is dead! Cannot attack.", this.hero.getName()), result);
    }

    @Test
    public void attack_ReturnsProperMessageIfTargetIsDeadBeforeAttack() {
        this.target.setHealth(0.00);
        String result = this.hero.attack(this.target);
        Assert.assertEquals(String.format("%s is dead! Cannot be attacked.", this.target.getName()), result);
    }

    @Test
    public void attack_IfAttackIsSuccessfulTargetsHealthShouldBeReduced() {
        double damage = this.hero.getDamage();
        double health = this.target.getHealth();

        this.target.takeDamage(damage);
        assertEquals((health - damage), this.target.getHealth(), 0.001);
    }

    @Test
    public void attack_ReturnsProperMessageIfBossIsDeadAfterAttack() {
        Boss boss = new Boss("Bossy");
        boss.setHealth(10.00);
        String result = this.hero.attack(boss);

        assertEquals("Boss has been slain by: " + System.lineSeparator(), result);
    }

    @Test
    public void attack_ReturnsProperMessageIfTargetIsDeadAfterAttack() {
        this.target.setHealth(10.00);
        String result = this.hero.attack(this.target);

        assertEquals(String.format(" %s has been slain by %s.",
                this.target.getName(), this.hero.getName()) + System.lineSeparator(), result);
    }

    @Test
    public void giveReward_ShouldIncreaseTheVictorsGold() {
        double reward = this.hero.getGold();
        double currGold = this.target.getGold();
        double expectedGold = currGold + reward;

        this.hero.giveReward(this.target);

        assertEquals(expectedGold, this.target.getGold(), 0.001);
        assertEquals(0, this.hero.getGold(), 0.001);
    }

    /**
     * ONLY Toughness and Swiftness should be deactivated after battle completion!
     */
    @Test
    public void deactivateSpecial_ShouldSetActiveSpecialToNull() {
        Special special = new Toughness();
        this.hero.setSpecial(special);
        this.hero.deactivateSpecial();

        assertNull(this.hero.getSpecial());
    }

    @Test
    public void deactivateSpecial_InCaseOfHealShouldNotSetSpecialToNull() {
        Special special = new Heal();
        this.hero.setSpecial(special);
        this.hero.deactivateSpecial();

        assertNotNull(this.hero.getSpecial());
    }


}