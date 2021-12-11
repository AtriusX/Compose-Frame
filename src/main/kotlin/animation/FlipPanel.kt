package animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import animation.dsl.BuildState
import animation.dsl.FlipState

@Composable
fun FlipPanel(
    condition: Boolean,
    first: FlipState,
    second: FlipState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) = FlipPanel(
    value = condition,
    modifier = modifier,
) {
    on(true then first)
    on(false then second)
    base(content)
}

@Composable
fun <T> FlipPanel(
    value: T,
    modifier: Modifier = Modifier,
    builder: BuildState<T>.() -> Unit = {},
) {
    val (states, default) = remember { BuildState<T>().also(builder) }
    Box(modifier) {
        default()
        for ((mapping, state) in states) {
            val (enter, exit, stateContent) = state
            AnimatedVisibility(
                mapping(value),
                enter = enter,
                exit = exit
            ) {
                stateContent()
            }
        }
    }
}
