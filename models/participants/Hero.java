package models.participants;

public interface Hero extends Targetable, Specials {

    int getStrength();

    void setStrength(int strength);

    int getDexterity();

    void setDexterity(int dexterity);

    int getIntelligence();

    void setIntelligence(int intelligence);

}
