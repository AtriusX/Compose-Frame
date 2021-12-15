package ui.component.console

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListFeed(
    state: LazyListState,
    messages: List<String>,
    modifier: Modifier = Modifier,
    onItemClick: ((String) -> Unit)? = null,
) = Box(
    contentAlignment = Alignment.BottomCenter
) {
    LazyColumn(
        state = state,
        modifier = modifier.padding(10.dp, 0.dp, 10.dp, 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
    ) {
        items(messages.size) {
            val click = if (onItemClick != null)
                Modifier.clickable { onItemClick(messages[it]) }
            else
                Modifier
            Box(
                modifier = Modifier.fillParentMaxWidth().height(20.dp) then click
            ) {
                Text(
                    messages[it],
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                )
            }
        }
    }
    VerticalScrollbar(
        adapter = rememberScrollbarAdapter(state),
        modifier = Modifier.align(Alignment.CenterEnd)
    )
}
