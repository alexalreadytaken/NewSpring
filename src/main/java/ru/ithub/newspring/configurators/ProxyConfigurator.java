package ru.ithub.newspring.configurators;

public interface ProxyConfigurator {

    Object makeProxyIfNeeded(Object instance, Class<?> implClass);
}
