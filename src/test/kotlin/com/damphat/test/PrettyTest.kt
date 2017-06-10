package com.damphat.test

import com.damphat.timson.render
import org.testng.annotations.Test
import kotlin.test.assertEquals

@Test
class PrettyTest {
    fun noChildren() {
        assertEquals(render(emptyMap<Any?, Any?>(), 2), "{\n}")
        assertEquals(render(emptyList<Any?>(), 2), "[\n]")
    }

    fun hasChildren() {

        val map = mapOf(
                "x" to mapOf(
                     "1" to 1,
                     "2" to 2
                ),
                "y" to listOf(
                        1,
                        2
                )
        )

        val expected = """{
        |  "x": {
        |    "1": 1,
        |    "2": 2
        |  },
        |  "y": [
        |    1,
        |    2
        |  ]
        |}""".trimMargin()

        assertEquals(render(map, 2), expected)
    }
}