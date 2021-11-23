package ru.ithub.newspring.fillers;

public interface ProxyFiller {

    Object makeProxyIfNeeded(Object instance, Class<?> implClass);
}
