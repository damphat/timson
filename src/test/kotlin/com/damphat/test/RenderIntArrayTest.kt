package com.damphat.test

import com.damphat.timson.render
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

@Test class RenderIntArrayTest{


    fun empty() = assertEquals(render(intArrayOf()), "[]")

    fun single() = assertEquals(render(intArrayOf(1)), "[1]")

    fun multiple() {
        val arr = intArrayOf(1,2,3)

        val expected = """
            [1,2,3]
        """.replace(Regex("\\s"), "")

        assertEquals(render(arr), expected)
    }
}