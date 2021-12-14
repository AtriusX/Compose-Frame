package ui.component.console

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.io.rememberInterceptor
import kotlinx.coroutines.launch
import ui.data.rememberListState
import java.lang.Integer.max

@Composable
fun Console(
    visible: Boolean,
    allowInput: Boolean = true,
    onItemClick: ((String) -> Unit)? = null,
    background: Color = Color(0xFF222222),
    inputBackground: Color = Color(0xFF151515),
    modifier: Modifier = Modifier,
) {
    val (messages, update) = rememberListState<String>()
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    rememberInterceptor { msg ->
        update {
            if (it.isNotEmpty() && !it.last().endsWith("\n"))
                it[it.lastIndex] += msg else it += msg
        }
    }
    LaunchedEffect(messages.size) {
        val elem = state.layoutInfo.visibleItemsInfo.lastOrNull()
        if (elem == null || elem.index == state.layoutInfo.totalItemsCount - 1)
            state.scrollToItem(max(0, messages.lastIndex))
    }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column(modifier = modifier.fillMaxSize().background(background)) {
            Box(Modifier.weight(1f)) {
                SelectionContainer {
                    ListFeed(
                        state = state,
                        messages = messages,
                        onItemClick = if (allowInput) onItemClick else null
                    )
                }
            }
            if (allowInput) Row(
                Modifier.fillMaxWidth().background(inputBackground),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ConsoleInput(Modifier.weight(1f)) {
                    println(it)
                    scope.launch {
                        state.animateScrollToItem(state.layoutInfo.totalItemsCount - 1)
                    }
                }
                ActionDrawer()
            }
        }
    }
}
