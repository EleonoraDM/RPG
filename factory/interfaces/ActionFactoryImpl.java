package factory.interfaces;

import models.actions.Action;

import java.lang.reflect.InvocationTargetException;

public class ActionFactoryImpl implements ActionFactory {

    @Override
    public Action create(String actionName, String... participantNames)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {


        return null;
    }
}
