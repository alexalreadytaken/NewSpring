package ru.ithub.newspring.fillers.impl;

import ru.ithub.newspring.annotations.Inject;
import ru.ithub.newspring.fillers.ObjectFiller;
import ru.ithub.newspring.contexts.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class ConstructorObjectFiller implements ObjectFiller {

    public void fill(Object instance, ApplicationContext context) {
        Class<?> instanceClass = instance.getClass();
        if (containsFieldWithInjectAnnotation(instanceClass)) return;
        getBiggestPublicConstructor(instance)
                .ifPresent(c->{
                    Class<?>[] constructorParameters = c.getParameterTypes();
                    for (Class<?> cl: constructorParameters){
                        for(Field f: instanceClass.getDeclaredFields()){
                           if (f.getType()==cl){
                               f.setAccessible(true);
                               try {
                                   f.set(instance,context.getObject(cl));
                               } catch (IllegalAccessException e) {
                                   throw new RuntimeException(e);
                               }
                           }
                        }
                    }
                });
    }

    private boolean containsFieldWithInjectAnnotation(Class<?> instanceClass) {
        for(Field f: instanceClass.getDeclaredFields()){
            if (f.isAnnotationPresent(Inject.class)) return true;
        }
        return false;
    }

    private Optional<Constructor<?>> getBiggestPublicConstructor(Object instance){
        return Arrays.stream(instance.getClass().getConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount));
    }
}
