package core;

import common.ExceptionMessages;
import common.OutputMessages;
import core.interfaces.Battlefield;
import factory.ActionFactoryImpl;
import factory.SpecialFactoryImpl;
import factory.TargetableFactoryImpl;
import factory.interfaces.ActionFactory;
import factory.interfaces.SpecialFactory;
import factory.interfaces.TargetableFactory;
import models.interfaces.Action;
import models.interfaces.Special;
import models.interfaces.Targetable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BattlefieldImplementation implements Battlefield {
    private TargetableFactory targetableFactory;
    private ActionFactory actionFactory;
    private SpecialFactory specialFactory;
    private Map<String, Targetable> heroesOnTheBattleField;
    private List<Targetable> participants;

    public BattlefieldImplementation() {
        this.targetableFactory = new TargetableFactoryImpl();
        this.actionFactory = new ActionFactoryImpl();
        this.specialFactory = new SpecialFactoryImpl();
        this.heroesOnTheBattleField = new LinkedHashMap<>();
        this.participants = new LinkedList<>();
    }

    @Override
    public String createParticipant(String heroName, String heroClassName) {
        String result = null;

        try {
            if (this.heroesOnTheBattleField.containsKey(heroName)) {
                throw new IllegalArgumentException(ExceptionMessages.PARTICIPANT_EXISTS);
            } else {
                Targetable hero = this.targetableFactory.create(heroName, heroClassName);
                this.heroesOnTheBattleField.put(heroName, hero);
                result = String.format(OutputMessages.PARTICIPANT_CREATED, heroClassName, heroName);
            }

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String createAction(String actionName, String... participantNames) {
        String actionResult = null;

        try {
            for (String participantName : participantNames) {

                if (!this.heroesOnTheBattleField.containsKey(participantName)) {
                    throw new IllegalArgumentException
                            (String.format(ExceptionMessages.NON_EXISTING_PARTICIPANT, participantName, actionName));
                } else {
                    Targetable hero = this.heroesOnTheBattleField.get(participantName);
                    this.participants.add(hero);

                    Action action = this.actionFactory.create(actionName, participantNames);
                    actionResult = action.executeAction(this.participants);
                }
            }
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return actionResult;
    }

    @Override
    public void createSpecial(String heroName, String specialName) {
        Targetable hero = this.heroesOnTheBattleField.get(heroName);
        try {
            Special special = this.specialFactory.create(specialName);
            hero.addSpecial(special);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String reportParticipants() {
        return null;
    }

    @Override
    public String reportActions() {
        return null;
    }




















/*    private Map<String, Targetable> participants;
    private List<Action> executedActions;
    ConsoleWriter writer;
    TargetableFactory targetableFactory;

    public BattlefieldImplementation(ConsoleWriter writer) {
        this.executedActions = new ArrayList<>();
        this.participants = new TreeMap<>();
        this.writer = writer;
    }

    @Override
    public void createAction(String actionName, String... participantNames) {
        try {
            Action action = new OneVsOne();

            List<Targetable> actionParticipants = new ArrayList<>();
            for (String name : participantNames){
                if (this.participants.containsKey(name)){
                    actionParticipants.add(this.participants.get(name));
                } else {
                    System.out.println(String.format("%s is not on the battlefield. %s failed.", name, actionName));
                    return;
                }
            }

            System.out.println(action.executeAction(actionParticipants));
            checkForDeadParticipants();
            this.executedActions.add(action);
        } catch (Exception e) {
            System.out.println("Action does not exist.");
        }
    }

    @Override
    public void createParticipant(String name, String className) {

        if (this.participants.containsKey(name)){
            System.out.println("Participant with that name already exists.");
            return;
        }

        Targetable targetable;

        switch (className) {
            case "Warrior":
                targetable = new Warrior();
                targetable.setName(name);
                this.participants.put(targetable.getName(), targetable);
                System.out.println(
                        String.format("%s %s entered the battlefield.",
                                targetable.getClass().getSimpleName(),
                                targetable.getName()));
                break;
            default:
                System.out.println("Participant class does not exist.");
        }
    }

    @Override
    public void createSpecial(String name, String specialName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reportParticipants(){
        if (this.participants.size() < 1) {
            System.out.println("There are no participants on the battlefield.");
            return;
        }

        for (String name : this.participants.keySet()) {
            System.out.println(this.participants.get(name).toString());
            System.out.println("* * * * * * * * * * * * * * * * * * * *");
        }
    }

    @Override
    public void reportActions(){
        if (this.executedActions.size() < 1) {
            System.out.println("There are no actions on the battlefield.");
            return;
        }

        for (Action executedAction : executedActions) {
            System.out.println(executedAction.getClass().getSimpleName());
        }
    }

    private void checkForDeadParticipants(){
        Map<String, Targetable> aliveParticipants = new TreeMap<>();

        for (String name : this.participants.keySet()) {
            if (!this.participants.get(name).isAlive()){
                System.out.println(String.format("%s has been removed from the battlefield.", name));
            }else {
                aliveParticipants.put(name, this.participants.get(name));
            }
        }

        this.participants = aliveParticipants;
    }*/
}
