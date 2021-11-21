package ru.ithub.newspring.contexts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.ithub.newspring.Configs.Config;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.factories.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {

    @Getter
    private Config config;
    @Setter
    private ObjectFactory objectFactory;
    private Map<Class<?>,Object> beans;

    public ApplicationContextImpl(Config config) {
        this.config = config;
        beans = new ConcurrentHashMap<>();
    }

    @Override
    public <T> T getObject(Class<T> type) {
        if (beans.containsKey(type)){
            return (T) beans.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()){
            implClass = config.getImplementationClass(type);
        }

        T t = objectFactory.createObject(implClass);
        beans.put(type, t);
        return t;
    }
}
