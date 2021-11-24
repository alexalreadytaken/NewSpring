package ru.ithub.newspring.contexts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.ithub.newspring.configs.Config;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.factories.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {

    @Getter
    private final Config config;
    @Setter
    private ObjectFactory objectFactory;
    private final Map<Class<?>,Object> components;

    public ApplicationContextImpl(Config config) {
        this.config = config;
        components = new ConcurrentHashMap<>();
    }

    @Override
    public <T> T getObject(Class<T> type) {
        if (components.containsKey(type)){
            return (T) components.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()){
            implClass = config.getImplementationClass(type);
        }

        T t = objectFactory.createObject(implClass);
        components.put(type, t);
        return t;
    }
}
