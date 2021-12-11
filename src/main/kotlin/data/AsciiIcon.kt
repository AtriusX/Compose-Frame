package data

@JvmInline
value class AsciiIcon(
    private val asciiIcon: Char
) {
    val icon: String
        get() = asciiIcon.toString()
}