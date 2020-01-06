package factory.interfaces;

import models.interfaces.Action;

import java.lang.reflect.InvocationTargetException;

public interface ActionFactory {
    Action create(String actionName, String... participantNames) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException;
}
