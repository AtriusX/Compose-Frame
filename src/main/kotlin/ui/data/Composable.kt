package ui.data

import androidx.compose.runtime.Composable

typealias Composition =
    @Composable () -> Unit

typealias ValueComposition<T> =
    @Composable (T) -> Unit