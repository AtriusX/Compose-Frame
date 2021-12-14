package ui.component.console

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import ui.component.BasicButton
import ui.component.Drawer

@Composable
fun ActionDrawer() = Drawer {
    Row(Modifier.padding(4.dp, 0.dp)) {
        repeat(5) {
            val i = it + 1
            Action("Action $i") {
                println("Clicked action $i!")
            }
        }
    }
}

@Composable
fun Action(text: String, onClick: () -> Unit) {
    val colors = listOf(Color.Red, Color.Green, Color.Magenta, Color.Blue, Color.Yellow)
    BasicButton(
        text = text,
        onClick = onClick,
        modifier = Modifier.background(Color(0xFF222222)).padding(4.dp),
        color = colors.random(),
        fontFamily = FontFamily.Monospace
    )
}