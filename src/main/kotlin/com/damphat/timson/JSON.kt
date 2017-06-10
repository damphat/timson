@file:JvmName("JSON")
package com.damphat.timson;

@JvmOverloads
fun render(o: Any?, indent: Int = 0) = renderTo(StringBuilder(), o, indent).toString()

@JvmOverloads
fun renderTo(sb: StringBuilder, obj: Any?, indent: Int = 0, prefix: Int = 0): StringBuilder {
    when (obj) {
        null -> sb.append("null")
        is Boolean -> sb.append(obj)
        is Number -> renderNumberTo(sb, obj)
        is Char -> renderCharTo(sb, obj)
        is CharSequence -> renderCharSequenceTo(sb, obj)
        is Map<*, *> -> renderMapTo(sb, obj, indent, prefix)
        is Iterable<*> -> renderIterableTo(sb, obj, indent, prefix)
        is Array<*> -> renderArrayTo(sb, obj, indent, prefix)
        else -> {
            if (obj.javaClass.isArray) renderPrimativeArrayTo(sb, obj, indent, prefix)
            else throw IllegalArgumentException("Cannot render this Type:")
        }
    }
    return sb
}

fun renderNumberTo(sb: StringBuilder, num: Number) {
    when (num) {
        // TODO locale problem?
        is Int -> sb.append(num)
        is Double -> sb.append(num)
        is Byte -> sb.append(num.toInt())
        is Short -> sb.append(num.toInt())
        is Float -> sb.append(num)
        is Long -> sb.append(num)
        else -> sb.append(num)
    }
}


fun renderCharTo(sb: StringBuilder, c: Char) {
    sb.append('"')
    escapeCharTo(sb, c)
    sb.append('"')
}

fun renderCharSequenceTo(sb: StringBuilder, s: CharSequence) {
    sb.append('"')
    for (i in 0..s.length - 1) {
        escapeCharTo(sb, s[i])
    }
    sb.append('"')
}

fun renderIterableTo(sb: StringBuilder, iter: Iterable<*>, indent: Int, prefix: Int) {
    val pretty = indent > 0
    sb.append('[')
    val childPrefix = prefix + indent
    var comma = false
    for (v in iter) {
        if (!comma) comma = true else sb.append(',')
        if (pretty) renderSpaceTo(sb, childPrefix)
        renderTo(sb, v, indent, childPrefix)
    }
    if (pretty) renderSpaceTo(sb, prefix)
    sb.append(']')
}

fun renderArrayTo(sb: StringBuilder, arr: Array<*>, indent: Int, prefix: Int) {
    val pretty = indent > 0
    sb.append('[')
    val childPrefix = prefix + indent
    var comma = false
    for (v in arr) {
        if (!comma) comma = true else sb.append(',')
        if (pretty) renderSpaceTo(sb, childPrefix)
        renderTo(sb, v, indent, childPrefix)
    }
    if (pretty) renderSpaceTo(sb, prefix)
    sb.append(']')
}

fun renderPrimativeArrayTo(sb: StringBuilder, arr: Any, indent: Int, prefix: Int) {
    val pretty = indent > 0
    val len = java.lang.reflect.Array.getLength(arr)
    sb.append('[')
    val childPrefix = prefix + indent
    var comma = false
    for (i in 0..len - 1) {
        val v = java.lang.reflect.Array.get(arr, i)
        if (!comma) comma = true else sb.append(',')
        if (pretty) renderSpaceTo(sb, childPrefix)
        renderTo(sb, v, indent, childPrefix)
    }
    if (pretty) renderSpaceTo(sb, prefix)
    sb.append(']')
}

fun renderMapTo(sb: StringBuilder, o: Map<*, *>, indent: Int, prefix: Int) {
    val childPrefix = prefix + indent
    val pretty = indent > 0
    sb.append('{')
    var comma = false
    for ((k, v) in o) {
        if (!comma) comma = true else sb.append(',')
        if (pretty) renderSpaceTo(sb, childPrefix)
        renderCharSequenceTo(sb, k.toString())
        sb.append(':')
        if (pretty) sb.append(' ')
        renderTo(sb, v, indent, childPrefix)
    }
    if (pretty) renderSpaceTo(sb, prefix)
    sb.append('}')
}

private fun renderSpaceTo(sb: StringBuilder, prefix: Int) {
    sb.append('\n')
    repeat(prefix) { sb.append(' ') }
}

private fun escapeCharTo(sb: StringBuilder, c: Char) {
    when (c) {
        '"' -> sb.append('\\').append('"')
        '\\' -> sb.append('\\').append('\\')
        '\b' -> sb.append('\\').append('b')
        '\r' -> sb.append('\\').append('r')
        '\n' -> sb.append('\\').append('n')
        '\t' -> sb.append('\\').append('t')
        '\u000c' -> sb.append('\\').append('f')
        in '\u0000'..'\u001F',
        in '\u007F'..'\u009F',
        in '\u2000'..'\u20FF' -> {
            // TODO are these not-printable characters?
            sb.append("\\u")
            val x = c.toInt()
            sb.append(hex[x shr 12])
            sb.append(hex[x shr 8 and 15])
            sb.append(hex[x shr 4 and 15])
            sb.append(hex[x and 15])
        }
        else -> sb.append(c)
    }
}

private val hex = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
