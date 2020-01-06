package models.actions;

import models.interfaces.Action;
import models.interfaces.Targetable;

import java.util.List;

public abstract class ActionImpl implements Action {

    protected ActionImpl() {
    }

    @Override
    public String executeAction(List<Targetable> participants) {
        return null;
    }
}
