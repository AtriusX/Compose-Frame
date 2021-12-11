package ui.component.titlebar

import androidx.compose.ui.window.WindowPlacement
import ui.data.FrameState

typealias AsciiIcon =
    String

typealias ARGB =
    Long

@Suppress("PropertyName")
interface TitleButtonStyle<T> {

    val Minimize: T

    val Maximize: FrameState.() -> T

    val Exit: T
}

object TitleButtonStyles {

    object Windows : TitleButtonStyle<AsciiIcon> {

        override val Minimize: AsciiIcon = "-"

        override val Maximize: FrameState.() -> AsciiIcon = {
            if (mode == WindowPlacement.Maximized) "▼" else "▲"
        }

        override val Exit: AsciiIcon = "✕"
    }

    object Mac : TitleButtonStyle<ARGB> {

        override val Minimize: ARGB = 0xFFFFBD44

        override val Maximize: FrameState.() -> ARGB = { 0xFF00CA4E }

        override val Exit: ARGB = 0xFFFF605C
    }
}
