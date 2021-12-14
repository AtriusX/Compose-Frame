package data

open class ConsoleObject {
    val ESCAPE: Char = '\u001B'
}

class ConsoleColor(
    private val color: ARGB,
    background: Boolean = false,
) : ConsoleObject() {
    constructor(code: UInt, background: Boolean = false) : this(ARGB(code.toLong()), background)

    private val colorType: Int =
        if (background) 48 else 38

    override fun toString(): String {
        return "$ESCAPE[$colorType;2;${color.red};${color.green};${color.blue}m"
    }

    companion object {

        fun black(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFF000000u, background)

        fun white(background: Boolean): ConsoleColor =
            ConsoleColor(0xFFFFFFFFu, background)

        fun red(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFFFF0000u, background)

        fun green(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFF00FF00u, background)

        fun blue(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFF0000FFu, background)

        fun yellow(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFFFFFF00u, background)

        fun magenta(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFFFF00FFu, background)

        fun cyan(background: Boolean = false): ConsoleColor =
            ConsoleColor(0xFF00fFFFu, background)

        fun of(red: UByte, green: UByte, blue: UByte, background: Boolean = false): ConsoleColor =
            ConsoleColor(
                run {
                    var base = 0xFF000000u
                    base += red.toUInt() shl 16
                    base += green.toUInt() shl 8
                    base += blue
                    base
                },
                background
            )
    }
}

sealed class ConsoleStyle(private val code: Byte) : ConsoleObject() {

    object RESET : ConsoleStyle(0)

    object BOLD : ConsoleStyle(1)

    object ITALIC : ConsoleStyle(3)

    object UNDERLINE : ConsoleStyle(4)

    object STRIKETHROUGH : ConsoleStyle(9)

    override fun toString(): String {
        return "$ESCAPE[${code}m"
    }
}