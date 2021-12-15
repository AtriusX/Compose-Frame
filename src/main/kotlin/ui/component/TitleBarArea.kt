package ui.component

import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.awtEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPlacement
import listener.FrameListener
import ui.data.FrameState
import java.awt.Dimension
import java.awt.Point

typealias TitleBarComposition =
    @Composable TitleBarScope.() -> Unit

interface TitleBarScope : FrameWindowScope {
    val windowState: MutableState<FrameState>
}

@Composable
fun FrameWindowScope.rememberFrameState(
    position: Point = window.location,
    size: Dimension = window.size,
    mode: WindowPlacement = window.placement,
): MutableState<FrameState> = remember {
    mutableStateOf(FrameState(position, size, mode))
}

@Composable
fun FrameWindowScope.TitleBarArea(
    windowState: MutableState<FrameState> = rememberFrameState(),
    modifier: Modifier = Modifier,
    content: TitleBarComposition = {},
) {
    val handler = remember { FrameListener(window, this, windowState) }
    window.isResizable = windowState.value.mode == WindowPlacement.Floating
    Box(
        modifier = modifier.pointerInput(Unit) {
            forEachGesture {
                awaitPointerEventScope {
                    awaitFirstDown()
                    handler.register(currentEvent.awtEvent.clickCount)
                }
            }
        }
    ) {
        content(object : TitleBarScope {
            override val windowState = windowState
            override val window = this@TitleBarArea.window
        })
    }
}