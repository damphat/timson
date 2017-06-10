package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

@Test
class RenderMapTest {

    fun empty() = assertEquals(render(emptyMap<Any?,Any?>()), "{}")

    fun single() = assertEquals(render(mapOf("x" to 1)), """  {"x":1}  """.trim())

    fun multiple() {
        val map = mapOf(
                null to null,
                123 to 123,
                true to true,
                false to false,
                "xyz" to "xyz",
                "map" to mapOf(
                        "x" to 1
                )
        )

        val expected = """
            {
                "null": null,
                "123": 123,
                "true": true,
                "false": false,
                "xyz": "xyz",
                "map": {
                    "x": 1
                }
            }
            """.replace(Regex("\\s"), "")

        assertEquals(render(map), expected)
    }
}