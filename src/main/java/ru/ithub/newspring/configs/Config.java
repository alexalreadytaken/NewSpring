package ru.ithub.newspring.configs;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplementationClass(Class<T> type);

    Reflections getReflections();
}
