package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
@Test
class RenderPrimitiveTest {
    fun renderNull() {
        assertEquals("null", render(null))
    }

    fun renderBoolean() {
        assertEquals("true", render(true))
        assertEquals("false", render(false))
    }

    fun renderInt() {
        assertEquals(render(123.toByte()), "123")
        assertEquals(render(123.toShort()), "123")
        assertEquals(render(123), "123")
        assertEquals(render(123.toLong()), "123")
        assertEquals(render(Int.MAX_VALUE), "2147483647")
        assertEquals(render(Int.MIN_VALUE), "-2147483648")
        assertEquals(render(Long.MAX_VALUE), "9223372036854775807")
        assertEquals(render(Long.MIN_VALUE), "-9223372036854775808")
    }

    fun renderDouble() {
        assertEquals(render(1.23), "1.23")
        assertEquals(render(-1.23), "-1.23")

        // Exponent
        assertEquals(render(1.23e100), "1.23E100")
        assertEquals(render(1.23e-100), "1.23E-100")

        // PI
        assertEquals(render(Math.PI), "3.141592653589793")

        // -0.0, NaN, -Infinity, Infinity
        assertEquals(render(-0.0), "-0.0")
        assertEquals(render(Double.NaN), "NaN")
        assertEquals(render(Double.NEGATIVE_INFINITY), "-Infinity")
        assertEquals(render(Double.POSITIVE_INFINITY), "Infinity")
    }

    fun renderChar() {
        assertEquals(render('x'), """       "x"   """.trim())
        assertEquals(render('"'), """       "\""  """.trim())
        assertEquals(render('\\'), """      "\\"  """.trim())
        assertEquals(render('\b'), """      "\b"  """.trim())
        assertEquals(render('\r'), """      "\r"  """.trim())
        assertEquals(render('\n'), """      "\n"  """.trim())
        assertEquals(render('\t'), """      "\t"  """.trim())
        assertEquals(render('\u000c'), """  "\f"  """.trim())
    }
}

