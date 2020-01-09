package core.interfaces;

public interface Battlefield {
    //FIXME Here I changed the return type of all methods from void (as it was given in the preliminary structure)
    // to String, in order to keep the encapsulation and SOLID principals.

    String createAction(String actionName, String... participantNames);

    String createParticipant(String heroName, String heroClassName);

    String createSpecial(String heroName, String specialName);

    String reportParticipants();

    String reportActions();
}
