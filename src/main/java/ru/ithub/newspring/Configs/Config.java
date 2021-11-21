package ru.ithub.newspring.Configs;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplementationClass(Class<T> type);

    Reflections getReflections();
}
