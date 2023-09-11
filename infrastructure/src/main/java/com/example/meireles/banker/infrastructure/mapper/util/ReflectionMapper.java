package com.example.meireles.banker.infrastructure.mapper.util;

import com.example.meireles.banker.domain.exception.ProcessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Slf4j
public class ReflectionMapper {

    public void merge(Object targetObject, Object sourceObject){
        log.info("Merging Objects. Source Object = {}, Target Object = {}", sourceObject, targetObject);
        if (targetObject.getClass() != sourceObject.getClass()) {
            log.error("Error on merge objects. Class of target and source objects, " +
                    "are not the same. Source object class = {}, Target object class = {}", sourceObject.getClass(),
                    this.getClass());
            throw new ProcessException("The objects are not of the same type. So is impossible to merge");
        }

        var objectsClass = sourceObject.getClass();
        Field[] fields = sourceObject.getClass().getDeclaredFields();

        for (Field field : fields){
            try{
                log.info("Mapping field = {}", field);
                Method getter = getMethod(field, objectsClass);
                Method setter = setMethod(field, objectsClass);

                Object sourceValue = getter.invoke(sourceObject);
                Object targetValue = getter.invoke(targetObject);

                if (targetValue == null) {
                    setter.invoke(targetObject, sourceValue);
                }

            } catch (Exception e){
                log.error("Error on mapping field = {}", field);
                throw new ProcessException("An error occurred on trying to map field", e);
            }
        }
    }

    private Method setMethod(Field field, Class<?> objectClass) {
        try {
            return objectClass.getMethod("set" + capitalizeFirstLetter(field.getName()),
                    field.getType());
        } catch (NoSuchMethodException e){
            log.error("Set method not founded for field = {}", field);
            throw new ProcessException("Set Method not founded for that field", e);
        }
    }

    private Method getMethod(Field field, Class<?> objectClass) {
        try {
            return objectClass.
                    getMethod("get" + capitalizeFirstLetter(field.getName()));
        } catch (NoSuchMethodException e){
            log.error("Get method not founded for field = {}", field);
            throw new ProcessException("Get Method not founded for that field", e);
        }
    }

    private String capitalizeFirstLetter(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
