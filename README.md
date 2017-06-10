```kotlin
import com.damphat.timson.render

val user = mapOf(
    "name" to mapOf(
        "first" to "Phat",
        "last" to "Dam"
    ),
    "array" to listOf(1, 2, 3, 4)
)


fun main(args: Array<String>) {
    // render or stringify, indent = 2
    print(render(user, 2))
}
```

**Result:**
```json
{
  "name": {
    "first": "Phat",
    "last": "Dam"
  },
  "array": [
    1,
    2,
    3,
    4
  ]
}
```
