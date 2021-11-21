package ru.ithub.newspring.configurators.impl;

import lombok.SneakyThrows;
import ru.ithub.newspring.annotations.Inject;
import ru.ithub.newspring.configurators.ObjectConfigurator;
import ru.ithub.newspring.contexts.ApplicationContext;

import java.lang.reflect.Field;

public class InjectAnnotationObjectConfigurator implements ObjectConfigurator {

    @SneakyThrows
    public void configure(Object instance, ApplicationContext context) {
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
