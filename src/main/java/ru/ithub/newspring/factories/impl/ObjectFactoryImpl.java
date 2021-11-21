package ru.ithub.newspring.factories.impl;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.ithub.newspring.configurators.ObjectConfigurator;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.factories.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactoryImpl implements ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators;

    @SneakyThrows
    public ObjectFactoryImpl(ApplicationContext context) {
        this.context = context;
        configurators = new ArrayList<>();
        Reflections reflections = new Reflections("ru.ithub.newspring.configurators");
        for (Class<? extends ObjectConfigurator> cnf: reflections.getSubTypesOf(ObjectConfigurator.class)){
            configurators.add(cnf.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T instance = createEmpty(implClass);
        configure(instance);
        return instance;
    }

    @SneakyThrows
    private <T> T createEmpty(Class<T> implClass){
        return implClass.getDeclaredConstructor().newInstance();
    }

    private <T> void configure(T obj){
        configurators.forEach(cnf->cnf.configure(obj,context));
    }
}
