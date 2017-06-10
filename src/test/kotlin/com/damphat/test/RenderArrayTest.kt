package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test


@Test
class RenderArrayTest{

    fun empty() = assertEquals(render(emptyArray<Any?>()), "[]")

    fun single() = assertEquals(render(arrayOf(1)), "[1]")

    fun multiple() {
        val arr = arrayOf(null, 123, true, false, "xyz", arrayOf(1))

        val expected = """
            [null, 123, true, false, "xyz", [1]]
        """.replace(Regex("\\s"), "")

        assertEquals(render(arr), expected)
    }
}