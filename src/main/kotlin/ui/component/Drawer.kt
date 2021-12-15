package ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import ui.component.SlideDirection.*
import ui.data.Composition
import ui.data.ValueComposition

@Suppress("unused")
enum class SlideDirection {
    Up, Down, Left, Right
}

@Composable
fun DefaultDrawerButton(
    state: MutableState<Boolean>,
    color: Color,
    closedColor: Color? = null,
    direction: SlideDirection = Left,
) {
    val (open, setOpen) = state
    val text = when (direction) {
        Left, Right -> if (open == (direction == Left)) "<" else ">"
        else        -> if (open == (direction == Down)) "^" else "v"
    }
    BasicButton(
        text = text,
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
    direction: SlideDirection = Left,
    opened: Boolean = false,
    open: EnterTransition = fadeIn() + if (direction == Left || direction == Right)
        expandHorizontally() else expandIn(),
    close: ExitTransition = fadeOut() + if (direction == Left || direction == Right)
        shrinkHorizontally() else shrinkOut(),
    button: ValueComposition<MutableState<Boolean>> = { DefaultDrawerButton(it, background, direction = direction) },
    content: Composition,
) {
    val isOpen = remember { mutableStateOf(opened) }
    DisableSelection {
        Surface(
            color = background
        ) {
            DirectionalLayout(
                modifier = modifier,
                direction = direction
            ) {
                if (direction == Right || direction == Down) {
                    button(isOpen)
                    AnimatedVisibility(isOpen.value, enter = open, exit = close) {
                        content()
                    }
                } else {
                    AnimatedVisibility(isOpen.value, enter = open, exit = close) {
                        content()
                    }
                    button(isOpen)
                }
            }
        }
    }
}

@Composable
private fun DirectionalLayout(
    modifier: Modifier = Modifier,
    direction: SlideDirection,
    content: Composition,
) {
    when (direction) {
        Left, Right -> Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
        else        -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}