package models.interfaces;

import java.util.List;

public interface Action {
    String executeAction(List<Targetable> participants);
}
