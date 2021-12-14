package ui.component.console

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import ui.component.Submit

@Composable
fun ConsoleInput(
    modifier: Modifier = Modifier,
    onComplete: (String) -> Unit,
) {
    Row(modifier) {
        val (text, setText) = remember { mutableStateOf("") }
        Text(
            text = ">",
            color = Color.White,
            fontFamily = FontFamily.Monospace
        )
        Spacer(Modifier.padding(4.dp))
        Submit(
            onSubmit = {
                if (text.isNotEmpty()) {
                    onComplete(text)
                    setText("")
                }
            }
        ) {
            BasicTextField(
                text,
                onValueChange = {
                    setText(it)
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                ),
                cursorBrush = Brush.verticalGradient(listOf(Color.White, Color.White))
            )
        }
    }
}
