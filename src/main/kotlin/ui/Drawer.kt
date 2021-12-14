package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.component.BasicButton

@Suppress("unused")
enum class SlideDirection {
    Up, Down, Left, Right
}

@Composable
fun DefaultDrawerButton(state: MutableState<Boolean>, color: Color, closedColor: Color? = null) {
    val (open, setOpen) = state
    BasicButton(
        text = if (open) "<" else ">",
        onClick = {
            setOpen(!open)
        },
        modifier = Modifier
            .background(if (open) color else closedColor ?: color)
            .padding(8.dp, 4.dp),
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    background: Color = Color(0xFF222222),
    direction: SlideDirection = SlideDirection.Left,
    open: Boolean = false,
    button: @Composable (MutableState<Boolean>) -> Unit = { DefaultDrawerButton(it, background) },
    content: @Composable () -> Unit,
) {
    val isOpen = remember { mutableStateOf(open) }
    DisableSelection {
        Surface(
            color = background
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                button(isOpen)
                AnimatedVisibility(isOpen.value) {
                    content()
                }
            }
        }
    }
}