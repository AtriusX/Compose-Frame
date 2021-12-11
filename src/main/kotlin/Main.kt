
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.application
import ui.ComposableFrame
import ui.component.titlebar.DefaultTitleBar
import ui.component.titlebar.DefaultTitleBarLayout
import java.awt.Dimension

class Icon(
    private val draw: DrawScope.() -> Unit = {
        drawCircle(Color(0xFF0099FF))
    },
) : Painter() {
    override val intrinsicSize: Size
        get() = Size(256f, 256f)

    override fun DrawScope.onDraw() = draw()
}

fun main() = application {
    val (layout, setLayout) = remember { mutableStateOf(DefaultTitleBarLayout.Windows) }
    val icon = remember { Icon() }
    ComposableFrame(
        onCloseRequest = ::exitApplication,
        minSize = Dimension(400, 560),
        icon = icon,
        titleBar = { DefaultTitleBar(layout, icon) }
    ) {
        MaterialTheme {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        setLayout(if (layout == DefaultTitleBarLayout.Windows)
                            DefaultTitleBarLayout.Mac else DefaultTitleBarLayout.Windows)
                    }) {
                        Text("Switch title bar layout!")
                    }
                }
            }
        }
    }
}
