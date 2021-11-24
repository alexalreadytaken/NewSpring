package ru.ithub.newspring.fillers.impl;

import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import ru.ithub.newspring.annotations.LogMethods;
import ru.ithub.newspring.fillers.ProxyFiller;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

public class LogMethodProxyFiller implements ProxyFiller {

    @SneakyThrows
    // TODO: 23.11.2021 log not all methods
    public Object makeProxyIfNeeded(Object instance, Class<?> implClass) {
        Object proxyResult = instance;
        if (implClass.isAnnotationPresent(LogMethods.class)) {
            MethodInterceptor handler = (obj, method, args, proxy) ->
                    wrapWithLog(instance, implClass, method, args, proxy,
                            implClass.getAnnotation(LogMethods.class));
            proxyResult = Enhancer.create(implClass, handler);
        }
        return proxyResult;
    }

    private Object wrapWithLog(Object instance, Class<?> implClass, Method method, Object[] args,
                               MethodProxy proxy, LogMethods annotation) throws Throwable {
        if (annotation.logInputMethod()){
            System.out.printf("%s = Class %s method '%s' input arguments = %s \n",
                    LocalDateTime.now(),implClass.getName(),
                    method.getName(),Arrays.toString(args));
        }
        Object methodResult = proxy.invoke(instance, args);
        if (annotation.logOutputMethod()) {
            System.out.printf("%s = Class %s method '%s' output = %s \n",
                    LocalDateTime.now(),implClass.getName(),
                    method.getName(),methodResult);
        }
        return methodResult;
    }

}
