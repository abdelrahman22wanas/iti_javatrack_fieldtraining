package ch6;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args) {
        Class<?> clazz = AuthoredClass.class;

        if (clazz.isAnnotationPresent(Author.class)) {
            Author author = clazz.getAnnotation(Author.class);
            System.out.println("Class: " + clazz.getSimpleName()
                    + " -> Author: " + author.name() + ", Date: " + author.date());
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Author.class)) {
                Author author = field.getAnnotation(Author.class);
                System.out.println("Field: " + field.getName()
                        + " -> Author: " + author.name() + ", Date: " + author.date());
            }
        }

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Author.class)) {
                Author author = constructor.getAnnotation(Author.class);
                System.out.println("Constructor: " + constructor.getName()
                        + " -> Author: " + author.name() + ", Date: " + author.date());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Author.class)) {
                Author author = method.getAnnotation(Author.class);
                System.out.println("Method: " + method.getName()
                        + " -> Author: " + author.name() + ", Date: " + author.date());
            }
        }
    }
}
