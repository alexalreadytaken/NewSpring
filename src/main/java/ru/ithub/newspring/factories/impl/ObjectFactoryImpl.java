package ru.ithub.newspring.factories.impl;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.ithub.newspring.fillers.ObjectFiller;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.factories.ObjectFactory;
import ru.ithub.newspring.fillers.ProxyFiller;

import javax.annotation.PostConstruct;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactoryImpl implements ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectFiller> fillers;
    private final List<ProxyFiller> proxyFillers;

    @SneakyThrows
    public ObjectFactoryImpl(ApplicationContext context) {
        this.context = context;
        fillers = new ArrayList<>();
        proxyFillers = new ArrayList<>();
        // TODO: 23.11.2021 config
        Reflections reflections = new Reflections("ru.ithub.newspring.fillers");
        for (Class<? extends ObjectFiller> filler: reflections.getSubTypesOf(ObjectFiller.class)){
            fillers.add(filler.getDeclaredConstructor().newInstance());
        }
        for(Class<? extends ProxyFiller> filler: reflections.getSubTypesOf(ProxyFiller.class)){
            proxyFillers.add(filler.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        T instance = createEmpty(implClass);
        fill(instance);
        runInitMethods(implClass,instance);
        instance = makeProxyIfNeeded(implClass,instance);
        return instance;
    }

    @SneakyThrows
    private <T> T createEmpty(Class<T> implClass){
        Constructor<T> constructor;
        try {
            constructor = implClass.getDeclaredConstructor();
        }catch (NoSuchMethodException e){
            throw new RuntimeException("Component must contains empty constructor",e);
        }
        return constructor.newInstance();
    }

    @SneakyThrows
    private <T> void runInitMethods(Class<T> tClass,T instance){
        for (Method method: tClass.getDeclaredMethods()){
            if (method.isAnnotationPresent(PostConstruct.class)){
                if (method.getParameterCount()!=0) {
                    throw new RuntimeException("method with annotation @PostConstruct must not contains arguments");
                }
                method.invoke(instance);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T makeProxyIfNeeded(Class<T> implClass,T instance){
        for (ProxyFiller filler: proxyFillers){
            instance = (T) filler.makeProxyIfNeeded(instance,implClass);
        }
        return instance;
    }

    private <T> void fill(T obj){
        fillers.forEach(filler->filler.fill(obj,context));
    }
}
