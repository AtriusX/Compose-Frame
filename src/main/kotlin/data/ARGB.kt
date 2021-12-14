package data

@JvmInline @Suppress("unused")
value class ARGB(val color: Long) {

    val alpha: UByte
        get() = this[0]

    val red: UByte
        get() = this[1]

    val green: UByte
        get() = this[2]

    val blue: UByte
        get() = this[3]

    private operator fun get(channel: Int): UByte {
        require(channel < 4)
        return ((color shr (3 - channel) * 8) and 0xFF).toUByte()
    }
}