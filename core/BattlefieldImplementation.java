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
    private static final String STRING_SEPARATOR = "* * * * * * * * * * * * * * * * * * * *";

    private TargetableFactory targetableFactory;
    private ActionFactory actionFactory;
    private SpecialFactory specialFactory;
    private Map<String, Targetable> heroesOnTheBattleField;
    private List<String> actionNames;

    public BattlefieldImplementation() {
        this.targetableFactory = new TargetableFactoryImpl();
        this.actionFactory = new ActionFactoryImpl();
        this.specialFactory = new SpecialFactoryImpl();
        this.heroesOnTheBattleField = new LinkedHashMap<>();
        this.actionNames = new ArrayList<>();
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
        StringBuilder sb = new StringBuilder();

        try {
            verifyParticipants(actionName, participantNames);
            Action action = this.actionFactory.create(actionName, participantNames);
            this.actionNames.add(actionName);

            List<Targetable> participants = getParticipants(participantNames);
            String actionResult = action.executeAction(participants);
            sb.append(actionResult);

            String deadHeroesReport = removeDeadHeroes(this.heroesOnTheBattleField.values());
            sb.append(deadHeroesReport);

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void verifyParticipants(String actionName, String[] participantNames) {
        for (String participantName : participantNames) {
            if (!this.heroesOnTheBattleField.containsKey(participantName)) {
                throw new IllegalArgumentException
                        (String.format(ExceptionMessages.NON_EXISTING_PARTICIPANT, participantName, actionName));
            }
        }
    }

    private List<Targetable> getParticipants(String[] participantNames) {
        List<Targetable> participants = new ArrayList<>();

        for (String name : participantNames) {
            this.heroesOnTheBattleField.values().
                    stream().
                    filter(t -> t.getName().equals(name)).
                    findFirst().ifPresent(participants::add);
        }
        return participants;
    }

    private String removeDeadHeroes(Collection<Targetable> heroes) {
        StringBuilder sb = new StringBuilder();
        Iterator<Targetable> iterator = heroes.iterator();

        while (iterator.hasNext()) {
            Targetable hero = iterator.next();

            if (!hero.isAlive()) {
                sb.append(String.format(OutputMessages.REMOVE_DEAD_PARTICIPANTS, hero.getName()));
                sb.append(System.lineSeparator());
                heroes.remove(hero);
            }
        }
        return sb.toString();
    }

    @Override
    public void createSpecial(String heroName, String specialName) {
        Targetable hero = this.heroesOnTheBattleField.get(heroName);

        try {
            Special special = this.specialFactory.createSpecial(specialName);
            hero.setSpecial(special);
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
        StringBuilder sb = new StringBuilder();

        if (this.heroesOnTheBattleField.isEmpty()) {
            sb.append(OutputMessages.EMPTY_BATTLEFIELD);
        } else {
            this.heroesOnTheBattleField.
                    values().
                    forEach(targetable -> sb.
                            append(targetable.toString()).append(System.lineSeparator()).
                            append(STRING_SEPARATOR).append(System.lineSeparator()));
        }
        return sb.toString();
    }

    @Override
    public String reportActions() {
        return this.actionNames.isEmpty()
                ? OutputMessages.NO_ACTIONS : String.join("\n", this.actionNames);
    }

}
