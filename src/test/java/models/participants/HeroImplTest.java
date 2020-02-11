package models.participants;

import models.Config;
import models.interfaces.Hero;
import models.interfaces.Special;
import models.specials.Heal;
import models.specials.Swiftness;
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

        this.hero.attack(target);

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
    public void giveReward_ShouldIncreaseTheWinnersGold() {
        double reward = this.hero.getGold();
        double currGold = this.target.getGold();
        double expectedGold = currGold + reward;

        this.hero.giveReward(this.target);

        assertEquals(expectedGold, this.target.getGold(), 0.001);
        assertEquals(0, this.hero.getGold(), 0.001);
    }

    /**
     * Exact trigger times:
     * •	Warrior triggers his special RIGHT AFTER HE RECEIVES DAMAGE and his special's requirements are met
     * •	Wizzard triggers his special RIGHT BEFORE DEALING DAMAGE and his special's requirements are met
     * •	Necromancer triggers his special RIGHT AFTER DEALING DAMAGE and his special's requirements are met.
     * <p>
     * •	Heal - the hero gains health equal to his points of intelligence EVERY TIME the ability is triggered.
     * Trigger happens if the hero's health is BELOW or equal to 50%.
     * <p>
     * •	Toughness - the hero gains strength equal to his points of intelligence
     * as long as his health is BELOW or equal to 50%.
     * This effect is lasts ONLY FOR THE DURATION OF THE BATTLE.
     * <p>
     * •	Swiftness - the hero gains dexterity equal to his intelligence points
     * as long as his health is ABOVE or equal to 50%.
     * This effect is lasts ONLY FOR THE DURATION OF THE BATTLE.
     */

    @Test
    public void triggerSpecial_Heal_ShouldSetTheCorrectHealthIfCurrentHealthIsEqualToTheComparativeOne() {
        Special special = new Heal();
        this.hero.setSpecial(special);

        double comparativeHealth = this.hero.getDefaultHealth() * Config.SPECIALS_TRIGGER;
        this.hero.takeDamage(comparativeHealth);

        double expectedHealth = this.hero.getHealth() + this.hero.getIntelligence();
        this.hero.triggerSpecial();

        assertEquals("Special condition is met - health value is 50% of the default level!",
                expectedHealth, this.hero.getHealth(), 0.001);
    }

    @Test
    public void triggerSpecial_Heal_ShouldSetTheCorrectHealthIfCurrentHealthIsBelowTheComparativeOne() {
        Special special = new Heal();
        this.hero.setSpecial(special);
        this.hero.takeDamage(31.00);

        double expectedHealth = this.hero.getHealth() + this.hero.getIntelligence();
        this.hero.triggerSpecial();

        assertEquals("Special condition is met - health value is BELOW 50% of the default level!",
                expectedHealth, this.hero.getHealth(), 0.001);
    }

    @Test
    public void triggerSpecial_Heal_ShouldNotChangeHealthIfCurrentHealthIsAboveTheComparativeOne() {
        Special special = new Heal();
        this.hero.setSpecial(special);
        this.hero.takeDamage(0.00);

        this.hero.triggerSpecial();

        assertEquals("Special condition is NOT met!",
                this.hero.getDefaultHealth(), this.hero.getHealth(), 0.001);
    }

    @Test
    public void triggerSpecial_Toughness_ShouldSetTheCorrectStrengthIfCurrentHealthIsEqualToTheComparativeOne() {
        Special special = new Toughness();
        this.target.setSpecial(special);

        double comparativeHealth = this.target.getDefaultHealth() * Config.SPECIALS_TRIGGER;
        double expectedStrength = this.target.getStrength() + this.target.getIntelligence();

        this.target.setHealth(comparativeHealth);

        this.target.triggerSpecial();

        assertEquals("Special condition is met - health value is 50% of the default level!",
                expectedStrength, this.target.getStrength(), 0.001);
    }

    @Test
    public void triggerSpecial_Toughness_ShouldSetTheCorrectStrengthIfCurrentHealthIsBelowToTheComparativeOne() {
        Special special = new Toughness();
        this.target.setSpecial(special);

        double expectedStrength = this.target.getStrength() + this.target.getIntelligence();
        this.target.setHealth(10.00);

        this.target.triggerSpecial();

        assertEquals("Special condition is met - health value is BELOW 50% of the default level!",
                expectedStrength, this.target.getStrength(), 0.001);
    }

    @Test
    public void triggerSpecial_Toughness_ShouldNotChangeStrengthIfHeathIsAboveTheComparativeOne() {
        Special special = new Toughness();
        this.target.setSpecial(special);
        int currentStrength = this.target.getStrength();

        this.target.triggerSpecial();

        assertEquals("Special condition is NOT met!",
                currentStrength, this.target.getStrength(), 0.001);
    }

    @Test
    public void triggerSpecial_Swiftness_ShouldSetTheCorrectDexterityValueIfHealthIsEqualToTheComparativeOne() {
        Special special = new Swiftness();
        HeroImpl hero1 = new Necromancer("Necro");
        hero1.setSpecial(special);

        hero1.setHealth(hero1.getHealth() * 0.50);
        int expectedDexterity = hero1.getDexterity() + hero1.getIntelligence();

        hero1.triggerSpecial();

        assertEquals("Special condition is met - health value is 50% of the default level!",
                expectedDexterity, hero1.getDexterity());
    }

    @Test
    public void triggerSpecial_Swiftness_ShouldSetTheCorrectDexterityValueIfHealthIsAboveToTheComparativeOne() {
        Special special = new Swiftness();
        HeroImpl hero1 = new Necromancer("Necro");
        hero1.setSpecial(special);

        hero1.setHealth(35.00);
        int expectedDexterity = hero1.getDexterity() + hero1.getIntelligence();

        hero1.triggerSpecial();

        assertEquals("Special condition is met - health value is ABOVE 50% of the default level!",
                expectedDexterity, hero1.getDexterity());
    }

    @Test
    public void triggerSpecial_Swiftness_ShouldNotChangeDexterityIfHealthIsBelowTheComparativeOne() {
        Special special = new Swiftness();
        HeroImpl hero1 = new Necromancer("Necro");
        hero1.setSpecial(special);

        hero1.setHealth(10.00);
        int expectedDexterity = hero1.getDexterity();

        hero1.triggerSpecial();

        assertEquals("Special condition is met - health value is BELOW 50% of the default level!",
                expectedDexterity, hero1.getDexterity());
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

    @Test
    public void toString_ReturnsTheExpectedMessage() {
        String expected = String.format(
                "  Name: %s | Class: %s%n" +
                        "  Health: %.2f | Damage: %.2f%n" +
                        "  %d STR | %d DEX | %d INT | %.2f Gold",
                this.hero.getName(), this.hero.getClass().getSimpleName(),
                this.hero.getHealth(), this.hero.getDamage(),
                this.hero.getStrength(), this.hero.getDexterity(), this.hero.getIntelligence(), this.hero.getGold()
        );
        assertEquals(expected, this.hero.toString());
    }
}