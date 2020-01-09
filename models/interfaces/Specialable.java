package models.interfaces;

public interface Specialable {

    void addSpecial(Special special);

    boolean checkForSpecialAbility();

    void triggerHeal();

    void triggerToughness();

    void triggerSwiftness();

}
