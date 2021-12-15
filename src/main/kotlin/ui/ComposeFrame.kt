package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import ui.component.TitleBarArea
import ui.component.TitleBarComposition
import ui.component.rememberFrameState
import ui.component.titlebar.DefaultTitleBar
import ui.data.FrameState
import java.awt.Dimension

typealias WindowComposition =
    @Composable FrameWindowScope.(MutableState<FrameState>) -> Unit

@Composable
fun ComposableFrame(
    onCloseRequest: () -> Unit,
    visible: Boolean = true,
    resizable: Boolean = true,
    transparent: Boolean = false,
    enabled: Boolean = true,
    focusable: Boolean = true,
    alwaysOnTop: Boolean = false,
    title: String = "Untitled",
    onPreviewKeyEvent: (KeyEvent) -> Boolean = { false },
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    minSize: Dimension? = null,
    icon: Painter? = null,
    titleBar: TitleBarComposition = { DefaultTitleBar(icon = icon) },
    content: WindowComposition = {},
) {
    Window(
        onCloseRequest = onCloseRequest,
        visible = visible,
        undecorated = true,
        resizable = resizable,
        enabled = enabled,
        focusable = focusable,
        alwaysOnTop = alwaysOnTop,
        title = title,
        onPreviewKeyEvent = onPreviewKeyEvent,
        onKeyEvent = onKeyEvent,
        icon = icon,
        transparent = transparent
    ) {
        val frame = rememberFrameState()
        window.minimumSize = minSize
        Surface(color = Color.Transparent) {
            Column(Modifier.fillMaxSize()) {
                TitleBarArea(frame) {
                    titleBar()
                }
                Surface(color = Color.White) {
                    content(frame)
                }
            }
        }
    }
}
