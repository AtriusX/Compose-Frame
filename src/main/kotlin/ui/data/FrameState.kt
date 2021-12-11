package ui.data

import androidx.compose.ui.window.WindowPlacement
import java.awt.Dimension
import java.awt.Point

data class FrameState(
    var position: Point,
    var size: Dimension,
    var mode: WindowPlacement = WindowPlacement.Floating,
)