package data.nav

import androidx.compose.runtime.Composable

typealias KeyUpdater<K> =
    (K) -> Unit

typealias Navigate<K> =
    @Composable (KeyUpdater<K>) -> Unit
