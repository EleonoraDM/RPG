package factory.interfaces;

import models.participants.Targetable;

import java.lang.reflect.InvocationTargetException;

public interface TargetableFactory {
    Targetable create(String name, String className) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException;
}