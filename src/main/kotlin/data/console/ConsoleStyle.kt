package data.console

sealed class ConsoleStyle(private val code: Byte) : ConsoleObject() {

    object Reset : ConsoleStyle(0)

    object Bold : ConsoleStyle(1)

    object Italic : ConsoleStyle(3)

    object Underline : ConsoleStyle(4)

    object Strikethrough : ConsoleStyle(9)

    override fun toString(): String {
        return "$ESCAPE[${code}m"
    }
}