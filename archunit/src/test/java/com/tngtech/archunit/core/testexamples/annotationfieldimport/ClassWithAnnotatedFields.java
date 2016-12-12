package com.tngtech.archunit.core.testexamples.annotationfieldimport;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.tngtech.archunit.core.testexamples.SomeEnum;

import static com.tngtech.archunit.core.testexamples.SomeEnum.OTHER_VALUE;
import static com.tngtech.archunit.core.testexamples.SomeEnum.SOME_VALUE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class ClassWithAnnotatedFields {
    @FieldAnnotationWithStringValue("something")
    public Object stringAnnotatedField;

    @FieldAnnotationWithStringValue("otherThing")
    @FieldAnnotationWithIntValue(otherValue = "overridden")
    public Object stringAndIntAnnotatedField;

    @FieldAnnotationWithEnumClassAndArrayValue(
            value = OTHER_VALUE,
            enumArray = {SOME_VALUE, OTHER_VALUE},
            clazz = Serializable.class,
            classes = {Object.class, Serializable.class},
            additional = @SomeValueAnnotation(value = OTHER_VALUE),
            additionals = {@SomeValueAnnotation(value = SOME_VALUE), @SomeValueAnnotation(value = OTHER_VALUE)})
    public Object enumAndArrayAnnotatedField;

    @Target(FIELD)
    @Retention(RUNTIME)
    public @interface FieldAnnotationWithStringValue {
        String value();
    }

    @Target(FIELD)
    @Retention(RUNTIME)
    public @interface FieldAnnotationWithIntValue {
        int intValue() default 0;

        String otherValue() default "Nothing";
    }

    @Target(FIELD)
    @Retention(RUNTIME)
    public @interface FieldAnnotationWithEnumClassAndArrayValue {
        SomeEnum value() default SOME_VALUE;

        SomeEnum[] enumArray() default {SOME_VALUE};

        Class clazz() default String.class;

        Class[] classes();

        SomeValueAnnotation additional();

        SomeValueAnnotation[] additionals();
    }

    public @interface SomeValueAnnotation {
        SomeEnum value();
    }
}
