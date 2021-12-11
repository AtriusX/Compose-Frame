package data

import ui.data.FrameState

@Suppress("PropertyName")
interface TitleButtonStyle<T> {

    val Minimize: T

    val Maximize: FrameState.() -> T

    val Exit: T
}
