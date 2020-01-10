package models.interfaces;

public interface Specialable {

    void addSpecial(Special special);

    boolean checkForSpecial();

    void removeSpecial(Special special);

    void triggerHeal();

    void triggerToughness();

    void triggerSwiftness();

}
