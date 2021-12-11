package data

@JvmInline @Suppress("unused")
value class ARGB(val color: Long) {

    val alpha: Byte
        get() = this[0]

    val red: Byte
        get() = this[1]

    val green: Byte
        get() = this[2]

    val blue: Byte
        get() = this[3]

    private operator fun get(channel: Int): Byte {
        require(channel < 4)
        return (color shl (3 - channel) * 8 and 0xFF).toByte()
    }
}