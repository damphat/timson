package com.damphat.test

import com.damphat.timson.render
import org.testng.annotations.Test
import kotlin.test.assertFails

@Test
class ThrowExceptionTest {
    fun throwException() {
        class MyClass
        assertFails { render(MyClass()) }
    }
}