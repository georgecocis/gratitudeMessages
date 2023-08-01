package ro.nexttech.journalapp.ui.view.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp

@Composable
fun RoundedCornerImage(
    painter: Painter,
    contentDescription: String,
    cornerRadius: Dp,
    imageSize: Dp,
    onClick: (() -> Unit)?
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .fillMaxWidth()
            .clip(RoundedCornerShape(cornerRadius))
            .clickable { onClick?.invoke() }
    )
}