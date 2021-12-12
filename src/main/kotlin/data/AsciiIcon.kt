package data

@JvmInline
value class AsciiIcon private constructor(
    private val asciiIcon: UInt
) {
    constructor(char: Char) : this(char.code.toUInt())

    constructor(charA: Char, charB: Char) : this(
        ((charA.code shl 16).toUInt() or charB.code.toUInt())
    )

    val icon: String
        get() {
            val a = (asciiIcon shr 16).toInt().toChar()
            val b = (asciiIcon and 0xFFFFu).toInt().toChar()
            return if (a.code == 0) "$b" else "$a$b"
        }
}