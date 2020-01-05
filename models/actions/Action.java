package models.actions;

import models.participants.Targetable;

import java.util.List;

public interface Action {
    String executeAction(List<Targetable> participants);
}
