import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter

class Icon(
    private val draw: DrawScope.() -> Unit = {
        drawCircle(Color(0xFF0099FF))
    },
) : Painter() {
    override val intrinsicSize: Size
        get() = Size(256f, 256f)

    override fun DrawScope.onDraw() = draw()
}