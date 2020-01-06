package factory;

import factory.interfaces.TargetableFactory;
import models.participants.Targetable;

import java.lang.reflect.InvocationTargetException;

public class TargetableFactoryImpl implements TargetableFactory {
    @Override
    public Targetable create(String name, String className)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {


        return null;
    }
}
