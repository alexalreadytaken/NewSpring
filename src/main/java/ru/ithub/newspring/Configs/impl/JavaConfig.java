package ru.ithub.newspring.Configs.impl;

import lombok.Getter;
import org.reflections.Reflections;
import ru.ithub.newspring.Configs.Config;

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
        return subTypes.iterator().next();
    }

    private <T> boolean isNotImplementation(Class<T> tClass){
        return tClass.isInterface() || tClass.isAnnotation();
    }
}
