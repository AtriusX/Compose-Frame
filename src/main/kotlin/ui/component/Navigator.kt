package ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import data.nav.Navigation
import data.nav.NavigatorKey

@Composable
fun <K : NavigatorKey<K>> rememberNavigator(
    key: K
): Navigation<K> {
    val (state, setState) = remember { mutableStateOf(key) }
    return Navigation({ state.View(setState) }, setState)
}
