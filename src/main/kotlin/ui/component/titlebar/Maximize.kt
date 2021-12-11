package ui.component.titlebar

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPlacement
import listener.toggleState
import ui.component.CircleButton
import ui.component.titlebar.TitleButtonStyles.Mac
import ui.component.titlebar.TitleButtonStyles.Windows
import ui.data.FrameState
import java.awt.Toolkit
import java.lang.Integer.max

@Composable
fun FrameWindowScope.Maximize(
    state: MutableState<FrameState>,
    layout: DefaultTitleBarLayout = DefaultTitleBarLayout.Windows,
) {
    // Update frame by state
    fun update(state: MutableState<FrameState>) {
        if (state.value.mode == WindowPlacement.Maximized) with(window) {
            with(graphicsConfiguration) {
                val insets = Toolkit.getDefaultToolkit().getScreenInsets(this)
                setSize(
                    bounds.width - max(insets.left, insets.right),
                    bounds.height - max(insets.top, insets.bottom)
                )
                location = bounds.location.apply {
                    x += insets.left
                    y += insets.top
                }
            }
        }
    }
    // Content
    if (layout == DefaultTitleBarLayout.Windows) Button(
        onClick = { toggleState(state) },
        elevation = null
    ) {
        update(state)
        Text(
            text = Windows.Maximize(state.value),
            fontWeight = FontWeight.Bold)
    } else {
        update(state)
        CircleButton(
            color = Color(Mac.Maximize(state.value)),
            onClick = { toggleState(state) }
        )
    }
}
