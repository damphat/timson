package com.damphat.test

import com.damphat.timson.render
import org.testng.annotations.Test
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.test.assertEquals

@Test
class RenderBigNumberTest {
    fun bigInteger() {
        assertEquals(render(BigInteger("123")), "123")
    }

    fun bigDecimal() {
        assertEquals(render(BigDecimal("1.23")), "1.23")
    }
}