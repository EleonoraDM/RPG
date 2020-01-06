package models.actions;

import models.participants.Targetable;

import java.util.List;

public abstract class ActionImpl implements Action {

    protected ActionImpl() {
    }

    @Override
    public String executeAction(List<Targetable> participants) {
        return null;
    }
}
