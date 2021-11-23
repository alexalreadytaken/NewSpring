package ru.ithub.newspring.fillers.impl;

import lombok.SneakyThrows;
import ru.ithub.newspring.annotations.Inject;
import ru.ithub.newspring.fillers.ObjectFiller;
import ru.ithub.newspring.contexts.ApplicationContext;

import java.lang.reflect.Field;

public class InjectAnnotationObjectFiller implements ObjectFiller {

    @SneakyThrows
    public void fill(Object instance, ApplicationContext context) {
        for (Field f: instance.getClass().getDeclaredFields()){
            if (f.isAnnotationPresent(Inject.class)){
                f.setAccessible(true);
                if (f.get(instance)==null){
                    Object object = context.getObject(f.getType());
                    f.set(instance, object);
                }
            }
        }
    }
}
