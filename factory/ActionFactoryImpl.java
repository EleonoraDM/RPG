package factory;

import factory.interfaces.ActionFactory;
import models.actions.BossFight;
import models.actions.OneVsOne;
import models.interfaces.Action;

import java.lang.reflect.InvocationTargetException;

public class ActionFactoryImpl implements ActionFactory {

    //FIXME to think about the meaning of the var args here (because I do not use it!!!),
    // considering the fact that we pass a List<Targetable> to the executeAction method in the Action object.

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
                action = BossFight.class.getDeclaredConstructor().newInstance();
                break;
            case "OneVsOne":
                action = OneVsOne.class.getDeclaredConstructor().newInstance();
                break;
            default:
                throw new ClassNotFoundException();
        }
        return action;
    }
}
