
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import ui.ComposableFrame
import ui.component.console.ButtonAction
import ui.component.console.Console
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
    val (visible, setVisible) = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            println("Egg")
            delay(500)
        }
    }
    ComposableFrame(
        onCloseRequest = ::exitApplication,
        minSize = Dimension(400, 560),
        icon = icon,
        titleBar = { DefaultTitleBar(layout, icon) }
    ) {
        MaterialTheme {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    setLayout(if (layout == DefaultTitleBarLayout.Windows)
                        DefaultTitleBarLayout.Mac else DefaultTitleBarLayout.Windows)
                }) {
                    Text("Switch title bar layout!")
                }
                Button(onClick = {
                    setVisible(!visible)
                }) {
                    Text("Toggle Console")
                }
                Console(visible) {
                    Row(Modifier.padding(8.dp, 0.dp)) {
                        repeat(5) {
                            val i = it + 1
                            ButtonAction("Action $i") {
                                println("Clicked action $i!")
                            }
                        }
                    }
                }
            }
        }
    }
}
