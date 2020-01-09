package models.interfaces;

public interface Hero extends Targetable, Specialable {

    int getStrength();

    void setStrength(int strength);

    int getDexterity();

    void setDexterity(int dexterity);

    int getIntelligence();

    void setIntelligence(int intelligence);

}
