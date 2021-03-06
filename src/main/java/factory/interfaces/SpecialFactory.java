package factory.interfaces;

import models.interfaces.Special;

import java.lang.reflect.InvocationTargetException;

public interface SpecialFactory {

    Special createSpecial(String className) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException;

}
