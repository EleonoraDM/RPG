package models.interfaces;

public interface Specialable {

    Special getSpecial();

    void setSpecial(Special special);

    void deactivateSpecial();

    void triggerSpecial();

    double getDefaultHealth();

}
