package ru.ithub.newspring.configs.impl;

import lombok.Getter;
import org.reflections.Reflections;
import ru.ithub.newspring.configs.Config;
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
        subTypes.removeIf(cl->!cl.isAnnotationPresent(Component.class));
        int size = subTypes.size();
        if (size!=1){
            throw new RuntimeException(String.format("Incorrect implementations count {%s} of class = %s",size,type));
        }
        return subTypes.iterator().next();
    }

    private <T> boolean isNotImplementation(Class<T> tClass){
        return tClass.isInterface() || tClass.isAnnotation();
    }
}
