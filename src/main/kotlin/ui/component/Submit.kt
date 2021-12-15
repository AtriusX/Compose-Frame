package ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import ui.data.Composition

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Submit(
    modifier: Modifier = Modifier,
    key: Key = Key.Enter,
    onSubmit: () -> Unit = {},
    content: Composition = {}
) {
    Box(modifier.onKeyEvent {
        if (it.key == key) onSubmit()
        false
    }) {
        content()
    }
}