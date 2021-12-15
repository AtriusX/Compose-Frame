package data.nav

import ui.data.Composition

data class Navigation<K : NavigatorKey<K>>(
    val current: Composition,
    val navigate: KeyUpdater<K>
)