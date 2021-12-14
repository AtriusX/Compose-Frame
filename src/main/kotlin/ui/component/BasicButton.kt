package ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun BasicButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified
) {
    Surface(
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text,
            modifier = modifier,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            fontSize = fontSize,
            textAlign = textAlign,
            color = color
        )
    }
}