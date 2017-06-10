package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

class RenderListTest {

    @Test
    fun empty() = assertEquals(render(emptyList<Any?>()), "[]")

    @Test
    fun single() = assertEquals(render(listOf(1)), "[1]")

    @Test
    fun multiple() {
        val list = listOf(null, 123, true, false, "xyz", listOf(1))

        val expected = """
            [null, 123, true, false, "xyz", [1]]
        """.replace(Regex("\\s"), "")

        assertEquals(render(list), expected)
    }
}