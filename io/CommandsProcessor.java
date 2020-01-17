package io;

import common.Commands;
import common.ExceptionMessages;
import core.BattlefieldImplementation;
import core.interfaces.Battlefield;

import java.util.Arrays;

public class CommandsProcessor {
    private Battlefield battlefield;

    public CommandsProcessor() {
        this.battlefield = new BattlefieldImplementation();
    }

    public String commandExecution(String command, String[] data) {
        String result = null;

        if (command.toUpperCase().equals(Commands.CREATEPARTICIPANT.name())) {
            String heroName = data[0];
            String heroClassName = data[1];
            result = this.battlefield.createParticipant(heroName, heroClassName);

        } else if (command.toUpperCase().equals(Commands.CREATEACTION.name())) {
            String actionType = data[0];
            String[] participants = Arrays.copyOfRange(data, 1, data.length);
            result = this.battlefield.createAction(actionType, participants);

        } else if (command.toUpperCase().equals(Commands.CREATESPECIAL.name())) {
            String heroName = data[0];
            String specialClassName = data[1];
            //TODO to submit proper return message here, there is no such in the preliminary structure!
            // - unhandled NullPointerExc!!!
            this.battlefield.createSpecial(heroName, specialClassName);

        } else if (command.toUpperCase().equals(Commands.STATPARTICIPANTS.name())) {
            result = this.battlefield.reportParticipants();

        } else if (command.toUpperCase().equals(Commands.STATACTIONS.name())) {
            result = this.battlefield.reportActions();

        } else if (command.toUpperCase().equals(Commands.PEACE.name())) {
            result = Commands.PEACE.name();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMMAND);
        }
        return result;
    }
}
