package ui.component.titlebar

import androidx.compose.ui.window.WindowPlacement
import data.ARGB
import data.AsciiIcon
import data.MaximizeFunction
import data.TitleButtonStyle

object TitleButtonStyles {

    object Windows : TitleButtonStyle<AsciiIcon> {

        override val Minimize: AsciiIcon = AsciiIcon('▬')

        override val Maximize: MaximizeFunction<AsciiIcon> = {
            AsciiIcon(if (mode == WindowPlacement.Maximized) '▼' else '▲')
        }

        // X Icon
        override val Exit: AsciiIcon = AsciiIcon('\uD83D', '\uDFAE')
    }

    object Mac : TitleButtonStyle<ARGB> {

        override val Minimize: ARGB = ARGB(0xFFFFBD44)

        override val Maximize: MaximizeFunction<ARGB> = {
            ARGB(0xFF00CA4E)
        }

        override val Exit: ARGB = ARGB(0xFFFF605C)
    }
}
