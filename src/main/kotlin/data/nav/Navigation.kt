package data.nav

import ui.data.Composition

data class Navigation<K : NavigatorKey>(
    val current: Composition,
    val navigate: (K) -> Unit
)