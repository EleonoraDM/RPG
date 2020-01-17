package models.interfaces;

public interface Specialable {

    Special getSpecial();

    void setSpecial(Special special);

    void deactivateSpecial(Special special);

    void triggerSpecial();

    double getDefaultHealth();

}
