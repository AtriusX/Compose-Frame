package ui.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember

typealias ListUpdateFunction<T> =
    ((MutableList<T>) -> Unit) -> Unit

data class ListState<T>(
    val state: MutableList<T>,
    val update: ListUpdateFunction<T>,
)

@Composable
fun <T> rememberListState(default: MutableList<T> = arrayListOf()): ListState<T> {
    val (state, setState) = remember {
        mutableStateOf(default, neverEqualPolicy())
    }

    return ListState(state) {
        it(state)
        setState(state)
    }
}