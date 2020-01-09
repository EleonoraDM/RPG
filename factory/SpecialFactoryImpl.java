package factory;

import factory.interfaces.SpecialFactory;
import models.interfaces.Special;

import java.lang.reflect.InvocationTargetException;

public class SpecialFactoryImpl implements SpecialFactory {

    @Override
    public Special create(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return null;
    }
}
