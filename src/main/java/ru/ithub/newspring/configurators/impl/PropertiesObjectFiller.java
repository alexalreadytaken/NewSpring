package ru.ithub.newspring.configurators.impl;

import lombok.SneakyThrows;
import ru.ithub.newspring.annotations.Prop;
import ru.ithub.newspring.contexts.ApplicationContext;
import ru.ithub.newspring.configurators.ObjectFiller;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesObjectFiller implements ObjectFiller {

    @SneakyThrows
    public void fill(Object instance, ApplicationContext context) {
        InputStream propsStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        Properties props = new Properties();
        props.load(propsStream);
        for (Field f: instance.getClass().getDeclaredFields()){
            if (f.isAnnotationPresent(Prop.class)){
                f.setAccessible(true);
                String key = f.getAnnotation(Prop.class).key();
                Class<?> fType = f.getType();
                if (f.get(instance)==null){
                    if (!props.containsKey(key)) {
                        throw new RuntimeException(String.format("property %s not found",key));
                    }
                    f.set(instance,fType.cast(props.get(key)));
                }
            }
        }
    }
}
