package data

import ui.data.FrameState

typealias MaximizeFunction<T> =
    FrameState.() -> T

@Suppress("PropertyName")
interface TitleButtonStyle<T> {

    val Minimize: T

    val Maximize: MaximizeFunction<T>

    val Exit: T
}
