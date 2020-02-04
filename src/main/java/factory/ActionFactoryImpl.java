package factory;

import common.ExceptionMessages;
import factory.interfaces.ActionFactory;
import models.actions.BossFight;
import models.actions.OneVsOne;
import models.interfaces.Action;

import java.lang.reflect.InvocationTargetException;

public class ActionFactoryImpl implements ActionFactory {

    @Override
    public Action create(String actionName, String... participantNames)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {

        Action action = null;

        switch (actionName) {
            case "BossFight":
                action = BossFight.class.
                        getDeclaredConstructor(String[].class).
                        newInstance(new Object[]{participantNames});
                break;
            case "OneVsOne":
                action = OneVsOne.class.
                        getDeclaredConstructor(String[].class).
                        newInstance(new Object[]{participantNames});
                break;
            default:
                throw new ClassNotFoundException(ExceptionMessages.FIGHT_FAILED);
        }
        return action;
    }
}
