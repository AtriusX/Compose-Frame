package listener

import androidx.compose.runtime.MutableState
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPlacement
import ui.data.FrameState
import java.awt.MouseInfo
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter

class FrameListener(
    private val window: ComposeWindow,
    private val scope: FrameWindowScope,
    private val windowState: MutableState<FrameState>
) {
    private var location = window.location.toComposeOffset()
    private var pointStart = MouseInfo.getPointerInfo().location.toComposeOffset()
    private var ratio = 1.0
    private var reset = true

    private val dragListener = object : MouseMotionAdapter() {
        override fun mouseDragged(event: MouseEvent) = drag()
    }

    private val boundsListener = object : MouseAdapter() {
        override fun mouseReleased(e: MouseEvent?) = with(window) {
            if (location.y < graphicsConfiguration.bounds.y) {
                setLocation(location.x, graphicsConfiguration.bounds.y)
                windowState.value = FrameState(window.location, window.size)
            }
        }
    }

    private val removeListener = object : MouseAdapter() {
        override fun mouseReleased(event: MouseEvent) {
            window.removeMouseMotionListener(dragListener)
            window.removeMouseListener(boundsListener)
            window.removeMouseListener(this)
            if (window.location.toComposeOffset() != location) {
                windowState.value = FrameState(window.location, window.size)
                reset = true
            }
        }
    }

    fun register(clicks: Int) {
        location = window.location.toComposeOffset()
        pointStart = MouseInfo.getPointerInfo().location.toComposeOffset()
        if (clicks == 2) {
            scope.toggleState(windowState)
        } else with(window) {
            addMouseListener(removeListener)
            addMouseListener(boundsListener)
            addMouseMotionListener(dragListener)
        }
    }

    private fun drag() = with(window) {
        val point = MouseInfo.getPointerInfo().location.toComposeOffset()
        var location = this@FrameListener.location + (point - pointStart)
        if (windowState.value.mode == WindowPlacement.Maximized) {
            if (reset) with(graphicsConfiguration) {
                ratio = (point.x - bounds.x) / bounds.width.toDouble()
                reset = false
            }
            size = windowState.value.size
            location = location.copy(x = point.x - (width * ratio).toInt())
        }
        setLocation(location.x, location.y)
    }

    private fun Point.toComposeOffset() = IntOffset(x, y)
}

fun FrameWindowScope.toggleState(state: MutableState<FrameState>) {
    val (frameState, setFrameState) = state
    with(frameState) {
        if (mode == WindowPlacement.Floating)
            setFrameState(FrameState(window.location, window.size, WindowPlacement.Maximized))
        else {
            window.size = size
            window.location = position
            setFrameState(copy(mode = WindowPlacement.Floating))
        }
    }
}