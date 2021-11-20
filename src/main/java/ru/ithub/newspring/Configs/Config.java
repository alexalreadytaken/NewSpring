package ru.ithub.newspring.Configs;

public interface Config {
    <T> T getImplementation(Class<T> type);
}
