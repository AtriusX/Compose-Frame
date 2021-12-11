package ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CircleButton(
    color: Color,
    purpose: String = "Unknown",
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Button(
    modifier = modifier.then(Modifier.size(40.dp)),
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = color),
    elevation = null,
    contentPadding = PaddingValues(10.dp)
) {
    Icon(
        painter = object : Painter() {
            override val intrinsicSize: Size = Size(256f, 256f)

            override fun DrawScope.onDraw() {
                drawCircle(Color.White)
            }
        },
        contentDescription = purpose
    )
}