package models.actions;

import models.interfaces.Action;

public abstract class ActionImpl implements Action {
    private String[] participantNames;

    protected ActionImpl(String... participantNames) {
        this.participantNames = participantNames;
    }

}
