package ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import data.nav.Navigation
import data.nav.NavigatorKey

@Composable
fun <K : NavigatorKey> rememberNavigator(
    key: K,
): Navigation<K> {
    val (_, setState) = remember { mutableStateOf(key) }
    return Navigation(key.View) { setState(it) }
}
