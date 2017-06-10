package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

@Test
class RenderStringTest{

    fun empty() {
        assertEquals(render(""), """         ""       """.trim())
    }

    fun single() {
        assertEquals(render("x"), """        "x"      """.trim())
        assertEquals(render("\""), """       "\""     """.trim())
        assertEquals(render("\\"), """       "\\"     """.trim())
        assertEquals(render("\b"), """       "\b"     """.trim())
        assertEquals(render("\r"), """       "\r"     """.trim())
        assertEquals(render("\n"), """       "\n"     """.trim())
        assertEquals(render("\t"), """       "\t"     """.trim())
        assertEquals(render("\u000c"), """   "\f"     """.trim())
        assertEquals(render("\u0444"), """   "ф"      """.trim())
        assertEquals(render("\u0012"), """   "\u0012" """.trim())
    }

    fun multiple() {
        val s:CharSequence = "hello - こんにちは - Здравствуйте - \b\r\n\t\u000c"

        val expected = """
            "hello - こんにちは - Здравствуйте - \b\r\n\t\f"
        """.trim()

        assertEquals(render(s), expected)
    }
}