package com.damphat.test;

import kotlin.Unit;
import kotlin.reflect.KClass;
import kotlin.test.AssertionsKt;
import org.testng.annotations.Test;
import com.damphat.timson.JSON;

@Test
public class JavaCode {
    public void render() {
        String actual = JSON.render(new int[] {1,2,3});

        String expected = "[1,2,3]";

        AssertionsKt.assertEquals(actual, expected , "");
    }

    public void renderFailWithUnsupportedType() {
        class MyClass {}
        KClass<IllegalArgumentException> clazz = kotlin.jvm.JvmClassMappingKt.getKotlinClass(IllegalArgumentException.class);
        AssertionsKt.assertFailsWith(clazz, () -> {
            JSON.render(new MyClass());
            return Unit.INSTANCE;
        });
    }
}
