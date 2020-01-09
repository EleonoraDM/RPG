package factory;

import factory.interfaces.SpecialFactory;
import models.interfaces.Special;
import models.specials.Heal;
import models.specials.Swiftness;
import models.specials.Toughness;

import java.lang.reflect.InvocationTargetException;

public class SpecialFactoryImpl implements SpecialFactory {

    @Override
    public Special create(String className)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {

        Special special = null;

        switch (className) {
            case "Heal":
                special = Heal.class.getDeclaredConstructor().newInstance();
                break;
            case "Toughness":
                special = Toughness.class.getDeclaredConstructor().newInstance();
                break;
            case "Swiftness":
                special = Swiftness.class.getDeclaredConstructor().newInstance();
                break;
            default:
                throw new ClassNotFoundException();
        }
        return special;
    }
}
