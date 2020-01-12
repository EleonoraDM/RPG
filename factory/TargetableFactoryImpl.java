package factory;

import factory.interfaces.TargetableFactory;
import models.interfaces.Targetable;
import models.participants.Boss;
import models.participants.Necromancer;
import models.participants.Warrior;
import models.participants.Wizard;

import java.lang.reflect.InvocationTargetException;

public class TargetableFactoryImpl implements TargetableFactory{

    @Override
    public Targetable create(String name, String className)
            throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {

        Targetable hero;

        switch (className) {
            case "Boss":
                hero = Boss.class.getConstructor(String.class).newInstance(name);
                break;
            case "Necromancer":
                hero = Necromancer.class.getConstructor(String.class).newInstance(name);
                break;
            case "Warrior":
                hero = Warrior.class.getConstructor(String.class).newInstance(name);
                break;
            case "Wizard":
                hero = Wizard.class.getConstructor(String.class).newInstance(name);
                break;
            default:
                throw new ClassNotFoundException();
        }
        return hero;
    }
}
