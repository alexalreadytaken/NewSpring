package ru.ithub.newspring.Configs.impl;

import lombok.Getter;
import org.reflections.Reflections;
import ru.ithub.newspring.Configs.Config;
import ru.ithub.newspring.annotations.Component;

import java.util.Set;

public class JavaConfig implements Config {

    @Getter
    private final Reflections reflections;

    public JavaConfig(String packageToScan) {
        this.reflections = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> type) {
        Set<Class<? extends T>> subTypes = reflections.getSubTypesOf(type);
        subTypes.removeIf(this::isNotImplementation);
        if (subTypes.size()!=1){
            throw new RuntimeException("Incorrect implementations count of class = "+type);
        }
        Class<? extends T> implClass = subTypes.iterator().next();
        if (!implClass.isAnnotationPresent(Component.class)){
            throw new RuntimeException("Not registered components with class = "+type);
        }
        return implClass;
    }

    private <T> boolean isNotImplementation(Class<T> tClass){
        return tClass.isInterface() || tClass.isAnnotation();
    }
}
