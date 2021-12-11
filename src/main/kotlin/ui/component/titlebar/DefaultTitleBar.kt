package ui.component.titlebar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import animation.FlipPanel
import animation.dsl.FlipState
import ui.component.CircleButton
import ui.component.TitleBarScope
import ui.component.titlebar.TitleButtonStyles.Mac
import ui.component.titlebar.TitleButtonStyles.Windows
import kotlin.system.exitProcess

@Composable
fun TitleBarScope.DefaultTitleBar(
    layout: DefaultTitleBarLayout = DefaultTitleBarLayout.Windows,
    icon: Painter? = null,
) {
    val height = Modifier.height(35.dp)
    FlipPanel(
        modifier = Modifier.fillMaxWidth(),
        condition = layout == DefaultTitleBarLayout.Windows,
        first = FlipState {
            TopAppBar(height) {
                WindowsLayout(icon, this)
            }
        },
        second = FlipState {
            TopAppBar(height) {
                MacLayout(this)
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().height(35.dp),
            color = MaterialTheme.colors.primary,
            content = {}
        )
    }
}

@Composable
fun TitleBarScope.WindowsLayout(
    icon: Painter?,
    row: RowScope,
) = with(row) {
    if (icon != null) Image(
        painter = icon,
        contentDescription = "Program Icon",
        modifier = Modifier.padding(9.dp)
    )
    Text(
        text = window.title,
        modifier = Modifier.padding(2.dp, 0.dp).fillMaxWidth().weight(1f),
    )
    Button(onClick = { window.isMinimized = true }, elevation = null) {
        Text(Windows.Minimize.icon, fontWeight = FontWeight.Bold)
    }
    Maximize(
        state = windowState
    )
    Button(onClick = { exitProcess(0) }, elevation = null) {
        Text(Windows.Exit.icon, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TitleBarScope.MacLayout(
    row: RowScope,
) = with(row) {
    CircleButton(
        color = Color(Mac.Exit.color),
        onClick = { exitProcess(0) }
    )
    CircleButton(
        color = Color(Mac.Minimize.color),
        onClick = { window.isMinimized = true }
    )
    Maximize(
        state = windowState,
        layout = DefaultTitleBarLayout.Mac
    )
    Column(Modifier.weight(1f)) {
        Text(
            text = window.title,
            modifier = Modifier.padding(2.dp, 0.dp).align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}