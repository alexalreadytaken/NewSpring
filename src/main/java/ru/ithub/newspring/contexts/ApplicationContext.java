package ru.ithub.newspring.contexts;

public interface ApplicationContext {

    <T> T getObject(Class<T> type);
}
