package ui.component.titlebar

import androidx.compose.ui.window.WindowPlacement
import data.ARGB
import data.AsciiIcon
import data.TitleButtonStyle
import ui.data.FrameState

object TitleButtonStyles {

    object Windows : TitleButtonStyle<AsciiIcon> {

        override val Minimize: AsciiIcon = AsciiIcon('▬')

        override val Maximize: FrameState.() -> AsciiIcon = {
            AsciiIcon(if (mode == WindowPlacement.Maximized) '▼' else '▲')
        }

        override val Exit: AsciiIcon = AsciiIcon('✕')
    }

    object Mac : TitleButtonStyle<ARGB> {

        override val Minimize: ARGB = ARGB(0xFFFFBD44)

        override val Maximize: FrameState.() -> ARGB = { ARGB(0xFF00CA4E) }

        override val Exit: ARGB = ARGB(0xFFFF605C)
    }
}
